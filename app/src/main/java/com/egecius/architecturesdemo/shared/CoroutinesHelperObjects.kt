package com.egecius.architecturesdemo.shared

import kotlinx.coroutines.CoroutineExceptionHandler
import kotlin.coroutines.CoroutineContext

val emptyHandler = CoroutineExceptionHandler(object : (CoroutineContext, Throwable) -> Unit {

    override fun invoke(coroutineContext: CoroutineContext, throwable: Throwable) {
        // no-op
    }
})