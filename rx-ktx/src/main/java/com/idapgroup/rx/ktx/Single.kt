package com.idapgroup.rx.ktx

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.subscribeIo(): Single<T> = subscribeOn(Schedulers.io())

fun <T> Single<T>.subscribeComputation(): Single<T> = subscribeOn(Schedulers.computation())

fun <T> Single<T>.subscribeMainThread(): Single<T> = subscribeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.io(): Single<T> = observeOn(Schedulers.io())

fun <T> Single<T>.computation(): Single<T> = observeOn(Schedulers.computation())

fun <T> Single<T>.mainThread(): Single<T> = observeOn(AndroidSchedulers.mainThread())

@Suppress("NOTHING_TO_INLINE")
inline fun <T> Single<T>.logEvents(tag: Any = ""): Single<T> {
    var startAt: Long = 0
    return doOnSubscribe {
        startAt = System.currentTimeMillis()
        logOnSubscribe(tag)
    }
        .doOnSuccess { logOnSuccess(tag, it, startAt) }
        .doOnError { logOnError(tag, it, startAt) }
        .doOnDispose { logOnDispose(tag, startAt) }
}