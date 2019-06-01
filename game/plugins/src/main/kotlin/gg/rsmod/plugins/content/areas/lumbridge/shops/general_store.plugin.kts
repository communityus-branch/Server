package gg.rsmod.plugins.content.areas.lumbridge.shops

import gg.rsmod.plugins.content.mechanics.shops.CoinCurrency

spawn_npc(Npcs.SHOP_KEEPER, 3211, 3247, 0, 3)
spawn_npc(Npcs.SHOP_ASSISTANT, 3211, 3247, 0, 3)

create_shop("Lumbridge General Store", CoinCurrency()) {
    items[0] = ShopItem(Items.POT, 5, 1)
    items[1] = ShopItem(Items.JUG, 5, 1)
    items[2] = ShopItem(Items.EMPTY_JUG_PACK, 5, 182)
    items[3] = ShopItem(Items.SHEARS, 2, 1)
    items[4] = ShopItem(Items.BUCKET, 3, 2)
    items[5] = ShopItem(Items.EMPTY_BUCKET_PACK, 15, 650)
    items[6] = ShopItem(Items.BOWL, 2, 5)
    items[7] = ShopItem(Items.CAKE_TIN, 2, 15)
    items[8] = ShopItem(Items.TINDERBOX, 2, 1)
    items[9] = ShopItem(Items.CHISEL, 2, 1)
    items[10] = ShopItem(Items.SPADE, 5, 3)
    items[11] = ShopItem(Items.HAMMER, 5, 1)
    items[12] = ShopItem(Items.NEWCOMER_MAP, 5, 1)
    items[13] = ShopItem(Items.SECURITY_BOOK, 5, 2)
}

var NPC_SHOPKEEPERS = arrayOf(Npcs.SHOP_ASSISTANT, Npcs.SHOP_KEEPER)

NPC_SHOPKEEPERS.forEach { npc ->
    on_npc_option(npc, "talk-to") {
        player.queue {
            this.chatNpc("Can I help you at all?", npc)
            when(this.options("Yes please. What are you selling?", "No thanks.", title = "Select an Option")) {
                1 -> {
                    this.player.openShop("Lumbridge General Store")
                }

                2 -> {
                    this.chatPlayer("No thanks.")
                    this.player.closeInputDialog()
                }
            }
        }
    }

    on_npc_option(npc, "trade") {
        this.player.openShop("Lumbridge General Store")
    }
}