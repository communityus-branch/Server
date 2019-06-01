package gg.rsmod.game.message.encoder

import gg.rsmod.game.message.MessageEncoder
import gg.rsmod.game.message.impl.WorldSwitchMessage

class WorldSwitchEncoder : MessageEncoder<WorldSwitchMessage>() {

    override fun extract(message: WorldSwitchMessage, key: String): Number = when (key) {
        "id" -> message.id
        "mask" -> message.mask
        else -> throw Exception("Unhandled value key.")
    }

    override fun extractBytes(message: WorldSwitchMessage, key: String): ByteArray = when (key) {
        "address" -> {
            val option = message.address
            val data = ByteArray(option.length + 1)
            System.arraycopy(option.toByteArray(), 0, data, 0, data.size - 1)
            data[data.size - 1] = 0
            data
        }
        else -> throw Exception("Unhandled value key.")
    }
}