package gg.rsmod.plugins.content.grandexchange.intr

import gg.rsmod.plugins.content.grandexchange.GrandExchange

intArrayOf(7,8,9,10,11,12,13,14).forEach { button ->
    on_button(465, button) {
        val opt = player.getInteractingOption()
        val slot = player.getInteractingSlot()

        if(slot == 3) {
            player.closeInterface(467)
            player.setVarbit(4397, 0)
            player.queue(TaskPriority.WEAK) { search_item(this) }
        }
    }
}

fun test() {

}

suspend fun search_item(it: QueueTask) {
    val itemId: Int = it.searchItemInput("What would you like to buy?")
    val itemDef: ItemDef = world.definitions.get(ItemDef::class.java,itemId)

    it.player.setVarp(1151, itemId)
    it.player.setComponentText(465,25, itemDef.examine!!)

    val price = GrandExchange.getCheapestSellPrice(itemId)

    it.player.setComponentText(465,26,"$price")
    it.player.setVarbit(4398,price)
    it.player.setVarbit(4396,1)
}