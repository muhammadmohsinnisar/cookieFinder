import com.mohsin.cookiefinder.service.FileReaderService
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith
import kotlin.test.assertTrue

class FileReaderServiceTest {

    private val service = FileReaderService()

    @Test
    fun `reads CSV from resources`() {
        val resourceUrl = this::class.java.classLoader.getResource("cookie_log_test.csv")
        require(resourceUrl != null) { "Test resource not found: cookie_log_test.csv" }

        val path = resourceUrl.file
        val content = service.readFile(path)

        assertTrue(content.contains("AtY0laUfhglK3lC7"), "Content should contain cookie AtY0laUfhglK3lC7")
        assertTrue(content.contains("SAZuXPGUrfbcn5UA"), "Content should contain cookie SAZuXPGUrfbcn5UA")
    }

    @Test
    fun `reads file successfully`() {
        val tempFile = createTempFile("test_file", ".csv").apply {
            writeText("cookie,timestamp\nAtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00")
        }

        val content = service.readFile(tempFile.absolutePath)

        assertEquals(
            "cookie,timestamp\nAtY0laUfhglK3lC7,2018-12-09T14:19:00+00:00",
            content
        )

        tempFile.delete()
    }

    @Test
    fun `throws exception when file does not exist`() {
        assertFailsWith<IllegalArgumentException>("Should throw when file is missing") {
            service.readFile("non_existing_file.csv")
        }
    }

    @Test
    fun `reads empty file as empty string`() {
        val tempFile = createTempFile("empty_file", ".csv")
        val content = service.readFile(tempFile.absolutePath)

        assertEquals("", content, "Empty file should return empty string")
        tempFile.delete()
    }
}
