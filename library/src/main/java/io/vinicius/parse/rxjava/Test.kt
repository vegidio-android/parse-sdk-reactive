package io.vinicius.parse.rxjava

import com.parse.GetCallback
import com.parse.ParseObject


class Test
{
    fun blah()
    {
        val gameScore = ParseObject("GameScore")

        gameScore.fetchInBackground(GetCallback<ParseObject?> { `object`, e ->
            if (e == null) {
                // Success!
            } else {
                // Failure!
            }
        })

    }
}