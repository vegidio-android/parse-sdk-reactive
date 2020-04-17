package io.vinicius.parse.common.model

import com.parse.ParseClassName
import com.parse.ParseObject
import java.util.Date

@ParseClassName("Show")
class Show: ParseObject()
{
    var imdbId: String by ParseDelegate()
    var title: String by ParseDelegate()
    var releaseDate: Date by ParseDelegate()
}