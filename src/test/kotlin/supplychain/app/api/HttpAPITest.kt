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
import supplychain.app.repo.Supplier
import supplychain.app.repo.SupplyChain
import supplychain.app.repo.SupplyChainRepoInterface
import supplychain.app.repo.UserRepoInterface

class HttpAPITest {

    @Test
    fun `User can request specific supplier's details`() {
        // Arrange
        val supplierId = "supplier_a"
        val orgName = "org_a"
        val supplier = Supplier("Appleton Farm", "Warrington", "apples")
        val mockSupplyChain = SupplyChain("org_a", listOf(mapOf(supplierId to supplier)))


        val mockUserRepo = object : UserRepoInterface {
            override fun fetchCompanyThatUserBelongsTo(userID: String): String {
                return orgName
            }
        }

        val mockSupplyChainRepo = object : SupplyChainRepoInterface {
            override fun fetchSupplyChainForCompany(companyID: String): SupplyChain {
                return mockSupplyChain
            }
            override fun fetchSupplierDetailsBySupplierId(supplyChain: SupplyChain, supplierId: String): Supplier {
                return supplier
            }
        }

        val domain = Domain(mockUserRepo, mockSupplyChainRepo)
        val testApp = HttpAPI(domain).app

        // Act
        val request = Request(GET, "/suppliers?id=${supplierId}")

        // Assert
        val expected = "{supplier_a=Supplier(name=Appleton Farm, location=Warrington, produce=apples)}"

        assertThat(testApp(request), hasStatus(OK)
            .and(hasBody(expected)))
    }

    @Test
    fun `User cannot access supplier details when supplier is not in the user's org's supply chain`() {
        // Arrange
        val requestedSupplierId = "supplier_z"
        val supplierDetails = Supplier("fake name", "fake location", "fake produce")




        val mockUserRepo = object: UserRepoInterface {
            override fun fetchCompanyThatUserBelongsTo(userID: String): String {
                return "organisation_b"
            }
        }
        val mockSupplyChainRepo = object: SupplyChainRepoInterface {
            override fun fetchSupplierDetailsBySupplierId(supplyChain: SupplyChain, supplierId: String): Supplier {
                return supplierDetails
            }

            override fun fetchSupplyChainForCompany(companyID: String): SupplyChain {
                return SupplyChain(companyID, listOf(mapOf("supplier_b" to supplierDetails)))
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