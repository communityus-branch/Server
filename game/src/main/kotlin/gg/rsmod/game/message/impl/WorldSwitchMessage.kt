package gg.rsmod.game.message.impl

import gg.rsmod.game.message.Message

data class WorldSwitchMessage(val address: String, val id: Int, val mask: Int) : Message