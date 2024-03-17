package com.cellstudio.core.di.qualifiers

import javax.inject.Qualifier

@Qualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class Dispatcher(val dispatcher: CellDispatchers)

enum class CellDispatchers {
    IO, MAIN, DEFAULT
}
