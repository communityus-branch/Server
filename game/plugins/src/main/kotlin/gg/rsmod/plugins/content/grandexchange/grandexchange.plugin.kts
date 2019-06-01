package gg.rsmod.plugins.content.grandexchange

/*
 * Author: Kyle Escobar
 */

on_world_init {
    val offer = GrandExchangeOffer(1, 2357, 20, 243, false)
    offer.setSlot(1)
    offer.setUsername("kyle")
    offer.setStartAmount(20)

    GrandExchange.addOffer(offer)
}