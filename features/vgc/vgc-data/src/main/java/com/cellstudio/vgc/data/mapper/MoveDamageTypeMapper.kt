package com.cellstudio.vgc.data.mapper

import com.cellstudio.vgc.domain.models.MoveDamageType

internal fun Int.toMoveDamageType(): MoveDamageType {
    return when (this) {
        1 -> MoveDamageType.STATUS
        2 -> MoveDamageType.PHYSICAL
        3 -> MoveDamageType.SPECIAL
        else -> MoveDamageType.UNKNOWN
    }
}
