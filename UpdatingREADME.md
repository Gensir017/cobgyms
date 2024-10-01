### Issues when updating to v2.0.0
If updating to v2.0.0 of CobGyms from a lower version (for example v1.0.2) there can be some issues with the custom dimension. Follow these steps to ensure that the update goes smoothly:
- __Make sure that no players are in a gym instance__ when you close the server/singleplayer game.
- Once you have closed the server/singleplayer game, navigate to the world save directory. Inside the world save directory should be a `dimensions` folder. __Open it__.
- Inside the `dimensions` folder there should be a folder named `cobgyms`. __Delete the `cobgyms` folder__.
- Once deleted you can load the server back up again and enjoy v2.0.0!

If these steps are not followed then the custom dimension biome will get confused and revert back to minecraft:plains. This will allow hostile creatures to spawn in the gym instances and potentially kill the player. __It is not easy to get your loot back from a gym__.