package io.vinicius.parse.coroutines

import com.parse.ParseException
import com.parse.ParseObject
import kotlinx.coroutines.coroutineScope

suspend fun <T: ParseObject> T.suspendedFetch() = coroutineScope {
    try {
        this@suspendedFetch.fetch<T>()
    } catch (exp: ParseException) {
        throw exp
    }
}

suspend fun ParseObject.suspendedSave() = coroutineScope {
    try {
        this@suspendedSave.save()
    } catch (exp: ParseException) {
        throw exp
    }
}