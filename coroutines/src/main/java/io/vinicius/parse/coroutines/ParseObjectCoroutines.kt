package io.vinicius.parse.coroutines

import com.parse.ParseException
import com.parse.ParseObject
import kotlinx.coroutines.coroutineScope

class ParseObjectCoroutines<T: ParseObject>(private val parseObject: ParseObject)
{
    suspend fun fetch() = coroutineScope {
        try {
            parseObject.fetch<T>()
        } catch (exp: ParseException) {
            throw exp
        }
    }

    suspend fun save() = coroutineScope {
        try {
            parseObject.save()
        } catch (exp: ParseException) {
            throw exp
        }
    }
}

val <T: ParseObject> T.cr: ParseObjectCoroutines<T> get() = ParseObjectCoroutines(this)