package com.idapgroup.rx.ktx

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun <T> Observable<T>.subscribeIo(): Observable<T> = subscribeOn(Schedulers.io())

fun <T> Observable<T>.subscribeComputation(): Observable<T> = subscribeOn(Schedulers.computation())

fun <T> Observable<T>.subscribeMainThread(): Observable<T> = subscribeOn(AndroidSchedulers.mainThread())

fun <T> Observable<T>.io(): Observable<T> = observeOn(Schedulers.io())

fun <T> Observable<T>.computation(): Observable<T> = observeOn(Schedulers.computation())

fun <T> Observable<T>.mainThread(): Observable<T> = observeOn(AndroidSchedulers.mainThread())