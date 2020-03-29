package com.egecius.architecturesdemo.utils

import com.egecius.architecturesdemo.cleanarch.shared.InteractorSchedulers
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers

class TestInteractorSchedulers : InteractorSchedulers {

    override fun background(): Scheduler {
        return Schedulers.trampoline()
    }

    override fun main(): Scheduler {
        return Schedulers.trampoline()
    }
}
