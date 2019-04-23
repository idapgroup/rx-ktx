package com.idapgroup.rx.ktx

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.atomic.AtomicInteger


fun <T> Observable<T>.subscribeIo(): Observable<T> = subscribeOn(Schedulers.io())

fun <T> Observable<T>.subscribeComputation(): Observable<T> = subscribeOn(Schedulers.computation())

fun <T> Observable<T>.subscribeMainThread(): Observable<T> = subscribeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.io(): Observable<T> = observeOn(Schedulers.io())

fun <T> Observable<T>.computation(): Observable<T> = observeOn(Schedulers.computation())

fun <T> Observable<T>.mainThread(): Observable<T> = observeOn(AndroidSchedulers.mainThread())

@Suppress("NOTHING_TO_INLINE")
inline fun <T> Observable<T>.logEvents(tag: Any = ""): Observable<T> {
    var startAt: Long = 0
    val calls = AtomicInteger(0)
    return doOnSubscribe {
        startAt = System.currentTimeMillis()
        logOnSubscribe(tag)
    }
        .doOnNext { logOnNext(tag, calls.incrementAndGet(),  it, startAt) }
        .doOnComplete { logOnComplete(tag, startAt) }
        .doOnError { logOnError(tag, calls.get(), it, startAt) }
        .doOnDispose { logOnDispose(tag, startAt) }
}