package gg.rsmod.plugins.content.grandexchange

import java.io.Serializable

/*
 * Author: Kyle Escobar
 *
 *
 * This class is what is stored in the data/grandexchange_offers.dat file
 *
 * NOTE TO SELF !! ALL THE LOGIC IS DONE IN plugins/content/inter/grandexchange/serializers/BinaryPayload.kt
 *
 * Below is what is happening to the data structure because I keep forgetting. Lol
 *
 * 1. Properties are added as elements to the SerialDescriptor object as BinaryPayload.
 * 2. WHEN SERIALIZING -> Each element is encoded by converting them to strings. Then converting
 *    the string's binary data into HEX.
 * 3. WHEN DESERIALIZING -> BinaryPayload elements are decoded from hex into binary and then converted into
 *    Strings. They are then parsed back into the element indexes.
 * 4. A new object of the instance is then created with each property mapped to it's respected BinaryPayload element.
 */

class GrandExchangeOffer(private val offerId: Long, private val id: Int, private val amount: Int, private val price: Int, private val buy: Boolean = false) : Serializable {
    private var _offerId: Long = this.offerId
    private var _itemId: Int = this.id
    private var _amount: Int = this.amount
    private var _price: Int = this.price
    private var _buy: Boolean = this.buy

    private var _start_amount: Int = this.amount

    private var _cancelled: Boolean = false
    private var _username: String = ""
    private var _slot: Int = -1
    private var _totalPriceSoFar: Int = -1 // total gold received or spent so far
    private var _totalAmountSoFar: Int = -1 // total items received or spent so far
    private var _completed: Boolean = false

    fun getOfferId(): Long { return this._offerId }
    fun getItemId(): Int { return this._itemId }
    fun getAmount(): Int { return this._amount }
    fun getStartAmount(): Int { return this._start_amount }
    fun getPrice(): Int { return this._price }
    fun getBuy(): Boolean { return this._buy }
    fun getCancelled(): Boolean { return this._cancelled }
    fun getUsername(): String { return this._username }
    fun getSlot(): Int { return this._slot }
    fun getTotalPriceSoFar(): Int { return this._totalPriceSoFar }
    fun getTotalAmountSoFar(): Int { return this._totalAmountSoFar }
    fun getCompleted(): Boolean { return this._completed }

    fun setOfferId(offerId: Long) {
        this._offerId = offerId
    }

    fun setItemId(itemId: Int) {
        this._itemId = itemId
    }

    fun setAmount(amount: Int) {
        this._amount = amount
    }

    fun setStartAmount(startAmount: Int) {
        this._start_amount = startAmount
    }

    fun setPrice(price: Int) {
        this._price = price
    }

    fun setBuy(buyOrder: Boolean) {
        this._buy = buyOrder
    }

    fun setCancelled(cancelled: Boolean) {
        this._cancelled = cancelled
    }

    fun setUsername(username: String) {
        this._username = username
    }

    fun setSlot(slot: Int) {
        this._slot = slot
    }

    fun setTotalPriceSoFar(totalPriceSoFar: Int) {
        this._totalPriceSoFar = totalPriceSoFar
    }

    fun setTotalAmountSoFar(totalAmountSoFar: Int) {
        this._totalAmountSoFar = totalAmountSoFar
    }

    fun setCompleted(state: Boolean) {
        this._completed = state
    }
}
