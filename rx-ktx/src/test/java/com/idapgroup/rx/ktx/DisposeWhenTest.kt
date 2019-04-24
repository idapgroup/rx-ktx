package com.idapgroup.rx.ktx

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import io.reactivex.Observable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class DisposeWhenTest {

    private val lifecycleOwner = TestLifecycleOwner()
    private val disposable = Observable.never<Int>().subscribe()

    @Test
    fun testDisposeOnPause() {
        disposable.disposeOnPause(lifecycleOwner)
        lifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_RESUME)
        assertFalse(disposable.isDisposed)
        lifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
        assertTrue(disposable.isDisposed)
    }

    @Test
    fun testDisposeOnStop() {
        disposable.disposeOnStop(lifecycleOwner)
        lifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_START)
        assertFalse(disposable.isDisposed)
        lifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_STOP)
        assertTrue(disposable.isDisposed)
    }

    @Test
    fun testDisposeOnDestroy() {
        disposable.disposeOnDestroy(lifecycleOwner)
        lifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        assertFalse(disposable.isDisposed)
        lifecycleOwner.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        assertTrue(disposable.isDisposed)
    }

}


class TestLifecycleOwner : LifecycleOwner {

    private val _lifecycle = LifecycleRegistry(this)

    override fun getLifecycle(): Lifecycle = _lifecycle

    fun handleLifecycleEvent(event: Lifecycle.Event) {
        _lifecycle.handleLifecycleEvent(event)
    }

}