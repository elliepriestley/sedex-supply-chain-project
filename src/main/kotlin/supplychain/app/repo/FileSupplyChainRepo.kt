package supplychain.app.repo

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper

import java.io.File

class FileSupplyChainRepo: SupplyChainRepoInterface {
    private val mapper = jacksonObjectMapper()
    private val resource = this::class.java.classLoader.getResource("suppliers.json")
    private val file = File(resource?.path ?: "No file")
    private val jsonContent = file.readText()
    private val supplierDataForAllOrganisationsFromJson: List<SupplyChain> = mapper.readValue(jsonContent, object : TypeReference<List<SupplyChain>>() {})
    override fun fetchSupplyChainForCompany(companyID: String): SupplyChain {
       val supplyChainOfCompanyId = supplierDataForAllOrganisationsFromJson.find {it.companyId == companyID}
        return supplyChainOfCompanyId ?: throw Exception("Error finding Company ID")
    }

    override fun fetchSupplierDetailsBySupplierId(supplyChain: SupplyChain, supplierId: String): Supplier? {
        val supplierDetails = supplyChain.directSuppliers.firstOrNull { it.containsKey(supplierId) }?.get(supplierId)
        return supplierDetails
        // TODO() Error Handling

    }

}








