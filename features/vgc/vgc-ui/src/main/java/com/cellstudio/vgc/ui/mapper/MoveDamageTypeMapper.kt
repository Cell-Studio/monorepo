package com.cellstudio.vgc.ui.mapper

import com.cellstudio.vgc.domain.models.MoveDamageType
import com.cellstudio.vgc.ui.R

internal fun MoveDamageType.toMoveDamageTypeText(): Int {
    return when (this) {
        MoveDamageType.UNKNOWN -> R.string.moves_vgc_damage_type_unknown
        MoveDamageType.SPECIAL -> R.string.moves_vgc_damage_type_special
        MoveDamageType.PHYSICAL -> R.string.moves_vgc_damage_type_physical
        MoveDamageType.STATUS -> R.string.moves_vgc_damage_type_status
        else -> R.string.moves_vgc_unknown
    }
}