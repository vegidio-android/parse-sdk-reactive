package io.vinicius.parse.coroutines

import com.parse.ParseException
import com.parse.ParseObject
import com.parse.ParseQuery
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.flow

suspend fun <T: ParseObject> ParseQuery<T>.suspendedGet(objectId: String) = coroutineScope {
    try {
        this@suspendedGet.get(objectId)
    } catch (exp: ParseException) {
        when (exp.code) {
            ParseException.OBJECT_NOT_FOUND -> null
            else -> throw exp
        }
    }
}

fun <T: ParseObject> ParseQuery<T>.findFlow() = flow {
    try {
        val list = this@findFlow.find()
        list.forEach { emit(it) }
    } catch (exp: ParseException) {
        if (exp.code != ParseException.OBJECT_NOT_FOUND) throw exp
    }
}