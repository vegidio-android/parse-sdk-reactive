package io.vinicius.parse.common.model

import com.parse.ParseClassName
import com.parse.ParseObject
import com.parse.ktx.delegates.attribute
import java.util.Date

@ParseClassName("Show")
class Show: ParseObject()
{
    var imdbId: String by attribute()
    var title: String by attribute()
    var releaseDate: Date by attribute()
}