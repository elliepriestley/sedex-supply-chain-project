package supplychain.app

fun main() {
    val userRepo = FileUserRepo()
    val supplyChainRepo = FileSupplyChainRepo()
    val domain = Domain(userRepo, supplyChainRepo)

    val supplierIDs = domain.getDirectSuppliersForUser("testUser1")

    println("supplierIDs: $supplierIDs")



}
