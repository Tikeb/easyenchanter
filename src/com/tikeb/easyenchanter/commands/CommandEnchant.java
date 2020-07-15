package com.tikeb.easyenchanter.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import com.tikeb.easyenchanter.helpers.*;
import com.tikeb.easyenchanter.interfaces.*;
import com.tikeb.easyenchanter.models.*;

public class CommandEnchant implements ICommandEnchant {

	private ICommandAdd _addEnchant = new CommandAdd();
	private ICommandRemove _removeEnchant = new CommandRemove();
	private ICommandHelp _helper = new CommandHelp();
	private ICommandBottle _bottle = new CommandBottle();

	private IChatHelper _chat = new ChatHelper();
	private IConfigReader _config = new ConfigReader();
	private IJsonReader _json = new JsonReader();

	private boolean _debug = false;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String arg, String[] args) {

		_debug = _config.getDebug();
		
		try {
			// Check that the sender is a player and not the console.
			if (sender instanceof Player) {

				if (_debug) {
					sender.sendMessage("Command: " + cmd.getName());
					sender.sendMessage("Argument (main): " + arg);
					for (int i = 0; i < args.length; i++) {
						sender.sendMessage("Argument " + i + ": " + args[i]);
					}
				}

				// As sender is of typer Player - set a Player variable.
				// This will be used throughout the code.
				Player player = (Player) sender;

				// Check the user has input arguments into the command.
				if (args.length > 0) {

					// Check if the user requested the help text.
					if (args[0].toLowerCase().equals("help")) {
						_helper.cmdHelp(player);
						return true;
					}

					// Check if the user requested his XP statistics.
					if (args[0].toLowerCase().equals("stats")) {
						_helper.cmdStats(player);
						return true;
					}

					// TODO: Enchanting should be done in the presence of an enchantment table.
					// This should also check the amount of bookcases surrounding the table and
					// only allow certain enchantment's based on this.
					if (!checkForEnchantmentTable(player)) {
						throw new Exception("Please face an enchantment table");
					}

					// Get the player inventory so as to get the item stack from their main hand.
					PlayerInventory inventory = player.getInventory();
					ItemStack item = inventory.getItemInMainHand();

					// Check the player has an item to enchant in their main hand.
					if (item == null || item.getType() == Material.AIR) {
						throw new Exception("Action cancelled - nothing to enchant. Equip an item in yo  ur main hand.");
					}

					if (_debug) {
						_chat.SendMessage(player, "Item in main hand {0}", item.getType().name());
					}

					// Create the item model.
					ItemModel itemModel = new ItemModel();
					itemModel.setItem(item);
					itemModel.setEnchantment(args[0].toLowerCase());

					// The second argument passed should be one of two things: a number to represent
					// the level of the requested enchantment or the text 'refund' to indicate the
					// removal of an enchantment. Here we check for this.
					// If no argument exists continue as could be requesting to bottle experience.
					IntModel lvl = IntHelper.TryParse(args[1]);
					if (lvl.GetParsed())
						itemModel.setLevel(lvl.GetNumber());
					else if (args[1].toLowerCase().equals("refund"))
						itemModel.setRefund(true);

					if (_debug) {
						_chat.SendMessage(player, "Item model: {0}", itemModel.toString());
					}

					// Enchant the item.
					cmdEnchantItem(player, itemModel);
				} else {
					// Send the user a message prompting for correct input.
					_chat.SendMessage(player, "Type '/ee help' for a list of commands.");
				}
			}

			// Command must return true to let Spigot know the command run successfully.
			return true;
		} catch (Exception ex) {
			sender.sendMessage(ChatColor.YELLOW + "EE: Error: " + ex.getMessage());
			return false;
		}
	}

	private void cmdEnchantItem(Player player, ItemModel itemModel) {

		// Check if the user is requesting to bottle his experience.
		if (itemModel.getEnchantment().equals("bottle")) {
			_bottle.cmdBottleExperience(player);
		}

		try {
			if (_debug) {
				_chat.SendMessage(player, "Getting data for item.");
			}

			// Get the data for the item in hand, using the enchantment and level requested.
			itemModel = _json.GetDataForItem(itemModel);

			// Check if the request was valid.
			if (itemModel.getError() != null || itemModel.getError() != "") {
				throw new Exception(itemModel.getError());
			}

			if (_debug) {
				_chat.SendMessage(player, "Got data for item: {0}", itemModel.toString());
			}

			if (itemModel.getRefund()) {

				RefundModel refundModel = _removeEnchant.cmdEnchantRemove(itemModel);
				if (refundModel.getError() != null || refundModel.getError() != "") {
					throw new Exception("Unable to remove enchantment. Perhaps it doesn't exist on this item?");
				}

				// Refund the players experience

			} else {
				// Get the players experience.
				int exp = player.getTotalExperience();

				// Check the player can afford the requested enchantment.
				if (exp > itemModel.getCost()) {
					// Enchant the item.
					itemModel = _addEnchant.cmdEnchantAdd(itemModel);

					if (itemModel.getError() != null || itemModel.getError() != "") {
						throw new Exception(itemModel.getError());
					}

					// Reduce the players experience by the cost of the enchantment.
					player.setExp(exp - itemModel.getCost());

					// Put the enchanted item into the players inventory (main hand). This will
					// replace
					// the item they are holding with the same item + the requested enchantment.
					PlayerInventory inventory = player.getInventory();
					inventory.setItemInMainHand(itemModel.getItem());

				} else {
					// Calculate the missing XP and feedback to the user.
					float missingExp = itemModel.getCost() - exp;
					_chat.SendMessage(player, "You are missing {0} experience for this enchantment.", Float.toString(missingExp));
				}
			}
		} catch (Exception ex) {
			_chat.SendMessage(player, "Failed to enchant! Error: {0}", ex.getMessage());
		}
	}

	private boolean checkForEnchantmentTable(Player player) {
		// Location location = player.getLocation();
		// Location eyeLocation = player.getEyeLocation();
		return true;
	}
}