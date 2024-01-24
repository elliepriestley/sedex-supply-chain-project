package supplychain.app.domain

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

// when constructing the domain, be sure to use the interfaces rather than the actual repos.
// We want the SHAPE of them, not the actual Repos, because we want them to be reusable

// Nothing technology specific should be in the domain.
class Domain(private val userRepo: UserRepoInterface, private val supplyChainRepo: SupplyChainRepoInterface) {
    fun getDirectSuppliersForUser(userID: String): SupplyChain {
        // todo: handle errors
        val companyID: String = userRepo.fetchCompanyThatUserBelongsTo(userID)

        // todo: handle errors
        val supplyChain: SupplyChain = supplyChainRepo.fetchSupplyChainForCompany(companyID)

        return convertSupplyChainToJsonObject(supplyChain)

// domain should rely on interfaces not exact classes/technologies

    }

    private fun convertSupplyChainToJsonObject(supplyChain: SupplyChain): SupplyChain {
        val mapper = jacksonObjectMapper()
        val jsonString = mapper.writeValueAsString(supplyChain)
        val jsonSupplyChain: SupplyChain = mapper.readValue(jsonString)
        return jsonSupplyChain
    }

    fun getDetailsForSupplier(SupplierId: String?): Map<String, String> {
        if (SupplierId == null) {
            // todo: handle error
            TODO()
        } else {
            val supplierDetails = supplyChainRepo.fetchSupplierDetailsBySupplierId(SupplierId)
            return supplierDetails
        }

    }
}


