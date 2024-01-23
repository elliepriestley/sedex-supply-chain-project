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

            override fun fetchSupplierDetailsBySupplierId(supplierId: String): Map<String, String> {
                TODO("Not yet implemented")
            }
        }

        val mockUserRepoThatAlwaysReturnsUserID123 = object : UserRepoInterface {
            override fun fetchCompanyThatUserBelongsTo(userID: String): String {
                return "company_id_123"
            }
        }
        // make variable names more descriptive, not just "mockXRepo" - makes it easier to see what is going on quicker

        val domain = Domain(mockUserRepoThatAlwaysReturnsUserID123, mockSupplyChainRepoThatHasOnlyOneSupplier)

        val actual = domain.getDirectSuppliersForUser("user1")
        val expected = SupplyChain(companyId="company_id_123", directSuppliers= listOf("supplier1"))

        assertEquals(expected, actual)
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

            override fun fetchSupplierDetailsBySupplierId(supplierId: String): Map<String, String> {
                TODO("Not yet implemented")
            }
        }

        // creating the mock userRepo
        val mockUserRepoThatAlwaysReturnsUserID123 = object : UserRepoInterface {
            override fun fetchCompanyThatUserBelongsTo(userID: String): String {
                return "company_id_123"
            }
        }

        val domain = Domain(mockUserRepoThatAlwaysReturnsUserID123, mockSupplyChainRepoWithNoSuppliers)

        val actual = domain.getDirectSuppliersForUser("user1")
        val expected = SupplyChain("company_id_123", listOf())

        assertEquals(expected, actual)


    }

    // User Story 2:
    // As an admin at a top-level customer org, I want to get the details of a direct supplier that I specify by ID

    /** SPEC
     * Given a user U is present in a top level organisation O
     * And U is an admin at O
     * When U requests the details of a direct supplier S that they specify by ID (S_ID)
     * Then they are provided with:
     * {"S_ID" : {
     *      "name": "xxxx",
     *      "location": "xxxx",
     *      "produce": "xxxx"
     *     }
     * }
     *
     */

    /* Test Case 1
    * The domain tries to find the details for a direct supplier id (ZS456) that exists.
    * Create a mock supply chain repo so that when the domain searches for details on supplier ZS456, it returns:
    *       "ZS456": {
    *           "name": "Appleton Farm",
    *           "location": "Warrington",
    *           "produce": "apples"
    *          }
    * When we query the details for supplier by id: ZS456
    * Assert that the reply is return value above.
    *
    *
    * Test Case 2
    * The domain tries to find the details for a direct supplier id (InvalidSupplierId) that does not exist.
    * Create a mock supply chain repo that does not contain the supplier id:
    * When we query the details for supplier by id: (InvalidSupplierId)
    * Assert that an exception is raised to tell the user it is an invalid request.
    *
    *
    * Test Case 3
    * The domain tries to find the details for a direct supplier id (SupplierWithNoDetails) that exists, but has no supplier details.
    * Create a mock supply chain repo so that when the domain searches for details on supplier ZS456, it returns:
    *       "SupplierWithNoDetails": {}
    * When we query the details for supplier by id: SupplierWithNoDetails
    * Assert that the reply is:
    *   "SupplierWithNoDetails" : {}
    *
    * */



}



