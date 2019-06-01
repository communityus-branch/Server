package gg.rsmod.plugins.content.grandexchange

import gg.rsmod.game.model.entity.Player

/*
 * Author: Kyle Escobar
 */

object GrandExchange {
    private val _grandExchangeFileManager: GrandExchangeFileManager = GrandExchangeFileManager()

    private var GE_OFFERS: MutableList<GrandExchangeOffer> = mutableListOf()

    // METHODS

    fun loadOffers() {
        GE_OFFERS = _grandExchangeFileManager.loadGrandExchangeOffers()!!
    }

    fun saveOffers() {
        _grandExchangeFileManager.saveGrandExchangeOffers(GE_OFFERS)
    }

    fun addOffer(offer: GrandExchangeOffer) {
        GE_OFFERS.add(offer)
        this.saveOffers()
    }

    fun getGrandExchangeFileManager(): GrandExchangeFileManager { return this._grandExchangeFileManager }

    fun getCheapestSellPrice(itemId: Int): Int {
        var price: Int = 0
        for ( offer: GrandExchangeOffer in GE_OFFERS) {
            if(offer.getBuy() || offer.getItemId() != itemId || offer.getCompleted())
                continue
            if(offer.getPrice() < price || price == 0) {
                price = offer.getPrice()
            }
        }
        return price
    }

    fun getPlayerOffers(player: Player): MutableList<GrandExchangeOffer> {
        val playerName = player.username
        val playerOffers: MutableList<GrandExchangeOffer> = mutableListOf()

        for(offer in GE_OFFERS) {
            if(offer.getUsername() == playerName) {
                playerOffers.add(offer)
            }
        }

        return playerOffers
    }
}