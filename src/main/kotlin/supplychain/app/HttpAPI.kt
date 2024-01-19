package supplychain.app

import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.NOT_FOUND
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.lens.Query
import org.http4k.lens.string
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer

class HttpAPI {

    private val domain = Domain(FileUserRepo(), FileSupplyChainRepo())
    private val userID = "ZU124"


    val optionalQuery = Query.string().optional("type")

    val app: HttpHandler = routes(

        "/suppliers" bind GET to {request ->
            val type: String? = optionalQuery(request)
            val supplierIDs = domain.getDirectSuppliersForUser(userID)
            Response(OK).body(supplierIDs.toString())

            when (type) {
                "direct" -> Response(OK).body(supplierIDs.toString())
                "indirect" -> Response(OK).body("Feature not available yet")
                else -> Response(OK).body(supplierIDs.toString())
            }
        }
    )

}




fun main() {
    val api = HttpAPI()

    val printingApp: HttpHandler = PrintRequest().then(api.app)

    val server = printingApp.asServer(SunHttp(9000)).start()

    println("Server started on " + server.port())
}
