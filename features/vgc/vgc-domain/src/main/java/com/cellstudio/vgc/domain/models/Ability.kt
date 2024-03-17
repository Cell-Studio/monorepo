package com.cellstudio.vgc.domain.models

data class Ability(
    val id: Int,
    val name: String,
    val isHidden: Boolean,
    val description: String
)