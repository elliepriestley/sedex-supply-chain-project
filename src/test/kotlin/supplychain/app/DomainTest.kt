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
     * And the direct supplier S is in the direct supply chain of O.
     * When U requests the details of S that they specify by ID (S_ID)
     * Then they are provided with:
     * {"S_ID" : {
     *      "name": "xxxx",
     *      "location": "xxxx",
     *      "produce": "xxxx"
     *     }
     * }
     *
     */

    /* Test Case 1 - when supplier details and supplier exist in user's org's supply chain.
    * The domain tries to find the details for a direct supplier id (ZS456) that exists for a User user_a. The User belongs to
    * an organisation with the supplier in its direct supply chain.
    * Create a mock user repo so that when the domain searches for the company id of user_a it returns
    *   {
    *       "id": "user_a",
    *       "companyId": "org_a"
    *   }
    *
    * Create a mock supply chain repo so that when the domain searches for the details of org_a, it returns:
    *   {
    *        "companyId": "org_a",
    *        "directSuppliers": [
    *            {
    *                "ZS456": {
    *                    "name": "Appleton Farm",
    *                    "location": "Warrington",
    *                    "produce": "apples"
    *                }
    *            },
    *            "supplier2"
    *        ]
    *    }
    * When the user user_a queries the domain for the details for supplier by id: ZS456
    * Assert that the reply is:
    *       "ZS456": {
    *           "name": "Appleton Farm",
    *           "location": "Warrington",
    *           "produce": "apples"
    *          }
    */

    /*
    * Test Case 2 - whensSupplier Exists but is not in the user's company's direct supply chain.
    * The domain tries to find the details for a direct supplier id (ZS456) for a User user_b. The User DOES NOT belong to
    * an organisation with the supplier in its direct supply chain.
    * Create a mock user repo so that when the domain searches for the company id of user_b it returns
    *   {
    *       "id": "user_a",
    *       "companyId": "org_b"
    *   }
    *
    * Create a mock supply chain repo so that when the domain searches for the details of org_b, it sees in the json:
    * {
    *    "companies": [
    *        {
    *            "companyId": "org_b",
    *            "directSuppliers": [
    *                {
    *                    "supplier1": {
    *                        "name": "Test Farm",
    *                        "location": "Mock Location",
    *                        "produce": "potatoes"
    *                    }
    *                }
    *            ]
    *        },
    *        {
    *            "companyID": "org_with_supplier_in_sc",
    *            "directSuppliers": [
    *                {
    *                    "ZS456": {
    *                        "name": "CorrectFarm"
    *                    }
    *                },
    *                "another_supplier"
    *            ]
    *        }
    *    ]
    * }
    *
    * When the user, user_b queries the domain for the details for supplier by id: ZS456
    * Assert that a generic error message is raised, i.e. Supplier not found.
    */

    /* Test Case 3 - when supplier doesn't exist
    * The domain tries to find the details for a direct supplier id (InvalidSupplierId) that does not exist, for a User,
    * user_c.
    * Create a mock user repo that contains the following:
    *     {
    *       "id": "user_c",
    *       "companyId": "org_c"
    *     }
    *
    * Create a mock supply chain repo that does not contain the supplier id in it.
    * When we query the details for supplier by id: (InvalidSupplierId)
    * Assert that a generic error message is raised, i.e. Supplier not found.
    */

    /* Test Case 4 - when there are no supplier details
    * The domain tries to find the details for a direct supplier id (SupplierWithNoDetails) that exists, where the
    * user user_d is part of an org org_d that has this supplier in its direct supply chain, but where the supplier has no details.
    * Create a mock user repo that contains the following:
    *     {
    *       "id": "user_d",
    *       "companyId": "org_d"
    *     }
    *
    * Create a mock supply chain repo so that when the domain searches for the supply chain of org_d it returns:
    *     {
    *        "companyId": "org_d",
    *        "directSuppliers": [
    *            {
    *                "SupplierWithNoDetails": {}
    *            },
    *            "supplier2"
    *        ]
    *    }
    * When we query the details for supplier by id: SupplierWithNoDetails
    * Assert that the reply is:
    *   "SupplierWithNoDetails" : {}
    */

    /* Test case 5 - when the user does not exist.
    * The domain tries to find the details for a direct supplier id (supplier_f) that exists, where the user, user_z does
    * not exist.
    * Create a mock user repo that does not contain user_z.
    * When we query the domain for the supply chain details of supplier_f for user_z
    * Assert that a generic error message is raised, i.e. User not found.
    */




}



