package supplychain.app

import supplychain.app.domain.*
import supplychain.app.repo.FileSupplyChainRepo
import supplychain.app.repo.FileUserRepo

fun main() {
    val userRepo = FileUserRepo()
    val supplyChainRepo = FileSupplyChainRepo()
    val domain = Domain(userRepo, supplyChainRepo)

    // when domain.userHasAccessToSupplierDetails  is true
    println(domain.getDetailsForSupplier("ZU124", "Fruit Farm"))

    // when domain.userHasAccessToSupplierDetails is false
    println(domain.getDetailsForSupplier("ZU123", "Fruit Farm"))


}
