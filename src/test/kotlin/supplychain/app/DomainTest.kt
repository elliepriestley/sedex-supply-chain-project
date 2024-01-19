package supplychain.app

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Disabled
import org.junit.jupiter.api.Test


class DomainTest {

    // User Story 1:
    // As an admin at a top level customer org, I want to view a list of my direct suppliers

    /* SPEC
    * Given a user U is present in a top level customer org O
    * And a list of direct suppliers is present for O
    * When U requests a list of their direct suppliers
    * Then they are provided with [Suppliers]
    * */


    @Test
    fun `When the user's company has a list of suppliers and the user requests a list of direct suppliers, they are provided with a list of direct suppliers`() {

        val mockSupplyChainRepoThatHasOnlyOneSupplier = object : SupplyChainRepoInterface {
            override fun fetchSupplyChainForCompany(companyID: String): SupplyChain {
                return SupplyChain(companyID, listOf("supplier1"))
            }
        }

        val mockUserRepoThatAlwaysReturnsUserID123 = object : UserRepoInterface {
            override fun fetchCompanyThatUserBelongsTo(userID: String): String {
                return "user_id_123"
            }
        }
        // make variable names more descriptive, not just "mockXRepo" - makes it easier to see what is going on quicker

        val domain = Domain(mockUserRepoThatAlwaysReturnsUserID123, mockSupplyChainRepoThatHasOnlyOneSupplier)

        assertEquals(domain.getDirectSuppliersForUser("user1"), listOf("supplier1"))
    }


    /* SPEC
    * Given a user U is present in a top level customer org O
    * And a list of direct suppliers is not present for O
    * When U requests a list of their direct suppliers
    * Then they are provided with []
    * */

    @Test
    fun `When the user's company has no suppliers and the user requests a list of direct suppliers, the domain returns an empty list`() {

        // creating the mock SupplyChainRepo
        val mockSupplyChainRepoWithNoSuppliers = object : SupplyChainRepoInterface {
            override fun fetchSupplyChainForCompany(companyID: String): SupplyChain {
                return SupplyChain(companyID, listOf())
            }
        }

        // creating the mock userRepo
        val mockUserRepoThatAlwaysReturnsUserID123 = object : UserRepoInterface {
            override fun fetchCompanyThatUserBelongsTo(userID: String): String {
                return "user_id_123"
            }
        }

        val domain = Domain(mockUserRepoThatAlwaysReturnsUserID123, mockSupplyChainRepoWithNoSuppliers)
        assertEquals(domain.getDirectSuppliersForUser("user1"), emptyList<String>())


    }

}



