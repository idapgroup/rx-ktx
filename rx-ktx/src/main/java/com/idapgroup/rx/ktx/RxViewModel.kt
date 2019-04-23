package com.idapgroup.rx.ktx

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

open class RxViewModel : ViewModel() {

    private val disposable: CompositeDisposable = CompositeDisposable()

    fun Disposable.disposeOnCleared() {
        disposable.add(this)
    }

    override fun onCleared() {
        disposable.dispose()
        super.onCleared()
    }

}