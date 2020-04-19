package com.egecius.architecturesdemo.utils

import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job

fun CoroutineScope.log(msg: String) {
    val threadName = Thread.currentThread().name
    val job = println(coroutineContext[Job])
    val fullMsg = "thread: $threadName; job: $job, msg: $msg"
    println(fullMsg)
}