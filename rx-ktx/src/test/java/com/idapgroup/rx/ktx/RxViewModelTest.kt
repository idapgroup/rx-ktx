package com.idapgroup.rx.ktx

import io.reactivex.Observable
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class RxViewModelTest {

    @Test
    fun testAutoDisposeOnRxViewModel() {
        val viewModel = TestRxViewModel()
        assertFalse(viewModel.testDisposable.isDisposed)
        viewModel.callOnCleared()
        assertTrue(viewModel.testDisposable.isDisposed)
    }

}

class TestRxViewModel : RxViewModel() {
    val testDisposable =
        Observable.never<Int>()
            .subscribe()
            .autoDispose()

    fun callOnCleared() {
        onCleared()
    }
}