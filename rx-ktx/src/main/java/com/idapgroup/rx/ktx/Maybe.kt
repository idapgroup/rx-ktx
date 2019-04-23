package com.idapgroup.rx.ktx

import io.reactivex.Maybe
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Maybe<T>.subscribeIo(): Maybe<T> = subscribeOn(Schedulers.io())

fun <T> Maybe<T>.subscribeComputation(): Maybe<T> = subscribeOn(Schedulers.computation())

fun <T> Maybe<T>.subscribeMainThread(): Maybe<T> = subscribeOn(AndroidSchedulers.mainThread())

fun <T> Maybe<T>.io(): Maybe<T> = observeOn(Schedulers.io())

fun <T> Maybe<T>.computation(): Maybe<T> = observeOn(Schedulers.computation())

fun <T> Maybe<T>.mainThread(): Maybe<T> = observeOn(AndroidSchedulers.mainThread())

@Suppress("NOTHING_TO_INLINE")
inline fun <T> Maybe<T>.logEvents(tag: Any = ""): Maybe<T> {
    var startAt: Long = 0
    return doOnSubscribe {
        startAt = System.currentTimeMillis()
        logOnSubscribe(tag)
    }
        .doOnSuccess { logOnSuccess(tag, it, startAt) }
        .doOnComplete { logOnComplete(tag, startAt) }
        .doOnError { logOnError(tag, it, startAt) }
        .doOnDispose { logOnDispose(tag, startAt) }
}