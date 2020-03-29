package com.egecius.architecturesdemo.cleanarch.shared

import io.reactivex.*


fun <T> Observable<T>.on(schedulers: InteractorSchedulers): Observable<T> {
    return subscribeOn(schedulers.background())
        .observeOn(schedulers.main())
}

fun Completable.on(schedulers: InteractorSchedulers): Completable {
    return subscribeOn(schedulers.background())
        .observeOn(schedulers.main())
}

fun <T> Maybe<T>.on(schedulers: InteractorSchedulers): Maybe<T> {
    return subscribeOn(schedulers.background())
        .observeOn(schedulers.main())
}

fun <T> Single<T>.on(schedulers: InteractorSchedulers): Single<T> {
    return subscribeOn(schedulers.background())
        .observeOn(schedulers.main())
}
