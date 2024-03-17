package com.cellstudio.vgc.domain.models

data class MoveDetails(
    val id: Int,
    val name: String,
    val accuracy: Int,
    val power: Int,
    val type: MoveType,
    val damageType: MoveDamageType
)