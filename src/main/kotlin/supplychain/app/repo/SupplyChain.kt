package supplychain.app.repo

data class SupplyChain (
    val companyId: String,
    val directSuppliers: List<Map<String, Supplier>>
)




