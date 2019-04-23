//package com.idapgroup.rx.ktx
//
//import android.os.Bundle
//import androidx.fragment.app.FragmentActivity
//import androidx.lifecycle.Lifecycle
//import androidx.lifecycle.LifecycleRegistry
//import androidx.test.filters.LargeTest
//import androidx.test.rule.ActivityTestRule
//import androidx.test.runner.AndroidJUnit4
//import io.reactivex.subjects.BehaviorSubject
//import org.junit.Rule
//import org.junit.Test
//import org.junit.runner.RunWith
//
//@RunWith(AndroidJUnit4::class)
//@LargeTest
//class DisposeWhenTest {
//
//    @get:Rule
//    val rule = ActivityTestRule(ExampleActivity::class.java, false, true)
//
//    @Test
//    fun test() {
//        val lifecycle = LifecycleRegistry { null!! }
//        lifecycle.handleLifecycleEvent(Lifecycle.Event.ON_PAUSE)
//    }
//
//}
//
//class ExampleActivity : FragmentActivity() {
//
//    val stopSubject = BehaviorSubject.createDefault(0)
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//
//    }
//
//}