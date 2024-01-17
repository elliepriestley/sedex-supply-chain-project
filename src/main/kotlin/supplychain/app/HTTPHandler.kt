package supplychain.app

import org.http4k.core.HttpHandler
import org.http4k.core.Method.GET
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.core.then
import org.http4k.filter.DebuggingFilters.PrintRequest
import org.http4k.lens.Query
import org.http4k.lens.string
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.SunHttp
import org.http4k.server.asServer

val directSuppliersList = listOf("ZS456")
val indirectSuppliersList = listOf("indirect supplier example")

val optionalQuery = Query.string().optional("type")

val app: HttpHandler = routes(
    "/suppliers" bind GET to {request ->
        val type: String? = optionalQuery(request)
        if (type != null) {
            when (type) {
                "direct" -> Response(OK).body(directSuppliersList.toString())
                "indirect" -> Response(OK).body(indirectSuppliersList.toString())
                // todo: error handling
                else -> TODO()
            }
        } else {
            Response(OK).body(directSuppliersList.toString())
        }
    }
)




fun main() {
    val printingApp: HttpHandler = PrintRequest().then(app)

    val server = printingApp.asServer(SunHttp(9000)).start()

    println("Server started on " + server.port())
}
