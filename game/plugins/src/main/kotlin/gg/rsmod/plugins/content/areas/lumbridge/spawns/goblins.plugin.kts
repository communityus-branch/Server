package gg.rsmod.plugins.content.areas.lumbridge.spawns

// East of river spawns
val east_spawn_tiles = arrayOf(
        Tile(3250,3251, 0),
        Tile(3262, 3243, 0),
        Tile(3254,3244,0),
        Tile(3249,3242,0),
        Tile(3241,3241,0),
        Tile(3248,3233,0),
        Tile(3255,3230,0),
        Tile(3261,3226,0),
        Tile(3263,3235,0),
        Tile(3246,3246,0),
        Tile(3244,3246,0)
)

val goblin_npc_ids = arrayOf(
        Npcs.GOBLIN_3029,
        Npcs.GOBLIN_3030,
        Npcs.GOBLIN_3031,
        Npcs.GOBLIN_3032,
        Npcs.GOBLIN_3033,
        Npcs.GOBLIN_3034
)

val spawn_count = 30

for (i in 0..spawn_count) {
    val _npc = goblin_npc_ids[(0 until (goblin_npc_ids.size-1)).random()]
    val _tile = east_spawn_tiles[(0 until (east_spawn_tiles.size-1)).random()]
    spawn_npc(_npc, _tile.x, _tile.z, _tile.height, 20)
}