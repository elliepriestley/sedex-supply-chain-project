package supplychain.app

class SupplyChainRepo {
    fun fetchSupplyChainForCompany(companyID: String): SupplyChain {
        if (companyID == "ZC789") {
            return SupplyChain(listOf("ZS456"))
        }
        TODO()
    }
}