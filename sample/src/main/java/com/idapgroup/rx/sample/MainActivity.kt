package com.idapgroup.rx.sample

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.idapgroup.rx.ktx.disposeOnPause
import com.idapgroup.rx.ktx.disposeOnStop
import com.idapgroup.rx.ktx.logEvents
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

@SuppressLint("SetTextI18n")
class MainActivity : AppCompatActivity() {

    private var withError = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Timber.plant(object : Timber.Tree() {

            override fun d(t: Throwable) {
                log(Log.DEBUG, null as String?, "", t)
            }

            override fun d(message: String, vararg args: Any) {
                log(Log.DEBUG, null as String?, formatMessage(message, args), null)
            }

            override fun d(t: Throwable, message: String, vararg args: Any) {
                log(Log.DEBUG, null as String?, formatMessage(message, args), t)
            }

            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                val errorText = t?.let { ", error: $it" } ?: ""
                val line = "${tag.orEmpty()}.$message$errorText"
                logView.text = "${logView.text}\n->$line"
            }
        })
    }

    override fun onStart() {
        super.onStart()
        startWork()
    }

    fun startWork() {
        Timber.d("-----------------------------------------------------")

        val subject = BehaviorSubject.createDefault(1)
        subject.logEvents("MyTag")
            .subscribe({}, {})
            .disposeOnStop(this)

        subject.onNext(5)
        subject.onNext(670)
        subject.onNext(45)
        subject.onNext(-39)
        if (withError) {
            subject.onError(Exception())
        }

        withError = !withError
    }
}
