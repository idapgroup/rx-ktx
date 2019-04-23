@file:Suppress("NOTHING_TO_INLINE")
package com.idapgroup.rx.ktx

import timber.log.Timber

@PublishedApi
internal inline fun getPassedTime(startAt: Long): Long {
    return System.currentTimeMillis() - startAt
}

@PublishedApi
internal inline fun logOnSubscribe(tag: Any) {
    Timber.d("%s.onSubscribe() thread: %s", tag, Thread.currentThread().name)
}

@PublishedApi
internal inline fun logOnNext(tag: Any, calls: Int, value: Any?, startAt: Long) {
    Timber.d("%s [%d]-> value: %s, passed time: %d ms, thread: %s", tag, calls, value, getPassedTime(startAt), Thread.currentThread().name)
}

@PublishedApi
internal inline fun logOnSuccess(tag: Any, value: Any?, startAt: Long) {
    Timber.d("%s -> value: %s, passed time: %d ms, thread: %s", tag, value, getPassedTime(startAt), Thread.currentThread().name)
}

@PublishedApi
internal inline fun logOnComplete(tag: Any, startAt: Long) {
    Timber.d("%s.onComplete() passed time: %d ms, thread: %s", tag, getPassedTime(startAt), Thread.currentThread().name)
}

@PublishedApi
internal inline fun logOnDispose(tag: Any, startAt: Long) {
    Timber.d("%s.onDispose() passed time: %d ms, thread: %s", tag, getPassedTime(startAt), Thread.currentThread().name)
}

@PublishedApi
internal inline fun logOnCancel(tag: Any, startAt: Long) {
    Timber.d("%s.onCancel() passed time: %d ms, thread: %s", tag, getPassedTime(startAt), Thread.currentThread().name)
}

@PublishedApi
internal inline fun logOnError(tag: Any, steps: Int, error: Throwable, startAt: Long) {
    Timber.e(error, "%s [%d]-x passed time: %d ms, thread: %s", tag, steps, getPassedTime(startAt), Thread.currentThread().name)
}


@PublishedApi
internal inline fun logOnError(tag: Any, error: Throwable, startAt: Long) {
    Timber.e(error, "%s.onError() passed time: %d ms, thread: %s", tag, getPassedTime(startAt), Thread.currentThread().name)
}

