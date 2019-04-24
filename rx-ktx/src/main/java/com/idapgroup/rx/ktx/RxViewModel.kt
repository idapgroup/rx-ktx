package com.idapgroup.rx.ktx

import androidx.lifecycle.ViewModel

open class RxViewModel : ViewModel(), AutoDisposable by AutoDisposableImpl() {

    override fun onCleared() {
        dispose()
        super.onCleared()
    }

}