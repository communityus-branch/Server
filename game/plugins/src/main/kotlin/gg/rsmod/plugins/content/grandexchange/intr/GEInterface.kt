package gg.rsmod.plugins.content.grandexchange.intr

import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.api.InterfaceDestination
import gg.rsmod.plugins.api.ext.*
import gg.rsmod.plugins.content.grandexchange.GrandExchange
import gg.rsmod.plugins.content.grandexchange.GrandExchangeOffer
import gg.rsmod.plugins.content.grandexchange.impl.sendGrandExchangeOffer

object GEInterface {
    fun openGrandExchange(player: Player) {
        player.setVarbit(1043,0)
        player.setVarbit(563,0)
        player.setVarp(1151, -1)

        player.runClientScript(828, 1)
        player.runClientScript(2524, -1, -1)

        player.setVarbit(4398, 0)
        player.setVarbit(4396, 0)
        player.setVarbit(4397, 0)
        player.setVarbit(4439, 0)

        player.openInterface(465, InterfaceDestination.MAIN_SCREEN)
        player.openInterface(467, InterfaceDestination.TAB_AREA)

        player.setInterfaceEvents(164,53,-1..-1, 2)

        player.runClientScript(915,3)

        player.setInterfaceEvents(465, 7, 2..2, 6)
        player.setInterfaceEvents(465, 7, 3..4, 2)
        player.setInterfaceEvents(465, 8, 2..2, 6)
        player.setInterfaceEvents(465, 8, 3..4, 2)
        player.setInterfaceEvents(465, 9, 2..2, 6)
        player.setInterfaceEvents(465, 9, 3..4, 2)
        player.setInterfaceEvents(465, 10, 2..2, 6)
        player.setInterfaceEvents(465, 10, 3..4, 2)
        player.setInterfaceEvents(465, 11, 2..2, 6)
        player.setInterfaceEvents(465, 11, 3..4, 2)
        player.setInterfaceEvents(465, 12, 2..2, 6)
        player.setInterfaceEvents(465, 12, 3..4, 2)
        player.setInterfaceEvents(465, 13, 2..2, 6)
        player.setInterfaceEvents(465, 13, 3..4, 2)
        player.setInterfaceEvents(465, 14, 2..2, 6)
        player.setInterfaceEvents(465, 14, 3..4, 2)

        player.setInterfaceEvents(465,22,0..0,2)
        player.setInterfaceEvents(465,23,2..3,1038)
        player.setInterfaceEvents(465,6,0..0,6)
        player.setInterfaceEvents(465,24,0..13,2)
        player.setInterfaceEvents(467, 0,0..27,1026)

        player.setComponentText(465,25,"Click the icon on the left to search for items.")
        player.setComponentText(465,26,"")
        player.setComponentText(465,16,"")
        player.setComponentText(465,17,"")

        for(offer: GrandExchangeOffer in GrandExchange.getPlayerOffers(player)) {
            player.sendGrandExchangeOffer(offer)
        }
    }

    fun setupMainScreenVarbits(player: Player) {
        player.setVarbit(1043,0)
        player.setVarbit(563,0)
        player.setVarp(1151, -1)

        player.runClientScript(828, 1)
        player.runClientScript(2524, -1, -1)

        player.setVarbit(4398, 0)
        player.setVarbit(4396, 0)
        player.setVarbit(4397, 0)
        player.setVarbit(4439, 0)
    }
}