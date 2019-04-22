package com.idapgroup.rx.ktx

import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Flowable<T>.subscribeIo(): Flowable<T> = subscribeOn(Schedulers.io())

fun <T> Flowable<T>.subscribeComputation(): Flowable<T> = subscribeOn(Schedulers.computation())

fun <T> Flowable<T>.subscribeMainThread(): Flowable<T> = subscribeOn(AndroidSchedulers.mainThread())

fun <T> Flowable<T>.io(): Flowable<T> = observeOn(Schedulers.io())

fun <T> Flowable<T>.computation(): Flowable<T> = observeOn(Schedulers.computation())

fun <T> Flowable<T>.mainThread(): Flowable<T> = observeOn(AndroidSchedulers.mainThread())