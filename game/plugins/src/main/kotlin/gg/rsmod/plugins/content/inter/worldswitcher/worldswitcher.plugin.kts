package gg.rsmod.plugins.content.inter.worldswitcher

import gg.rsmod.game.GameContext
import gg.rsmod.game.message.impl.LogoutFullMessage
import gg.rsmod.game.message.impl.WorldSwitchMessage
import gg.rsmod.game.model.timer.ACTIVE_COMBAT_TIMER
import gg.rsmod.game.system.LoginSystem
import gg.rsmod.plugins.service.worldlist.WorldListService
import gg.rsmod.plugins.service.worldlist.model.WorldEntry

on_button(interfaceId = WorldSwitcherTab.BUTTON_PARENT_ID, component = WorldSwitcherTab.BUTTON_COMPONENT_ID) {
    openWorldSwitcher(player)
}

on_button(WorldSwitcherTab.INTERFACE_ID, component = 16) {
    val opt = player.getInteractingOption()
    val slot = player.getInteractingSlot()

    if(opt == 1) {
        // Option = Switch

        if(!player.timers.has(ACTIVE_COMBAT_TIMER)) {
            switchWorld(player,slot)
        } else {
            player.message("You can't log out until 10 seconds after the end of combat.")
        }

    } else if(opt == 2) {
        // Option = Favorite
        var varbitToSet = 4597
        if(player.getVarbit(4597) != 0) {
            varbitToSet = 4598
        }

        player.setVarbit(varbitToSet, slot)
    }
}

on_button(WorldSwitcherTab.INTERFACE_ID, component = 3) {
    player.closeInterface(69)
}

on_button(WorldSwitcherTab.INTERFACE_ID, component = 23) {
    if (!player.timers.has(ACTIVE_COMBAT_TIMER)) {
        player.requestLogout()
        player.write(LogoutFullMessage())
        player.channelClose()
    } else {
        player.message("You can't log out until 10 seconds after the end of combat.")
    }
}

intArrayOf(21, 22).forEach { favoriteWorld ->
    on_button(WorldSwitcherTab.INTERFACE_ID, component = favoriteWorld) {
        val opt = player.getInteractingOption()
        if(opt == 1) {
            if(favoriteWorld == 21) {
                switchWorldById(player, player.getVarbit(4597))
            }
            else if(favoriteWorld == 22) {
                switchWorldById(player, player.getVarbit(4598))
            }
            else {
                player.message("This world is no longer your favorite.")
            }
        }

        if(opt == 2) {
            if(favoriteWorld == 21) {
                player.setVarbit(4597, 0)
            }
            else if(favoriteWorld == 22) {
                player.setVarbit(4598, 0)
            }
            else {
                player.message("This world is no longer your favorite.")
            }
        }
    }
}

fun openWorldSwitcher(player: Player) {
    player.runClientScript(915,10)

    player.setInterfaceEvents(69,16, range = 0..600, setting = 6)

    player.openInterface(WorldSwitcherTab.INTERFACE_ID, InterfaceDestination.LOG_OUT)
}

fun switchWorld(player: Player, slot: Int) {
    val worlds: List<WorldEntry> = world.getService(WorldListService::class.java)!!.worldEntry

    player.runClientScript(841)
    player.write(WorldSwitchMessage(worlds.get(slot-1).address, worlds.get(slot-1).id, worlds.get(slot-1).location.id))
}

fun switchWorldById(player: Player, worldId: Int) {
    val worlds: List<WorldEntry> = world.getService(WorldListService::class.java)!!.worldEntry
    var world: WorldEntry? = null
    for(i in worlds) {
        if(i.id == worldId) {
            world = i
            break
        }
    }

    if (world != null) {
        player.runClientScript(841)
        player.write(WorldSwitchMessage(world.address, world.id, world.location.id))
    } else {
        throw Exception("Wtf did you do to break this...")
    }
}