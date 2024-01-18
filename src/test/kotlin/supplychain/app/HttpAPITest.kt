package supplychain.app

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.and
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.hamkrest.hasBody
import org.http4k.hamkrest.hasStatus
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test

class HttpAPITest {


    @Disabled // no longer relevant due to new query parameter
    @Test
    fun `Test that suppliers endpoint returns hard coded list of suppliers`() {
        val api = HttpAPI()
        val request = api.app(Request(GET, "http://localhost:9000/suppliers"))
        assertThat(request, hasStatus(OK)
            .and(hasBody("[ZS456]")))
    }

    @Test
    fun `Test that suppliers endpoint optional query parameter type returns hard coded direct suppliers list`() {
        val api = HttpAPI()
        val request = api.app(Request(GET, "http://localhost:9000/suppliers?type=direct"))

        assertThat(request, hasStatus(OK)
            .and(hasBody("[ZS456]")))
    }

    @Test
    fun `Test that suppliers endpoint optional query parameter type returns hard coded indirect suppliers list`() {
        val api = HttpAPI()
        val request = api.app(Request(GET, "http://localhost:9000/suppliers?type=indirect"))

        assertThat(request, hasStatus(OK)
            .and(hasBody("[indirect supplier example]")))
    }

    @Test
    fun `Test that when no optional query parameter provided, suppliers endpoint will return the direct suppliers`() {
        val api = HttpAPI()
        val request = api.app(Request(GET, "http://localhost:9000/suppliers"))

        assertThat(request, hasStatus(OK)
            .and(hasBody("[ZS456]")))
    }

    @Test
    fun `Test that when the optional query parameter is invalid, Status Code is 404 and body returns error message`() {
        val api = HttpAPI()
        val request1 = api.app(Request(GET, "http://localhost:9000/suppliers?type=test"))
        val request2 = api.app(Request(GET, "http://localhost:9000/suppliers?type=direc"))
        val request3 = api.app(Request(GET, "http://localhost:9000/suppliers?type=92846"))
        val request4 = api.app(Request(GET, "http://localhost:9000/suppliers?type=secondlevel"))
        val request5 = api.app(Request(GET, "http://localhost:9000/suppliers?type=unavailable"))

        assertThat(request1, hasStatus(NOT_FOUND)
            .and(hasBody("Error: 404 Not Found\nInvalid Supplier type")))

        assertThat(request2, hasStatus(NOT_FOUND)
            .and(hasBody("Error: 404 Not Found\nInvalid Supplier type")))

        assertThat(request3, hasStatus(NOT_FOUND)
            .and(hasBody("Error: 404 Not Found\nInvalid Supplier type")))

        assertThat(request4, hasStatus(NOT_FOUND)
            .and(hasBody("Error: 404 Not Found\nInvalid Supplier type")))

        assertThat(request5, hasStatus(NOT_FOUND)
            .and(hasBody("Error: 404 Not Found\nInvalid Supplier type")))





    }







}