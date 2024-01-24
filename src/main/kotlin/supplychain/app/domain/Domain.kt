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

    fun getDetailsForSupplier(userId: String, supplierId: String?): Map<String, String>? {
        // get the org the user belongs to
        val companyId: String = userRepo.fetchCompanyThatUserBelongsTo(userId)

        // check if the supplierId is part of the list of suppliers in companyID
        val companySupplyChain = supplyChainRepo.fetchSupplyChainForCompany(companyId)
        val companySupplierList = companySupplyChain.directSuppliers
        val userHasAccessToSupplierDetails: Boolean = companySupplierList.contains(supplierId)

        // if user has access, then return details.
        if (userHasAccessToSupplierDetails) {
            val supplierDetails = supplierId?.let { supplyChainRepo.fetchSupplierDetailsBySupplierId(it) }
            return supplierDetails
        } else {
            throw Exception("Cannot access supplier details")
        }


        // if not, raise an exception



    }
}


