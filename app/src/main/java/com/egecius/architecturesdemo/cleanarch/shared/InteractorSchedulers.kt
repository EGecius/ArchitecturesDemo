package com.egecius.architecturesdemo.cleanarch.shared

import io.reactivex.Scheduler

interface InteractorSchedulers {

    fun background(): Scheduler

    fun main(): Scheduler
}
