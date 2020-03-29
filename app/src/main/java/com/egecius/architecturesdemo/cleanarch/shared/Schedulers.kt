package com.egecius.architecturesdemo.cleanarch.shared

import io.reactivex.Scheduler

interface Schedulers {
    fun getExecutionScheduler(): Scheduler
    fun getPostExecutionScheduler(): Scheduler
}
