package gg.rsmod.plugins.service.worldlist

import com.google.common.reflect.TypeToken
import com.google.gson.Gson
import gg.rsmod.game.Server
import gg.rsmod.game.model.World
import gg.rsmod.game.service.Service
import gg.rsmod.plugins.api.ext.enumSetOf
import gg.rsmod.plugins.service.worldlist.io.WorldListChannelHandler
import gg.rsmod.plugins.service.worldlist.io.WorldListChannelInitializer
import gg.rsmod.plugins.service.worldlist.model.WorldEntry
import gg.rsmod.plugins.service.worldlist.model.WorldLocation
import gg.rsmod.plugins.service.worldlist.model.WorldType
import gg.rsmod.util.ServerProperties
import io.netty.channel.ChannelFuture
import mu.KLogging
import java.io.BufferedWriter
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardOpenOption

/**
 * @author Triston Plummer ("Dread")
 *
 * Handles the initialisation of the network pipeline used for providing the client's world list. Perhaps in the future
 * if we decide to actually handle multiple worlds, this logic should be moved to a login service or some intermediate
 * service that can retrieve data from multiple worlds.
 */
class WorldListService : Service {

    /**
     * The port for this [WorldListService] to listen on
     */
    private var port : Int = 80

    /**
     * The [Path] to the configuration file holding the [WorldEntry] data.
     */
    private lateinit var path : Path

    /**
     * The current app's [WorldEntry].
     */
    @Volatile lateinit var worldEntry : List<WorldEntry>

    /**
     * The [ChannelFuture] for the network service
     */
    private lateinit var channelFuture : ChannelFuture

    /**
     * Initialises the [WorldListService] by checking that the world list configuration file exists,
     * and attempts to parse it.
     *
     * @param server            The server instance
     * @param world             The game world instance
     * @param serviceProperties The properties for this server
     */
    override fun init(server: Server, world: World, serviceProperties: ServerProperties) {
        port = serviceProperties.getOrDefault("port", 80)
        path = Paths.get(serviceProperties.getOrDefault("config-path", "./data/cfg/world.json"))

        // If the world configuration file doesn't exist, spit out a warning and do nothing
        if (!Files.exists(path)) {
            logger.warn("World list configuration file does not exist: $path : Creating example file")
            Files.createFile(path)
            worldEntry = listOf(WorldEntry(1, enumSetOf(WorldType.MEMBERS), "127.0.0.1", "-", WorldLocation.UNITED_STATES, 0), WorldEntry(2, enumSetOf(WorldType.MEMBERS), "127.0.0.1", "-", WorldLocation.UNITED_STATES, 0))
            saveWorldData()
        }

        loadWorldData()
    }

    /**
     * Parses the world list
     */
    private fun loadWorldData() {
        var jsonString: String = ""
        Files.newBufferedReader(path).use { reader ->
            jsonString = reader.readText()
        }
        worldEntry = ArrayList(Gson().fromJson<Array<WorldEntry>>(jsonString, Array<WorldEntry>::class.java).toMutableList())
    }

    private fun saveWorldData() {
        val writer: BufferedWriter = Files.newBufferedWriter(path,StandardCharsets.UTF_8,StandardOpenOption.WRITE)
        writer.use { it.write(Gson().toJson(worldEntry)) }
        writer.close()
    }

    /**
     * Binds the network used for serving the world list
     *
     * @param server    The [Server] context
     * @param world     The [World] instance
     */
    override fun bindNet(server: Server, world: World) {

        // The inbound channel handler for the world list protocol
        val handler = WorldListChannelHandler(worldEntry)

        // Bind the world list network pipeline
        val bootstrap = server.bootstrap.clone()
        bootstrap.childHandler(WorldListChannelInitializer(handler))
        channelFuture = bootstrap.bind(port).syncUninterruptibly()
        logger.info("World list service listening on port $port")
    }

    /**
     * Binds the plugin logic for incrementing and decrementing player count
     *
     * @param server    The [Server] instance
     * @param world     The [World] instance
     */
    override fun postLoad(server: Server, world: World) {
        val plugins = world.plugins
        plugins.bindLogin{ incrementPlayerCount(world.gameContext.worldId) }
        plugins.bindLogout { decrementPlayerCount(world.gameContext.worldId) }
    }

    /**
     * Terminates this [WorldListService]
     *
     * @param server    The [Server] instance
     * @param world     The [World] instance
     */
    override fun terminate(server: Server, world: World) {
        channelFuture.channel().close().syncUninterruptibly()
    }

    /**
     * Increments the player count for every entry in the world list
     */
    private fun incrementPlayerCount(worldId: Int) {
        worldEntry.get(worldId-1).players++
    }

    /**
     * Decrements the player count for every entry in the world list
     */
    private fun decrementPlayerCount(worldId: Int) {
        worldEntry.get(worldId-1).players--
    }

    companion object : KLogging()
}