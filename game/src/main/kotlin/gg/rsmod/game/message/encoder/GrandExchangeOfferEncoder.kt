package gg.rsmod.game.message.encoder

import gg.rsmod.game.message.MessageEncoder
import gg.rsmod.game.message.impl.GrandExchangeOfferMessage

class GrandExchangeOfferEncoder : MessageEncoder<GrandExchangeOfferMessage>() {
    override fun extract(message: GrandExchangeOfferMessage, key: String): Number = when(key) {
        "slot" -> message.slot
        "state" -> message.state
        "itemId" -> message.itemId
        "price" -> message.price
        "totalQuanity" -> message.totalQuanity
        "quanitySold" -> message.quanitySold
        "spent" -> message.spent
        else -> throw Exception("Unhandled key value")
    }

    override fun extractBytes(message: GrandExchangeOfferMessage, key: String): ByteArray {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}