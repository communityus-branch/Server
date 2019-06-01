package gg.rsmod.plugins.content.areas.lumbridge.objs

on_obj_option(Objs.LADDER_16683, "climb-up") {
    player.moveTo(player.tile.x, player.tile.z, height = 1)
}

on_obj_option(Objs.LADDER_16684, "climb-up") {
    player.moveTo(player.tile.x, player.tile.z, height = 2)
}

on_obj_option(Objs.LADDER_16684, "climb-down") {
    player.moveTo(player.tile.x, player.tile.z, height = 0)
}

on_obj_option(Objs.LADDER_16684, "climb") {
    player.queue { climb_ladder_16684(this)}
}





suspend fun climb_ladder_16684(it: QueueTask): Unit? {
    var tile = when(it.options("Climb up the ladder.", "Climb down the ladder.", title = "Climb up or down the ladder?")) {
        1 -> Tile(it.player.tile.x, it.player.tile.z, height = 2)
        2 -> Tile(it.player.tile.x, it.player.tile.z, height = 0)
        else -> return null
    }

    it.player.moveTo(tile)
    return null
}