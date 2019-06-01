package gg.rsmod.plugins.content.areas.lumbridge.objs

// South Stairs

on_obj_option(Objs.STAIRCASE_16671, "climb-up") {
    player.moveTo(player.tile.x,player.tile.z,1)
}

on_obj_option(Objs.STAIRCASE_16672, "climb-down") {
    player.moveTo(player.tile.x,player.tile.z,0)
}

on_obj_option(Objs.STAIRCASE_16672, "climb-up") {
    player.moveTo(player.tile.x,player.tile.z,2)
}

on_obj_option(Objs.STAIRCASE_16672, "climb") {
    player.queue { staircase_16672_climb(this)}
}

on_obj_option(Objs.STAIRCASE_16673, "climb-down") {
    player.moveTo(player.tile.x, player.tile.z, 1)
}


suspend fun staircase_16672_climb(it: QueueTask): Unit? {
    val tile = when(it.options("Climb up the stairs.", "Climb down the stairs.", title = "Climb up or down the stairs?")) {
        1 -> Tile(it.player.tile.x,it.player.tile.z,2)
        2 -> Tile(it.player.tile.x,it.player.tile.z,0)
        else -> return null
    }

    it.player.moveTo(tile)
    return null
}