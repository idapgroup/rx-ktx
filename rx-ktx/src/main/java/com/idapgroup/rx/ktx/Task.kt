package com.idapgroup.rx.ktx

import com.google.android.gms.tasks.Task
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Single

fun <R> completableOf(newTask: () -> Task<R>): Completable {
    return maybeOf(newTask).ignoreElement()
}

fun <R> singleOf(newTask: () -> Task<R>): Single<R> {
    return maybeOf(newTask).toSingle()
}

fun <R> maybeOf(newTask: () -> Task<R>): Maybe<R> {
    return Maybe.create { emitter ->
        newTask().addOnCompleteListener {
            if (it.isSuccessful) {
                if(it.result != null) {
                    emitter.onSuccess(it.result!!)
                } else {
                    emitter.onComplete()
                }
            } else {
                emitter.onError(it.exception!!)
            }
        }
    }
}
