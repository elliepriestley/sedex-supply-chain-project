package supplychain.app

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class FileSupplyChainRepoTest {
    @Test
    fun `Test that fetchSupplyChainForCompany is called suppliersjson file is read to return list of correct suppliers for specified companyId`() {
        val fileSupplyChainRepo = FileSupplyChainRepo()

        assertEquals(SupplyChain("ZC789", listOf("ZS456", "supplier2", "supplier3")), fileSupplyChainRepo.fetchSupplyChainForCompany("ZC789"), )
        assertEquals(SupplyChain( "company2", listOf("supplier4", "supplier5", "supplier6")), fileSupplyChainRepo.fetchSupplyChainForCompany("company2"))
    }

    // Test that when fetchSupplyChainForCompany is called and the companyId does not exist on suppliersjson file, an Exception is thrown
}