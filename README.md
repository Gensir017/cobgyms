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

# CobGyms
CobGyms is an unofficial implementation of Gyms using Cobblemon & Cobblemon Trainers __for both Fabric and Forge__. These have some similarity to the Gyms from the games but differ in a few unique ways which I hope are an improvement to the whole system.

__You enter a Gym by using a Poké Gym Key__, allowing you to choose the level of the Pokémon that you would be battling. Entering a Gym consumes the Poké Gym Key.

There are currently three themes of Gyms implemented (Woodland, Volcanic, Aquatic), and using a key will result in you __entering one at random__ (incentivising preperation via team type diversity).

The Gym Leader and Gym Trainers teams are generated from a pre-defined selection based on the randomised theme and scale to the Gym Pokémon level you chose, with any evolution logic being handled.

Losing or forfeiting against the Gym Leader or any of the Gym Trainers will result in a whiteout, __which forces you to leave the Gym with no rewards__.

Beating a Gym yields __rewards that scale based on the Gym Pokémon level chosen__, for example a level 30 Gym is much more likely to give better rewards than a level 5 Gym.


---
<details>
  <summary><b>How to get Poké Gym Keys</b></summary>
  Poké Gym keys grant access to the instanced Gyms. There are currently three methods of acquisition:<br><br>
  <ul>
    <li><b>Chests in Vanilla or Cobblemon structures</b> (for example Villages or Gimmi Towers). For most chests there is about a 75% chance to contain a key, but this can differ based on the structure.</li><br>
    <li><b>Mining between Y-level -40 and -60 to find Ancient Relics</b>. These will always drop 1 Poké Gym Key when broken with a pickaxe, regardless of fortune or silk touch enchantments. It should be quite rare to find this ore.</li><br>
    <li><b>Beating or Capturing a Wild Pokémon has a small chance to drop a key</b> (which will notify the player in the chat). The chance varies between roughly 5-10%, with higher level Wild Pokémon having a higher drop chance. Capturing a Pokémon also gives a greater drop chance.</li><br>
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
  Poké shards are used to make an empty Poké Cache of the corresponding rarity, or can be dismantled to a lower rarity of Poké shards.
</details>

---
<details>
  <summary><b>Poké Caches (Made from Poké Shards)</b></summary>
  Empty Poké Caches are made from Poké shards. Poké caches come in four different rarities: Lesser, Adept, Naster and Legendary. An empty Poké Cache can be further crafted into a specific theme (Woodland, Volcanic, Aquatic).<br><br>Using a Poké Cache will consume it and give you a random Pokémon of the given theme and rarity from a pre-defined list. For example using a Master Volcanic Poké Cache has a chance of giving you a Charmander or Tyrunt or Gible etc. <b>All Pokémon gained through a Poké Cache will start at level 1</b>.<br><br>You can further craft a Shiny variant of a given Poké Cache by surrounding it with Poké Shards of the same rarity, which has the same functionality as a Poké Cache but has a higher chance of giving a Shiny (1/50 chance).<br><br>Legendary Poké Caches (crafted from Legendary Poké Shards) are the highest rarity and will give a random Legendary Pokémon. Currently the list only includes Legendary Pokémon that the official Cobblemon mod has models for which is only a handful. This is hopefully a unique and challenging way of getting Legendaries, as the Legendary Poké shards only drop from levels 90-100 and in very small quantities.<br>
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
### Planned Features
- Config for the various values such as Wild Pokémon Level Scaling timer or Shiny chance from Shiny Poké Caches etc.
- Optional Datapacks that modifiy the recipes of Poké Caches to use Poké Shard Blocks instead of shards, meaning they would be more expensive and harder to get.

---
### Support
_Discord link coming soon..._

---
### Donate
_Donate link coming soon..._

---
### Credits