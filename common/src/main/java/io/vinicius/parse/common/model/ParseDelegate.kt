package io.vinicius.parse.common.model

import com.parse.ParseObject
import kotlin.reflect.KProperty

class ParseDelegate<T>
{
    operator fun getValue(parseObj: ParseObject, property: KProperty<*>): T {
        return parseObj.get(property.name) as T
    }

    operator fun setValue(parseObj: ParseObject, property: KProperty<*>, value: Any) {
        parseObj.put(property.name, value)
    }
}