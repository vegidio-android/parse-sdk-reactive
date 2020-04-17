package io.vinicius.parse.coroutines

import android.content.Context
import android.os.Build
import androidx.test.core.app.ApplicationProvider
import com.parse.Parse
import com.parse.ParseObject
import com.parse.ParseQuery
import io.vinicius.parse.common.ext.toDate
import io.vinicius.parse.common.model.Show
import kotlinx.coroutines.async
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import java.time.LocalDate

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.P])
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class ParseQueryCoroutinesTest
{
    @Before
    fun `Initializing Parse`()
    {
        val context = ApplicationProvider.getApplicationContext<Context>()
        ParseObject.registerSubclass(Show::class.java)

        Parse.initialize(Parse.Configuration.Builder(context)
            .server("https://parse.vinicius.io/app/common")
            .applicationId("common")
            .build())
    }

    @Test
    fun `1- Get a ParseObject by objectId`()
    {
        val show = Show().apply {
            imdbId = "tt0903747"
            title = "Breaking Bad"
            releaseDate = LocalDate.of(2008, 1, 20).toDate("UTC")
        }

        runBlockingTest {
            show.suspendedSave()

            val query = ParseQuery.getQuery(Show::class.java)
            val result = query.suspendedGet(show.objectId)

            Assert.assertEquals(result?.imdbId, show.imdbId)
        }
    }

    @Test
    fun `2- Find ParseObjects with title containing "S2"`()
    {
        val episodes = listOf(1, 1, 2, 2, 3).mapIndexed { index, season ->
            Show().apply {
                imdbId = "tt3032476"
                title = "Better Call Saul S${season}E${index + 1}"
                releaseDate = LocalDate.of(2015, 2, 8).toDate("UTC")
            }
        }

        runBlockingTest {
            val job = episodes.map { async { it.suspendedSave() } }
            job.forEach { it.await() }

            val query = ParseQuery.getQuery(Show::class.java)
            query.whereContains("title", "S2")

            val collector = TestCollector.test(this, query.findFlow())
            collector.assertNoErrors()
            collector.assertValueCount(2)
        }
    }
}