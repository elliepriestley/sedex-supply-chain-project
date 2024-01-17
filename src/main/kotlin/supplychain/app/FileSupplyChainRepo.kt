package supplychain.app

class FileSupplyChainRepo: SupplyChainRepoInterface {
    override fun fetchSupplyChainForCompany(companyID: String): SupplyChain {
        if (companyID == "ZC789") {
            return SupplyChain(listOf("ZS456"))
        }
        TODO()
    }
}

interface SupplyChainRepoInterface {
    fun fetchSupplyChainForCompany(companyID: String): SupplyChain

}






