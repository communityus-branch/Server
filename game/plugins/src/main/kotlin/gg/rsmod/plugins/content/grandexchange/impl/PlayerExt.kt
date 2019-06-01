package gg.rsmod.plugins.content.grandexchange.impl

import gg.rsmod.game.message.impl.GrandExchangeOfferMessage
import gg.rsmod.game.model.entity.Player
import gg.rsmod.plugins.content.grandexchange.GrandExchangeOffer
import gg.rsmod.plugins.content.grandexchange.intr.GEInterface

fun Player.openGrandExchange() {
    GEInterface.openGrandExchange(player = this)
}

fun Player.sendGrandExchangeOffer(offer: GrandExchangeOffer) {
    var state = 1
    if(offer.getBuy()) { state = 2 } else { state = 10 }
    write(GrandExchangeOfferMessage(slot = offer.getSlot(), state = state, itemId = offer.getItemId(), price = offer.getPrice(), totalQuanity = offer.getStartAmount(), quanitySold = offer.getTotalAmountSoFar(), spent = (offer.getTotalAmountSoFar()*offer.getPrice())))
}