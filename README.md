# README #

Easy enchanter is a plugin for the Spigot Minecraft server. 
It allows users to type specific commands in order to be able to enchant items within the game. 
The enchantments can be configured via a json file and will allow for non-standard configurations.

### Available Commands ###

* /ee
* /ee help
* /ee stats
* /ee bottle
* /ee (enchantment)
* /ee (enchantment) (level)
* /ee (enchantment) refund

### Command Details ###

**/ee**

Prompts user to type /ee help

**/ee help**

Displays to the user a list of available commands

**/ee stats**

Displays to the user his experience and level

**/ee bottle**

If the user is holding an empty bottle, his experience will be transferred to the bottle

**/ee (enchantment)**

If the user does not specify a level then the available levels and costs will be listed

**/ee (enchantment) (level)**

Enchants the currently held item with the requested enchantment/level if available

**/ee (enchantment) refund**

Refunds the experience cost (depending on % configured. Default 80%) and removes the enchantment


License
Copyright (c) 2016 Anthony Ward
Released under the [Apache License 2.0.](https://bitbucket.org/Emohawk/easyenchanter/raw/01a79a44b4e603fe50a2e3b81eaf744d1ab09a94/LICENCE)