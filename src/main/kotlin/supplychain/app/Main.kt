package supplychain.app

import org.http4k.core.HttpHandler
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters
import org.http4k.server.SunHttp
import org.http4k.server.asServer

fun main() {
    val userRepo = UserRepo()
    val supplyChainRepo = SupplyChainRepo()
    val domain = Domain(userRepo, supplyChainRepo)

    val supplierIDs = domain.getDirectSuppliersForUser("ZU123")

    println("supplierIDs: $supplierIDs")



}
