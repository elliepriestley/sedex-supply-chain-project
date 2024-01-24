package supplychain.app

import supplychain.app.domain.*

fun main() {
    val userRepo = FileUserRepo()
    val supplyChainRepo = FileSupplyChainRepo()
    val domain = Domain(userRepo, supplyChainRepo)

    val detailsForSupplier = domain.getDetailsForSupplier("ZS123")
    println(detailsForSupplier)



}
