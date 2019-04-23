package com.idapgroup.rx.ktx

import com.google.android.gms.tasks.Task
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

private const val TAG = "FirebaseExt"
@Suppress("SimplifyBooleanWithConstants")
private val DEBUG = true && BuildConfig.DEBUG


fun <R> completableOf(task: () -> Task<R>): Completable {
    return maybeOf(task).ignoreElement()
}

fun <R> singleOf(task: () -> Task<R>): Single<R> {
    return maybeOf(task).toSingle()
}

fun <R> maybeOf(task: () -> Task<R>): Maybe<R> {
    return Maybe.create { emitter ->
        task().addOnCompleteListener {
            if (it.isSuccessful) {
                if(it.result == null) {
                    emitter.onComplete()
                } else {
                    emitter.onSuccess(it.result!!)
                }
            } else {
                emitter.onError(it.exception!!)
            }
        }
    }
}
