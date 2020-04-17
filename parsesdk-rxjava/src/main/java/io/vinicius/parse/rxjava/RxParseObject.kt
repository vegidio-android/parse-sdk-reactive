package io.vinicius.parse.rxjava

import com.parse.ParseException
import com.parse.ParseObject
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

fun <T: ParseObject> T.rxFetch() = Single.create<T> { emitter ->
    try {
        val obj = this.fetch<T>()
        emitter.onSuccess(obj)
    } catch (exp: ParseException) {
        emitter.onError(exp)
    }
}

fun ParseObject.rxSave() = Completable.create { emitter ->
    try {
        this.save()
        emitter.onComplete()
    } catch (exp: ParseException) {
        emitter.onError(exp)
    }
}