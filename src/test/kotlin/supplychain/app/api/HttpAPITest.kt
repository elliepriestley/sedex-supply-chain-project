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
import org.junit.jupiter.api.assertThrows
import supplychain.app.domain.Domain
import supplychain.app.repo.SupplyChain
import supplychain.app.repo.SupplyChainRepoInterface
import supplychain.app.repo.UserRepoInterface

class HttpAPITest {

    @Test
    fun `User can request specific supplier's details`() {
        // Arrange
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
        val request = Request(GET, "/suppliers?id=${supplierId}")

        // Assert
        val expectedResponse = mapOf(supplierId to expectedSupplierDetails)
        assertThat(testApp(request), hasStatus(OK)
            .and(hasBody(expectedResponse.toString())))
    }

    @Test
    fun `User cannot access supplier details when supplier is not in the user's org's supply chain`() {
        // Arrange
        val requestedSupplierId = "supplier_z"
        val mockUserRepo = object: UserRepoInterface {
            override fun fetchCompanyThatUserBelongsTo(userID: String): String {
                return "organisation_b"
            }
        }
        val mockSupplyChainRepo = object: SupplyChainRepoInterface {
            override fun fetchSupplierDetailsBySupplierId(supplierId: String): Map<String, String> {
                return mapOf("name" to "fake_name")
            }

            override fun fetchSupplyChainForCompany(companyID: String): SupplyChain {
                return SupplyChain(companyID, listOf("supplier_b"))
            }
        }
        val domain = Domain(mockUserRepo, mockSupplyChainRepo)
        val testApp = HttpAPI(domain).app

        // Act and Assert
        assertThrows<Exception> {
            testApp(Request(GET, "/suppliers?id=${requestedSupplierId}"))
        }

    }
}