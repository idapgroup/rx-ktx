package com.idapgroup.rx.ktx

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import io.reactivex.Observable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DisposeWhenTest {

    private val owner = Owner()
    private val disposable = Observable.never<Int>().subscribe()

    @Test
    fun testDisposeOnPause() {
        disposable.disposeOnPause(owner)
        owner.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        assertFalse(disposable.isDisposed)
        owner.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        assertTrue(disposable.isDisposed)

    }

    @Test
    fun testDisposeOnStop() {
        disposable.disposeOnStop(owner)
        owner.handleLifecycleEvent(Lifecycle.Event.ON_START)
        assertFalse(disposable.isDisposed)
        owner.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        assertTrue(disposable.isDisposed)
    }

    @Test
    fun testDisposeOnDestroy() {
        disposable.disposeOnDestroy(owner)
        owner.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        assertFalse(disposable.isDisposed)
        owner.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        assertTrue(disposable.isDisposed)
    }

}


class Owner : LifecycleOwner {

    private val _lifecycle = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle = _lifecycle

    fun handleLifecycleEvent(event: Lifecycle.Event) {
        _lifecycle.handleLifecycleEvent(event)
    }

}