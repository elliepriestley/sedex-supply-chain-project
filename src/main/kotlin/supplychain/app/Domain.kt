package supplychain.app

class Domain(private val userRepo: UserRepo, private val supplyChainRepo: SupplyChainRepo) {
    fun getDirectSuppliersForUser(userID: String): List<String> {


        // todo: handle errors
        val companyID: String = userRepo.fetchCompanyThatUserBelongsTo(userID)
        println("companyID: $companyID")


        // todo: handle errors
        val supplyChain: SupplyChain = supplyChainRepo.fetchSupplyChainForCompany(companyID)

        val supplierIDs: List<String> = findDirectSuppliersForCompany(supplyChain, companyID)
        println("supplierIDs: $supplierIDs")

        return supplierIDs


    }

    private fun findDirectSuppliersForCompany(supplyChain: SupplyChain, companyID: String): List<String> {
        return supplyChain.directSuppliers
    }

}

data class SupplyChain(val directSuppliers: List<String>) {

}
