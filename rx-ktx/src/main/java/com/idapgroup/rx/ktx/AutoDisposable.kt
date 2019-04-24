package com.idapgroup.rx.ktx

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.internal.disposables.DisposableContainer

interface AutoDisposable :  DisposableContainer {

    fun Disposable.autoDispose() : Disposable

    fun cancel()
    fun dispose()
}

class AutoDisposableImpl : AutoDisposable {

    private val disposable = CompositeDisposable()

    override fun Disposable.autoDispose() : Disposable {
        disposable.add(this)
        return this
    }

    override fun dispose() {
        disposable.dispose()
    }

    override fun cancel() {
        disposable.clear()
    }

    override fun add(d: Disposable): Boolean {
        return disposable.add(d)
    }

    override fun remove(d: Disposable): Boolean {
        return disposable.remove(d)
    }

    override fun delete(d: Disposable): Boolean {
        return disposable.delete(d)
    }
}
