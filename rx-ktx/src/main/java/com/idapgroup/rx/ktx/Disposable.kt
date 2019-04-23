package com.idapgroup.rx.ktx

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import io.reactivex.disposables.Disposable

fun Disposable.disposeOnPause(lifecycleOwner: LifecycleOwner) =
    disposeOn(lifecycleOwner, Lifecycle.Event.ON_PAUSE)


fun Disposable.disposeOnStop(lifecycleOwner: LifecycleOwner) =
    disposeOn(lifecycleOwner, Lifecycle.Event.ON_STOP)


fun Disposable.disposeOnDestroy(lifecycleOwner: LifecycleOwner) =
    disposeOn(lifecycleOwner, Lifecycle.Event.ON_DESTROY)


fun Disposable.disposeOn(lifecycleOwner: LifecycleOwner, disposeEvent: Lifecycle.Event): Disposable {
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
                this@disposeOn.dispose()
            }
        }

    }
    lifecycleOwner.lifecycle.addObserver(observer)
    return this
}


