package io.vinicius.parse.rxjava

import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import io.reactivex.rxjava3.core.Maybe
import io.reactivex.rxjava3.core.Observable

class RxParseQuery<T: ParseObject>(private val parseQuery: ParseQuery<T>)
{
    fun find() = Observable.create<T> { emitter ->
        try {
            val list = parseQuery.find()
            list.forEach { emitter.onNext(it) }
            emitter.onComplete()
        } catch (exp: ParseException) {
            when (exp.code) {
                ParseException.OBJECT_NOT_FOUND -> emitter.onComplete()
                else -> emitter.onError(exp)
            }
        }
    }

    fun get(objectId: String) = Maybe.create<T> { emitter ->
        try {
            val obj = parseQuery.get(objectId)
            emitter.onSuccess(obj)
        } catch (exp: ParseException) {
            when (exp.code) {
                ParseException.OBJECT_NOT_FOUND -> emitter.onComplete()
                else -> emitter.onError(exp)
            }
        }
    }
}

val <T: ParseObject> ParseQuery<T>.rx: RxParseQuery<T> get() = RxParseQuery(this)