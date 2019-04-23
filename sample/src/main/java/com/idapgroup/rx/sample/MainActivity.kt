package com.idapgroup.rx.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.idapgroup.rx.ktx.logEvents
import io.reactivex.subjects.BehaviorSubject
import timber.log.Timber

class MainActivity : AppCompatActivity() {
    val subject = BehaviorSubject.createDefault(1)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(Timber.DebugTree())

        subject.logEvents("MyTag")
            .subscribe()

        subject.onNext(2)
        subject.onNext(20)
        subject.onNext(21)
        subject.onNext(24)
        subject.onNext(12)
        subject.onNext(212)
        subject.onError(Exception())
        subject.onError(Exception())
        subject.onError(Exception())
    }
}
