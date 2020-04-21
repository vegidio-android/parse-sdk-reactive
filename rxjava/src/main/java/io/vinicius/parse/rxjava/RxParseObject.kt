package io.vinicius.parse.rxjava

import com.parse.ParseException
import com.parse.ParseObject
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

class RxParseObject<T: ParseObject>(private val parseObject: ParseObject)
{
    fun fetch() = Single.create<T> { emitter ->
        try {
            val obj = parseObject.fetch<T>()
            emitter.onSuccess(obj)
        } catch (exp: ParseException) {
            emitter.onError(exp)
        }
    }

    fun save() = Completable.create { emitter ->
        try {
            parseObject.save()
            emitter.onComplete()
        } catch (exp: ParseException) {
            emitter.onError(exp)
        }
    }
}

val <T: ParseObject> T.rx: RxParseObject<T> get() = RxParseObject(this)