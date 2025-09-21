import com.mohsin.cookiefinder.main
import java.io.File
import kotlin.test.Test
import kotlin.test.assertTrue

class IntegrationTest {

    @Test
    fun `test integration with sample csv`(){
        val file = File("src/main/resources/cookie_log_test.csv")
        val args = arrayOf("-f", file.absolutePath, "-d", "2018-12-09")

        val outputStream = java.io.ByteArrayOutputStream()
        System.setOut(java.io.PrintStream(outputStream))

        main(args)

        val output = outputStream.toString()
        assertTrue(output.contains("AtY0laUfhglK3lC7"), "Output should contain most active cookie")
    }
}