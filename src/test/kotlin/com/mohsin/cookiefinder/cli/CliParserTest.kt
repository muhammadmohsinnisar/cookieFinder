import com.mohsin.cookiefinder.cli.CliParser
import java.time.LocalDate
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CliParserTest {

    @Test
    fun `parses valid arguments`() {
        val args = arrayOf("-f", "cookie_log_test.csv", "-d", "2018-12-09")
        val result = CliParser.parseArguments(args)
        assertEquals("cookie_log_test.csv", result.fileName)
        assertEquals(LocalDate.parse("2018-12-09"), result.date)
    }

    @Test
    fun `throws exception when -f is missing`() {
        val args = arrayOf("-d", "2018-12-09")
        assertFailsWith<IllegalArgumentException> {
            CliParser.parseArguments(args)
        }
    }

    @Test
    fun `throws exception when -d is missing`() {
        val args = arrayOf("-f", "cookie_log.csv")
        assertFailsWith<IllegalArgumentException> {
            CliParser.parseArguments(args)
        }
    }

    @Test
    fun `throws exception when date is invalid`() {
        val args = arrayOf("-f", "cookie_log.csv", "-d", "2018-13-09")
        assertFailsWith<IllegalArgumentException> {
            CliParser.parseArguments(args)
        }
    }
}