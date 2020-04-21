package io.vinicius.parse.coroutines

import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow

class ParseQueryCoroutines<T: ParseObject>(private val parseQuery: ParseQuery<T>)
{
    fun find() = flow {
        try {
            val list = parseQuery.find()
            list.forEach { emit(it) }
        } catch (exp: ParseException) {
            if (exp.code != ParseException.OBJECT_NOT_FOUND) throw exp
        }
    }

    suspend fun get(objectId: String) = coroutineScope {
        try {
            parseQuery.get(objectId)
        } catch (exp: ParseException) {
            when (exp.code) {
                ParseException.OBJECT_NOT_FOUND -> null
                else -> throw exp
            }
        }
    }
}

val <T: ParseObject> ParseQuery<T>.cr: ParseQueryCoroutines<T> get() = ParseQueryCoroutines(this)