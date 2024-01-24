package supplychain.app.repo

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import supplychain.app.repo.FileSupplyChainRepo
import supplychain.app.repo.SupplyChain
import java.lang.Exception

class FileSupplyChainRepoTest {
    @Test
    fun `Test that when fetchSupplyChainForCompany is called, suppliersjson file is read to return list of correct suppliers for specified companyId`() {
        val fileSupplyChainRepo = FileSupplyChainRepo()

        assertEquals(SupplyChain("ZC789", listOf("ZS456", "supplier2", "supplier3")), fileSupplyChainRepo.fetchSupplyChainForCompany("ZC789"), )
        assertEquals(SupplyChain( "company2", listOf("supplier4", "supplier5", "supplier6")), fileSupplyChainRepo.fetchSupplyChainForCompany("company2"))
    }

    // Test that when fetchSupplyChainForCompany is called and the companyId does not exist on suppliersjson file, an Exception is thrown

    @Test
    fun `Test that when fetchSupplyChainForCompany is called and there are no direct suppliers present, returns empty list within SupplyChain object`() {
        val fileSupplyChainRepo = FileSupplyChainRepo()
        assertEquals(SupplyChain("company3", listOf()), fileSupplyChainRepo.fetchSupplyChainForCompany("company3"))
    }

    @Test
    fun `Test that when fetchSupplyChainForCompany is called and companyid does not exist, throws an Exception`() {
        val fileSupplyChainRepo = FileSupplyChainRepo()
        assertThrows<Exception> {
            fileSupplyChainRepo.fetchSupplyChainForCompany("CompanyIDThatDoesntExist")
        }

    }

}