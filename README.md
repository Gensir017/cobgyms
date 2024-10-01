# ! IF UPDATING TO v2.0.0 FROM AN OLDER VERSION READ [THIS]()

<details>
  <summary><b>Requirements</b></summary>
  CobGyms requires the following mods in order to function:<br><br>
  <ul>
    <li><a href="https://modrinth.com/mod/architectury-api">Architectury</a></li><br>
    <li><a href="https://modrinth.com/mod/cobblemon">Cobblemon</a></li><br>
    <li><a href="https://modrinth.com/mod/cobblemontrainers">Cobblemon Trainers</a></li><br>
    <li><a href="https://modrinth.com/mod/fabric-api">Fabric API</a> (if using Fabric)</li><br>
    <li><a href="https://modrinth.com/mod/kotlin-for-forge">Kotlin for Forge</a> (if using Forge)</li>
  </ul>
</details>

![https://i.imgur.com/JEMjMMW.png](https://i.imgur.com/JEMjMMW.png)
<div align="center">
  <em>Woodland themed Gym</em>
</div><br><br>

# CobGyms
CobGyms is an unofficial implementation of Gyms using Cobblemon & Cobblemon Trainers __for both Fabric and Forge__. These have some similarity to the Gyms from the games but differ in a few unique ways which I hope are an improvement to the whole system.

__There are 2 different ways of entering a Gym__. You can enter via naturally spawning Gym Entrance structures in the overworld __OR__ you can enter a random one by using a Poké Gym Key.

There are currently three themes of Gyms implemented: Woodland, Volcanic, Aquatic.

The Gym Leader and Gym Trainers teams are generated from a pre-defined selection based on the theme and scale to the Gym Pokémon level you chose, with any evolution logic being handled.

Losing or forfeiting against the Gym Leader or any of the Gym Trainers will result in a whiteout, __which forces you to leave the Gym with no rewards__.

Beating a Gym yields __rewards that scale based on the Gym Pokémon level chosen__, for example a level 30 Gym is much more likely to give better rewards than a level 5 Gym.

---
<details>
  <summary><b>Gym Entrance Structures</b></summary>
  Gym Entrance structures are one of the two ways of entering Gyms. They will enter you into a specific themed Gym. There are currently three different of variants:<br><br>
  <ul>
    <li><b>Woodland Gym Entrance</b> - Most commonly found in forests, jungle, swamp etc.</li><br>
    <li><b>Volcanic Gym Entrance</b> - Most commonly found in deserts, mesa, savanna etc.</li><br>
    <li><b>Aquatic Gym Entrance</b> - Most commonly found in snowy biomes, taiga etc.</li><br>
  </ul>
</details>

---
<details>
  <summary><b>How to get Poké Gym Keys</b></summary>
  Poké Gym keys are one of the two ways of entering Gyms. They will enter you into a random themed Gym. There are currently three methods of acquisition:<br><br>
  <ul>
    <li><b>Chests in Vanilla or Cobblemon structures</b> (for example Villages or Gimmi Towers). For most chests there is about a 75% chance to contain a key, but this can differ based on the structure.</li><br>
    <li><b>Mining between Y-level -40 and -60 to find Ancient Relics</b>. These will always drop 1 Poké Gym Key when broken with a pickaxe, regardless of fortune or silk touch enchantments. It should be quite rare to find this ore.</li><br>
    <li><b>Beating or Capturing a Wild Pokémon has a small chance to drop a key</b> (which will notify the player in the chat). The chance varies between roughly 1-3%, with higher level Wild Pokémon having a higher drop chance. Capturing a Pokémon also gives a greater drop chance.</li><br>
  </ul>
</details>

---
<details>
  <summary><b>Poké Shards (Gym Completion Reward)</b></summary>
  Poké shards are <b>one</b> of the various rewards you can get for beating a Gym. There are four different types: Lesser, Adept, Master and Legendary. These drop at different Gym Pokémon level intervals:<br><br>
  <ul>
    <li>Lesser: level 0-59 (most common at level 30)</li><br>
    <li>Adept: level 30-89 (most common at level 60)</li><br>
    <li>Master: level 60-100 (most common at level 90)</li><br>
    <li>Legendary: level 90-100 (most common at level 100)</li><br>
  </ul>
  Poké shards are used to make a Poké Cache of the corresponding rarity, or can be dismantled to a lower rarity of Poké shards.
</details>

---
<details>
  <summary><b>Poké Caches (Made from Poké Shards)</b></summary>
  Poké Caches are made from Poké shards. Poké caches come in four different rarities: Lesser, Adept, Master and Legendary.<br><br>When you use a Poké Cache you can choose a specific theme and you will get a random Pokémon of the given theme and rarity from a pre-defined list. For example using a Master Poké Cache and selecting the Volcanic theme has a chance of giving you a Charmander or Tyrunt or Gible etc. <b>All Pokémon gained through a Poké Cache will start at level 1</b>.<br><br>You can further craft a Shiny variant of a given Poké Cache by surrounding it with Poké Shards of the same rarity, which has the same functionality as a Poké Cache but has a higher chance of giving a Shiny (1/50 chance).<br><br>Legendary Poké Caches (crafted from Legendary Poké Shards) are the highest rarity and will give a random Legendary Pokémon. Currently the list only includes Legendary Pokémon that the official Cobblemon mod has models for which is only a handful. This is hopefully a unique and challenging way of getting Legendaries, as the Legendary Poké shards only drop from levels 90-100 and in very small quantities.<br>
</details>

---
<details>
  <summary><b>Wild Pokémon Level Scaling</b></summary>
  Wild Pokémon in a radius around you will occasionally (roughly every 10 mins) have their level scaled up. The level that they will be scaled to will be a bit less than your highest Gym Pokémon Level beaten. <b>This only occurs if there are not already high level Wild Pokémon around the player</b>.<br><br>This way Wild Pokémon around each player should start to follow the players progression a bit more, and players will start to see Wild Pokémon closer to their actual team level more regularly.
</details>

---
### Commands
- `/cobgyms whichCache <pokemon>` - Will tell you which Poké Cache(s) the given Pokémon is in and the chance that you get that specific Pokémon from that specific cache.
- `/cobgyms listCache <cache name>` - Will tell you all of the Pokémon that can be obtained from a given cache, with the overall chance for any given Pokémon.
- `/cobgyms forceLeave <player>` - Requires permission. Forces a given player to leave the instanced Gym incase they are stuck somehow and can't get to the exit.

---
### Credits
- I cannot thank __Selfdot__ enough for not only creating [Cobblemon Trainers](https://modrinth.com/mod/cobblemontrainers) but also for helping me extensively when trying to use his mod API. CobGyms would not have been possible without him.
- Of course [Cobblemon](https://modrinth.com/mod/cobblemon) and the team behind it, again this wouldn't have been possible without them.

---
### Having Issues?
You can find me in the [Official Cobblemon Discord](https://discord.gg/cobblemon) where I have a made a [Support Thread](https://discord.com/channels/934267676354834442/1287715521050972191). Let me know here if you have any issues or feedback.

---
### Donate
Any support is appreciated, I have a [Ko-fi](https://ko-fi.com/gensir) where you can donate if you wish.

---

![https://i.imgur.com/5FSGlON.png](https://i.imgur.com/5FSGlON.png)
<div align="center">
  <em>Aquatic themed Gym</em>
</div><br><br>

![https://i.imgur.com/LNuafMr.png](https://i.imgur.com/LNuafMr.png)
<div align="center">
  <em>Volcanic themed Gym</em>
</div><br><br>