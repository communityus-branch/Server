package gg.rsmod.plugins.content.npcs.cow

val COWS = arrayOf(
        Npcs.COW,
        Npcs.COW_2806,
        Npcs.COW_2808,
        Npcs.COW_CALF,
        Npcs.COW_CALF_2809
)

COWS.forEach { npc ->
    set_combat_def(npc) {
        configs {
            attackSpeed = 4
            respawnDelay = 180
        }

        stats {
            hitpoints = 8
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

            defenceStab = -21
            defenceSlash = -21
            defenceCrush = -21
            defenceMagic = -21
            defenceRanged = -21

            attackBonus = 0
            strengthBonus = 0
            rangedStrengthBonus = 0
            magicDamageBonus = 0
        }

        anims {
            block = 5850
            attack = 5849
            death = 5851
        }
    }
}