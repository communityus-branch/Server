package gg.rsmod.plugins.content.areas.lumbridge.objs

import gg.rsmod.plugins.content.inter.bank.openBank

var BANK_BOOTHS = arrayOf(
        Objs.BANK_BOOTH_18491,
        Objs.BANK_BOOT_27291
)

BANK_BOOTHS.forEach { obj ->
    on_obj_option(obj, "bank") {
        player.openBank()
    }

    on_obj_option(obj, "collect") {
        open_collect(player)
    }
}

fun open_collect(p: Player) {
    p.setInterfaceUnderlay(color = -1, transparency = -1)
    p.openInterface(interfaceId = 402, dest = InterfaceDestination.MAIN_SCREEN)
}