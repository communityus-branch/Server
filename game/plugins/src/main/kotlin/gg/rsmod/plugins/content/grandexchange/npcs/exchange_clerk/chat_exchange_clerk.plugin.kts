package gg.rsmod.plugins.content.grandexchange.npcs.exchange_clerk

import gg.rsmod.plugins.content.grandexchange.intr.openGrandExchange

/*
 * Author: Kyle Escobar
 */

val EXCHANGE_CLERKS = arrayOf(
        Npcs.GRAND_EXCHANGE_CLERK,
        Npcs.GRAND_EXCHANGE_CLERK_2151,
        Npcs.GRAND_EXCHANGE_CLERK_2150,
        Npcs.GRAND_EXCHANGE_CLERK_2149
)

EXCHANGE_CLERKS.forEach { npc ->
    on_npc_option(npc, "talk-to", 2) {
        player.queue { chat_exchange_clerk(this, npc)}
    }

    on_npc_option(npc, "exchange", 2) {
        player.openGrandExchange()
    }

    on_npc_option(npc, "history", 2) {
        player.message("TODO")
    }

    on_npc_option(npc, "sets", 2) {
        player.message("TODO")
    }
}

suspend fun chat_exchange_clerk(it: QueueTask, npc: Int) {
    it.chatNpc(message = "Welcome to the Grand Exchange.<br>Would you like to trade now, or exchange item sets?", title = "Grand Exchange Clerk", npc = npc)

    when(it.options("How do I use the Grand Exchange?", "I'd like to set up trade offers please.", "Can you help me with item sets?", "I'd like Exchange collection reminders on login, please.", "I'm find, thanks.", title = "Select an Option")) {
        1 -> {
            it.player.message("TODO")
        }

        2 -> {
            it.player.message("TODO")
        }

        3 -> {
            it.player.message("TODO")
        }

        4 -> {
            it.player.message("TODO")
        }

        5 -> {
            it.chatPlayer(message = "I'm fine, thanks.", title = it.player.username)
            it.player.closeInputDialog()
        }
    }
}