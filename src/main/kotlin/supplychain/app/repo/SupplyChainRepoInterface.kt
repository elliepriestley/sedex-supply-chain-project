package supplychain.app.repo

interface SupplyChainRepoInterface {
    fun fetchSupplyChainForCompany(companyID: String): SupplyChain
    fun fetchSupplierDetailsBySupplierId(supplierId: String): Map<String, String>

}