import com.mohsin.cookiefinder.model.CookieEntry
import com.mohsin.cookiefinder.parser.CookieLogParser
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.OffsetDateTime
import kotlin.test.Test

class CookieLogParserTest {

    @Test
    fun `parses a single cookie log line`() {
        val line = "AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00"
        val parser = CookieLogParser()
        val result = parser.parseLine(line)
        assertEquals(
            CookieEntry("AtY0laUfhglK3lC7", OffsetDateTime.parse("2018-12-09T14:19:00+00:00")),
            result
        )
    }

    @Test
    fun `parses multiple lines`() {
        val csv = """
        cookie,timestamp
        AtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00
        SAZuXPGUrfbcn5UA,2018-12-09T10:13:00+00:00
        5UAVanZf6UtGyKVS,2018-12-09T07:25:00+00:00
        AtY0laUfhglK3lC7,2018-12-09T06:19:00+00:00
        SAZuXPGUrfbcn5UA,2018-12-08T22:03:00+00:00
        4sMM2LxV07bPJzwf,2018-12-08T21:30:00+00:00
        fbcn5UAVanZf6UtG,2018-12-08T09:30:00+00:00
        4sMM2LxV07bPJzwf,2018-12-07T23:30:00+00:00
    """.trimIndent()

        val parser = CookieLogParser()
        val result = parser.parse(csv)

        assertEquals(
            listOf(
                CookieEntry("AtY0laUfhglK3lC7", OffsetDateTime.parse("2018-12-09T14:19:00+00:00")),
                CookieEntry("SAZuXPGUrfbcn5UA", OffsetDateTime.parse("2018-12-09T10:13:00+00:00")),
                CookieEntry("5UAVanZf6UtGyKVS", OffsetDateTime.parse("2018-12-09T07:25:00+00:00")),
                CookieEntry("AtY0laUfhglK3lC7", OffsetDateTime.parse("2018-12-09T06:19:00+00:00")),
                CookieEntry("SAZuXPGUrfbcn5UA", OffsetDateTime.parse("2018-12-08T22:03:00+00:00")),
                CookieEntry("4sMM2LxV07bPJzwf", OffsetDateTime.parse("2018-12-08T21:30:00+00:00")),
                CookieEntry("fbcn5UAVanZf6UtG", OffsetDateTime.parse("2018-12-08T09:30:00+00:00")),
                CookieEntry("4sMM2LxV07bPJzwf", OffsetDateTime.parse("2018-12-07T23:30:00+00:00"))
            ),
            result
        )
    }
}
