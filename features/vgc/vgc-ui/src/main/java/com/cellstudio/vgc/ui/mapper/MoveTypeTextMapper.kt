package com.cellstudio.vgc.ui.mapper

import com.cellstudio.vgc.domain.models.MoveType
import com.cellstudio.vgc.ui.R

internal fun MoveType.toMoveTypeText(): Int {
    return when (this) {
        MoveType.NORMAL -> R.string.moves_vgc_normal
        MoveType.FIGHTING -> R.string.moves_vgc_fighting
        MoveType.FLYING -> R.string.moves_vgc_flying
        MoveType.POISON -> R.string.moves_vgc_poison
        MoveType.GROUND -> R.string.moves_vgc_ground
        MoveType.ROCK -> R.string.moves_vgc_rock
        MoveType.BUG -> R.string.moves_vgc_bug
        MoveType.GHOST -> R.string.moves_vgc_ghost
        MoveType.STEEL -> R.string.moves_vgc_steel
        MoveType.FIRE -> R.string.moves_vgc_fire
        MoveType.WATER -> R.string.moves_vgc_water
        MoveType.GRASS -> R.string.moves_vgc_grass
        MoveType.ELECTRIC -> R.string.moves_vgc_electric
        MoveType.PSYCHIC -> R.string.moves_vgc_psychic
        MoveType.ICE -> R.string.moves_vgc_ice
        MoveType.DRAGON -> R.string.moves_vgc_dragon
        MoveType.DARK -> R.string.moves_vgc_dark
        MoveType.FAIRY -> R.string.moves_vgc_fairy
        MoveType.UNKNOWN -> R.string.moves_vgc_unknown
        MoveType.SHADOW -> R.string.moves_vgc_shadow
        else -> R.string.moves_vgc_unknown
    }
}