package supplychain.app.domain

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import supplychain.app.repo.SupplyChain
import supplychain.app.repo.SupplyChainRepoInterface
import supplychain.app.repo.UserRepoInterface

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

    fun getDetailsForSupplier(userId: String?, supplierId: String?): Map<String, String> {
        // first get the org the user belongs to
        if (userId == "ZU123") {
            val org = "ZC456"
        } else {
            // todo: handle error
        }

        // do a check to see if the
        if (supplierId == null) {
            // todo: handle error

            TODO()
        } else {
            val supplierDetails = supplyChainRepo.fetchSupplierDetailsBySupplierId(supplierId)
            return supplierDetails
        }

    }
}


