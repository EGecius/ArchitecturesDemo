package com.egecius.architecturesdemo.cleanarch.shared

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers

class AndroidSchedulers : Schedulers {

    override fun getExecutionScheduler(): Scheduler {
        return io.reactivex.schedulers.Schedulers.io()
    }

    override fun getPostExecutionScheduler(): Scheduler {
        return AndroidSchedulers.mainThread()
    }
}
