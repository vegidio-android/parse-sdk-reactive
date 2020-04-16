package io.vinicius.parse.rxjava

import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

fun <T: ParseObject> ParseObject.rxFetch() = Single.create<T> { emitter ->
    try {
        val obj = this.fetch<T>()
        emitter.onSuccess(obj)
    } catch (exp: ParseException) {
        emitter.onError(exp)
    }
}

fun <T: ParseObject> ParseQuery<T>.rxGet(objectId: String) = Single.create<T> { emitter ->
    try {
        val obj = this.get(objectId)
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