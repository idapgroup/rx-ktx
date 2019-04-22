package com.idapgroup.rx.ktx

import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun Completable.subscribeIo(): Completable = subscribeOn(Schedulers.io())

fun Completable.subscribeComputation(): Completable = subscribeOn(Schedulers.computation())

fun Completable.subscribeMainThread(): Completable = subscribeOn(AndroidSchedulers.mainThread())

fun Completable.io(): Completable = observeOn(Schedulers.io())

fun Completable.computation(): Completable = observeOn(Schedulers.computation())

fun Completable.mainThread(): Completable = observeOn(AndroidSchedulers.mainThread())