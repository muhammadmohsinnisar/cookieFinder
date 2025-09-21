import com.mohsin.cookiefinder.parser.Utils
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import java.time.LocalDate

class DateParserTest {

    @Test
    fun `utcDateFromIso should convert +01 offset timestamp to previous UTC date`() {
        val timestamp = "2018-12-10T00:30:00+01:00"
        val expected = LocalDate.parse("2018-12-09")
        val actual = Utils.utcDateFromIso(timestamp)
        assertEquals(expected, actual)
    }

    @Test
    fun `utcDateFromIso should parse Z-suffix and return same UTC date`() {
        val timestamp = "2018-12-09T14:19:00Z"
        val expected = LocalDate.parse("2018-12-09")
        val actual = Utils.utcDateFromIso(timestamp)
        assertEquals(expected, actual)
    }

    @Test
    fun `utcDateFromIso should convert negative offset to next UTC date when applicable`() {
        // 2018-12-10T23:30:00-02:00 -> UTC 2018-12-11T01:30 -> date 2018-12-11
        val timestamp = "2018-12-10T23:30:00-02:00"
        val expected = LocalDate.parse("2018-12-11")
        val actual = Utils.utcDateFromIso(timestamp)
        assertEquals(expected, actual)
    }

    @Test
    fun `utcDateFromIso should handle fractional seconds`() {
        val timestamp = "2018-12-10T00:30:00.123+01:00"
        val expected = LocalDate.parse("2018-12-09")
        val actual = Utils.utcDateFromIso(timestamp)
        assertEquals(expected, actual)
    }

    @Test
    fun `utcDateFromIso should trim whitespace`() {
        val timestamp = " 2018-12-10T00:30:00+01:00 "
        val expected = LocalDate.parse("2018-12-09")
        val actual = Utils.utcDateFromIso(timestamp)
        assertEquals(expected, actual)
    }

    @Test
    fun `utcDateFromIso should return null for empty string`() {
        val actual = Utils.utcDateFromIso("")
        assertNull(actual)
    }

    @Test
    fun `utcDateFromIso should return null for whitespace-only`() {
        val actual = Utils.utcDateFromIso("   \t\n")
        assertNull(actual)
    }

    @Test
    fun `utcDateFromIso should return null for malformed timestamp`() {
        val bad = "not-a-timestamp"
        val actual = Utils.utcDateFromIso(bad)
        assertNull(actual)
    }

    @Test
    fun `utcDateFromIso should return null for null input`() {
        val actual = Utils.utcDateFromIso(null)
        assertNull(actual)
    }
}
