package gg.rsmod.plugins.content.grandexchange.intr

import gg.rsmod.game.model.entity.Player

fun Player.openGrandExchange() {
    GEInterface.openGrandExchange(player = this)
}