package supplychain.app.api

import com.natpryce.hamkrest.and
import com.natpryce.hamkrest.assertion.assertThat
import org.http4k.core.Method.GET
import org.http4k.core.Request
import org.http4k.core.Status.Companion.OK
import org.http4k.hamkrest.hasBody
import org.http4k.hamkrest.hasStatus
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import supplychain.app.domain.Domain
import supplychain.app.repo.SupplyChain
import supplychain.app.repo.SupplyChainRepoInterface
import supplychain.app.repo.UserRepoInterface

class HttpAPITest {

    // user can request specific supplier's details

    @Test
    fun `User can request specific supplier's details`() {
        // Arrange
        // create an instance of the domain, and pass it through HTTPAPI class

        val supplierId = "supplier_a"
        val expectedSupplierDetails = mapOf("name" to "Appleton Farm")

        val mockUserRepo = object : UserRepoInterface {
            override fun fetchCompanyThatUserBelongsTo(userID: String): String {
                return "org_a"
            }
        }

        val mockSupplyChainRepo = object : SupplyChainRepoInterface {
            override fun fetchSupplyChainForCompany(companyID: String): SupplyChain {
                return SupplyChain(companyID, listOf(supplierId))
            }

            override fun fetchSupplierDetailsBySupplierId(supplierId: String): Map<String, String> {
                return expectedSupplierDetails
            }

        }

        val domain = Domain(mockUserRepo, mockSupplyChainRepo)
        val testApp = HttpAPI(domain).app

        // Act
        // Make a request on HttpApi.app
        // url = /suppliers?id=ZS123
        val request = Request(GET, "/suppliers?id=${supplierId}")

        // Assert
        // that the response is a map of maps with supplier details of ZS123
        val expectedResponse = mapOf(supplierId to expectedSupplierDetails)
        assertThat(testApp(request), hasStatus(OK)
            .and(hasBody(expectedResponse.toString())))
    }
}