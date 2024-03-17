package com.cellstudio.vgc.data.mapper

import com.cellstudio.vgc.domain.models.MoveType

internal fun Int.toMoveType(): MoveType {
    return when (this) {
        1 -> MoveType.NORMAL
        2 -> MoveType.FIGHTING
        3 -> MoveType.FLYING
        4 -> MoveType.POISON
        5 -> MoveType.GROUND
        6 -> MoveType.ROCK
        7 -> MoveType.BUG
        8 -> MoveType.GHOST
        9 -> MoveType.STEEL
        10 -> MoveType.FIRE
        11 -> MoveType.WATER
        12 -> MoveType.GRASS
        13 -> MoveType.ELECTRIC
        14 -> MoveType.PSYCHIC
        15 -> MoveType.ICE
        16 -> MoveType.DRAGON
        17 -> MoveType.DARK
        18 -> MoveType.FAIRY
        10001 -> MoveType.UNKNOWN
        10002 -> MoveType.SHADOW
        else -> MoveType.UNKNOWN
    }
}
