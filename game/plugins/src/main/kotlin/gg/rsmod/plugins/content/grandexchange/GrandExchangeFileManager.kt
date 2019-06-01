package gg.rsmod.plugins.content.grandexchange

import java.io.*
import java.util.zip.GZIPInputStream
import java.util.zip.GZIPOutputStream

/*
 * Author: Kyle Escobar
 */
class GrandExchangeFileManager {
    private val GE_OFFERS: String = "data/grandexchange/grandexchange_offers.dat"
    private val GE_OFFERS_HISTORY: String = "data/grandexchange/grandexchange_offers_history.dat"
    private val GE_PRICES: String = "data/grandexchange/grandexchange_prices.dat"

    fun loadGrandExchangeOffers(): MutableList<GrandExchangeOffer>? {
        if (File(GE_OFFERS).exists()) {
            try {
                @Suppress("UNCHECKED_CAST")
                return (loadSerializedFile(File(GE_OFFERS)) as? MutableList<GrandExchangeOffer>)
            } catch(e: Exception) {
                println("[GE-LOGGER] ERROR LOADING GE_OFFERS FILE")
                e.printStackTrace()
                return null
            }
        } else {
            return mutableListOf()
        }
    }

    fun saveGrandExchangeOffers(offers: MutableList<GrandExchangeOffer>) {
        try {
            saveSerializableClass(((offers as? Serializable)!!), File(GE_OFFERS))
        } catch(e: Exception) {
            println("[GE-LOGGER] Error saving GE_OFFERS")
            e.printStackTrace()
        }
    }






    fun loadSerializedFile(file: File): Any? {
        decode(file)

        var file = File(file.path)
        if(!file.exists()) { return null }

        val datastream_in = ObjectInputStream(FileInputStream(file))
        val obj: Any = datastream_in.readObject()
        datastream_in.close()
        return obj
    }

    fun saveSerializableClass(obj: Serializable, file: File) {
        val objectOutputStream = ObjectOutputStream(FileOutputStream(file))
        objectOutputStream.writeObject(obj)
        objectOutputStream.close()

        encode(file)
    }

    private fun encode(file: File) {
        val fis = FileInputStream(file)
        val fos = FileOutputStream(File(file.path + ".encoded"))

        val encodeStream = GZIPOutputStream(fos)
        var buffer: ByteArray = ByteArray(1024)
        var length: Int = fis.read(buffer)

        while(length > 0) {
            encodeStream.write(buffer, 0, length)
            length = fis.read(buffer)
        }

        encodeStream.finish()

        fis.close()
        fos.close()

        file.delete()
        File(file.path + ".encoded").renameTo(File(file.path))
    }

    private fun decode(file: File) {
        val fis = FileInputStream(file)
        val fos = FileOutputStream(File(file.path + ".decoded"))

        var decodeStream = GZIPInputStream(fis)

        var buffer: ByteArray = ByteArray(1024)
        var length: Int = decodeStream.read(buffer)

        while(length > 0) {
            fos.write(buffer, 0, length)
            length = decodeStream.read(buffer)
        }

        decodeStream.close()
        fis.close()
        fos.close()

        file.delete()
        File(file.path + ".decoded").renameTo(File(file.path))
    }
}