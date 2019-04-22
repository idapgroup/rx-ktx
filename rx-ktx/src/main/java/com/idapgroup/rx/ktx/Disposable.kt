package com.idapgroup.rx.ktx

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import io.reactivex.disposables.Disposable


fun Disposable.disposeWhen(lifecycleOwner: LifecycleOwner, disposeEvent: Lifecycle.Event) {
    val observer = object : DisposeObserver {
        override fun onPause() {
            checkDispose(Lifecycle.Event.ON_PAUSE)
        }

        override fun onStop() {
            checkDispose(Lifecycle.Event.ON_STOP)
        }

        override fun onDestroy() {
            checkDispose(Lifecycle.Event.ON_DESTROY)
        }

        override fun checkDispose(currentEvent: Lifecycle.Event) {
            if (currentEvent == disposeEvent) {
                lifecycleOwner.lifecycle.removeObserver(this)
                this@disposeWhen.dispose()
            }
        }

    }
    lifecycleOwner.lifecycle.addObserver(observer)
}

fun Disposable.disposeWhenPause(lifecycleOwner: LifecycleOwner) {
    disposeWhen(lifecycleOwner, Lifecycle.Event.ON_DESTROY)
}

fun Disposable.disposeWhenStop(lifecycleOwner: LifecycleOwner) {
    disposeWhen(lifecycleOwner, Lifecycle.Event.ON_DESTROY)
}

fun Disposable.disposeWhenDestroy(lifecycleOwner: LifecycleOwner) {
    disposeWhen(lifecycleOwner, Lifecycle.Event.ON_DESTROY)
}


