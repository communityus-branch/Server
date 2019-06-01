package gg.rsmod.plugins.content.areas.lumbridge.spawns

val east_spawns = arrayOf(
        Tile(3257,3260,0),
        Tile(3261,3260,0),
        Tile(3263,3264,0),
        Tile(3255,3269,0),
        Tile(3262,3271,0),
        Tile(3255,3278,0),
        Tile(3262,3284,0),
        Tile(3254,3289,0),
        Tile(3247,3284,0),
        Tile(3247,3294,0)
)

val cow_npcs = arrayOf(
        Npcs.COW,
        Npcs.COW_2806,
        Npcs.COW_2808,
        Npcs.COW_CALF,
        Npcs.COW_CALF_2809
)

val spawn_count = 40

for(i in (0..spawn_count)) {
    val _npc = cow_npcs[(0 until (cow_npcs.size-1)).random()]
    val _tile = east_spawns[(0 until (east_spawns.size-1)).random()]
    spawn_npc(_npc, _tile.x, _tile.z, _tile.height, 15)
}