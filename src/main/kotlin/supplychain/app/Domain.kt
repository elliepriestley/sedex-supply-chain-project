package supplychain.app

// when constructing the domain, be sure to use the interfaces rather than the actual repos.
// We want the SHAPE of them, not the actual Repos, because we want them to be reusable

// Nothing technology specific should be in the domain.
class Domain(private val userRepo: UserRepoInterface, private val supplyChainRepo: SupplyChainRepoInterface) {
    fun getDirectSuppliersForUser(userID: String): List<String> {


        // todo: handle errors
        val companyID: String = userRepo.fetchCompanyThatUserBelongsTo(userID)
        println("companyID: $companyID")


        // todo: handle errors
        val supplyChain: SupplyChain = supplyChainRepo.fetchSupplyChainForCompany(companyID)

        val supplierIDs: List<String> = findDirectSuppliersForCompany(supplyChain, companyID)
        println("supplierIDs: $supplierIDs")

        return supplierIDs

// domain should rely on interfaces not exact classes/technologies
    }

    private fun findDirectSuppliersForCompany(supplyChain: SupplyChain, companyID: String): List<String> {
        return supplyChain.directSuppliers
    }

}

data class SupplyChain(val directSuppliers: List<String>) {

}
