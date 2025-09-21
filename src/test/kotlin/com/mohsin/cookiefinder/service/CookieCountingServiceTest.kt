import com.mohsin.cookiefinder.model.CookieEntry
import com.mohsin.cookiefinder.service.CookieCountingService
import org.junit.jupiter.api.Assertions.assertEquals
import java.time.LocalDate
import java.time.OffsetDateTime
import kotlin.test.Test

class CookieCountingServiceTest {
    @Test
    fun `finds most active cookie for a given date`() {
        val entries = listOf(
            CookieEntry("AtY0laUfhglK3lC7", OffsetDateTime.parse("2018-12-09T14:19:00+00:00")),
            CookieEntry("SAZuXPGUrfbcn5UA", OffsetDateTime.parse("2018-12-09T10:13:00+00:00")),
            CookieEntry("5UAVanZf6UtGyKVS", OffsetDateTime.parse("2018-12-09T07:25:00+00:00")),
            CookieEntry("AtY0laUfhglK3lC7", OffsetDateTime.parse("2018-12-09T06:19:00+00:00"))
        )

        val service = CookieCountingService()
        val result = service.findMostActiveCookies(entries, LocalDate.parse("2018-12-09"))

        assertEquals(listOf("AtY0laUfhglK3lC7"), result)
    }

    @Test
    fun `returns all cookies if multiple tie for max count`() {
        val entries = listOf(
            CookieEntry("cookie1", OffsetDateTime.parse("2018-12-09T14:19:00+00:00")),
            CookieEntry("cookie2", OffsetDateTime.parse("2018-12-09T10:13:00+00:00")),
            CookieEntry("cookie1", OffsetDateTime.parse("2018-12-09T07:25:00+00:00")),
            CookieEntry("cookie2", OffsetDateTime.parse("2018-12-09T06:19:00+00:00"))
        )

        val service = CookieCountingService()
        val result = service.findMostActiveCookies(entries, LocalDate.parse("2018-12-09"))

        assertEquals(setOf("cookie1", "cookie2"), result.toSet())
    }

    @Test
    fun `returns empty list when no entries exist for target date`() {
        val entries = listOf(
            CookieEntry("cookie1", OffsetDateTime.parse("2018-12-08T14:19:00+00:00")),
            CookieEntry("cookie2", OffsetDateTime.parse("2018-12-08T10:13:00+00:00"))
        )

        val service = CookieCountingService()
        val result = service.findMostActiveCookies(entries, LocalDate.parse("2018-12-09"))

        assertEquals(emptyList<String>(), result)
    }

    @Test
    fun `returns empty list when input list is empty`() {
        val entries = emptyList<CookieEntry>()

        val service = CookieCountingService()
        val result = service.findMostActiveCookies(entries, LocalDate.parse("2018-12-09"))

        assertEquals(emptyList<String>(), result)
    }

    @Test
    fun `finds most active cookie using task sample data`() {
        val entries = listOf(
            CookieEntry("AtY0laUfhglK3lC7", OffsetDateTime.parse("2018-12-09T14:19:00+00:00")),
            CookieEntry("SAZuXPGUrfbcn5UA", OffsetDateTime.parse("2018-12-09T10:13:00+00:00")),
            CookieEntry("5UAVanZf6UtGyKVS", OffsetDateTime.parse("2018-12-09T07:25:00+00:00")),
            CookieEntry("AtY0laUfhglK3lC7", OffsetDateTime.parse("2018-12-09T06:19:00+00:00")),
            CookieEntry("SAZuXPGUrfbcn5UA", OffsetDateTime.parse("2018-12-08T22:03:00+00:00")),
            CookieEntry("4sMM2LxV07bPJzwf", OffsetDateTime.parse("2018-12-08T21:30:00+00:00")),
            CookieEntry("fbcn5UAVanZf6UtG", OffsetDateTime.parse("2018-12-08T09:30:00+00:00")),
            CookieEntry("4sMM2LxV07bPJzwf", OffsetDateTime.parse("2018-12-07T23:30:00+00:00"))
        )

        val service = CookieCountingService()
        val result = service.findMostActiveCookies(entries, LocalDate.parse("2018-12-09"))

        assertEquals(listOf("AtY0laUfhglK3lC7"), result)
    }
}