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


    @Disabled // no longer relevant as HTTPAPI now linked to domain and therefore FileUserRepo and FileSupplyChainRepo
    @Test
    fun `Test that suppliers endpoint optional query parameter type returns hard coded direct suppliers list`() {
        val api = HttpAPI()
        val request = api.app(Request(GET, "http://localhost:9000/suppliers?type=direct"))

        assertThat(request, hasStatus(OK)
            .and(hasBody("[ZS456]")))
    }

    @Disabled // no longer relevant as HTTPAPI now linked to domain and therefore FileUserRepo and FileSupplyChainRepo
    @Test
    fun `Test that suppliers endpoint optional query parameter type returns hard coded indirect suppliers list`() {
        val api = HttpAPI()
        val request = api.app(Request(GET, "http://localhost:9000/suppliers?type=indirect"))

        assertThat(request, hasStatus(OK)
            .and(hasBody("[indirect supplier example]")))
    }

    @Disabled // no longer relevant as HTTPAPI now linked to domain and therefore FileUserRepo and FileSupplyChainRepo
    @Test
    fun `Test that when no optional query parameter provided, suppliers endpoint will return the direct suppliers`() {
        val api = HttpAPI()
        val request = api.app(Request(GET, "http://localhost:9000/suppliers"))

        assertThat(request, hasStatus(OK)
            .and(hasBody("[ZS456]")))
    }

}