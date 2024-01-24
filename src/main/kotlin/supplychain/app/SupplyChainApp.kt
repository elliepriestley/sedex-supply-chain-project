package supplychain.app

import org.http4k.core.HttpHandler
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters
import org.http4k.server.SunHttp
import org.http4k.server.asServer
import supplychain.app.api.HttpAPI
import supplychain.app.domain.Domain
import supplychain.app.repo.FileSupplyChainRepo
import supplychain.app.repo.FileUserRepo

fun main() {
    val domain = Domain(FileUserRepo(), FileSupplyChainRepo())
    val api = HttpAPI(domain)

    val printingApp: HttpHandler = DebuggingFilters.PrintRequest().then(api.app)

    val server = printingApp.asServer(SunHttp(9000)).start()

    println("Server started on " + server.port())
}