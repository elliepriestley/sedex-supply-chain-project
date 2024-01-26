package supplychain.app.repo

interface SupplyChainRepoInterface {
    fun fetchSupplyChainForCompany(companyID: String): SupplyChain
    fun fetchSupplierDetailsBySupplierId(supplyChain: SupplyChain, supplierId: String): Supplier?

}