package com.idapgroup.rx.ktx

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.atomic.AtomicInteger

fun <T> Flowable<T>.subscribeIo(): Flowable<T> = subscribeOn(Schedulers.io())

fun <T> Flowable<T>.subscribeComputation(): Flowable<T> = subscribeOn(Schedulers.computation())

fun <T> Flowable<T>.subscribeMainThread(): Flowable<T> = subscribeOn(AndroidSchedulers.mainThread())

fun <T> Flowable<T>.io(): Flowable<T> = observeOn(Schedulers.io())

fun <T> Flowable<T>.computation(): Flowable<T> = observeOn(Schedulers.computation())

fun <T> Flowable<T>.mainThread(): Flowable<T> = observeOn(AndroidSchedulers.mainThread())

@Suppress("NOTHING_TO_INLINE")
inline fun <T> Flowable<T>.debug(tag: Any = ""): Flowable<T> {
    var startAt: Long = 0
    val calls = AtomicInteger(0)
    return doOnSubscribe {
        startAt = System.currentTimeMillis()
        logOnSubscribe(tag)
    }
        .doOnNext { logOnNext(tag, calls.incrementAndGet(), it, startAt) }
        .doOnComplete { logOnComplete(tag, startAt) }
        .doOnError { logOnError(tag, calls.get(), it, startAt) }
        .doOnCancel { logOnCancel(tag, startAt) }
}