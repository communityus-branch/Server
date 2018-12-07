package gg.rsmod.game

import gg.rsmod.game.model.Tile
import gg.rsmod.game.model.entity.Player
import gg.rsmod.game.service.GameService
import java.nio.file.Paths

fun main(args: Array<String>) {
    val server = Server()
    server.startServer(apiProps = Paths.get("./data/api.yml"))
    server.startGame(gameProps = Paths.get("./game.yml"), packets = Paths.get("./packets.yml"))

    val gameService = server.getService(GameService::class.java, false).get()
    val world = gameService.world
    for (i in 0 until 1998) {
        val player = Player(world)
        player.username = "Test $i"
        player.tile = Tile(gameService.world.gameContext.home).transform(world.random(-16..16), world.random(-16..16))
        player.register()
    }
}