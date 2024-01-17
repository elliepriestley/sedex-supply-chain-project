package supplychain.app

import com.natpryce.hamkrest.assertion.assertThat
import com.natpryce.hamkrest.and
import org.http4k.core.Method.GET
import org.http4k.core.Request
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
        val request = app(Request(GET, "http://localhost:9000/suppliers"))
        assertThat(request, hasStatus(OK)
            .and(hasBody("[ZS456]")))
    }

    @Test
    fun `Test that suppliers endpoint optional query parameter type returns hard coded direct suppliers list`() {
        val request = app(Request(GET, "http://localhost:9000/suppliers?type=direct"))

        assertThat(request, hasStatus(OK)
            .and(hasBody("[ZS456]")))
    }

    @Test
    fun `Test that suppliers endpoint optional query parameter type returns hard coded indirect suppliers list`() {
        val request = app(Request(GET, "http://localhost:9000/suppliers?type=indirect"))

        assertThat(request, hasStatus(OK)
            .and(hasBody("[indirect supplier example]")))
    }




}