package supplychain.app

import supplychain.app.domain.*
import supplychain.app.repo.FileSupplyChainRepo
import supplychain.app.repo.FileUserRepo

fun main() {
    val userRepo = FileUserRepo()
    val supplyChainRepo = FileSupplyChainRepo()
    val domain = Domain(userRepo, supplyChainRepo)


}
