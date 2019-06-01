package gg.rsmod.plugins.content.npcs.goblin_2

val GOBLIN_LVL_2 = arrayOf(
        Npcs.GOBLIN_3029,
        Npcs.GOBLIN_3030,
        Npcs.GOBLIN_3031,
        Npcs.GOBLIN_3032,
        Npcs.GOBLIN_3033,
        Npcs.GOBLIN_3034
)

GOBLIN_LVL_2.forEach { npc ->
    set_combat_def(npc) {
        configs {
            attackSpeed = 4
            respawnDelay = 270
        }

        stats {
            hitpoints = 5
            attack = 1
            strength = 1
            defence = 1
            magic = 1
            ranged = 1
        }

        bonuses {
            attackStab = 0
            attackSlash = 0
            attackCrush = 0
            attackMagic = 0
            attackRanged = 0

            defenceStab = -15
            defenceSlash = -15
            defenceCrush = -15
            defenceMagic = -15
            defenceRanged = -15

            attackBonus = 0
            strengthBonus = 0
            rangedStrengthBonus = 0
            magicDamageBonus = 0
        }

        anims {
            block = 6183
            attack = 6184
            death = 6182
        }
    }
}