package com.cellstudio.ui.utilities

import androidx.compose.ui.Modifier

fun Modifier.thenIf(condition: Boolean, modifier: Modifier.() -> Modifier) =
    if (condition) { then(modifier(Modifier)) } else { this }