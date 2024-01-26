package supplychain.app.api

import com.fasterxml.jackson.databind.ObjectMapper
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
import supplychain.app.domain.*
import supplychain.app.repo.FileSupplyChainRepo //
import supplychain.app.repo.FileUserRepo //

class HttpAPI(domain: Domain) {

    private val userID = "ZU124"
    private val mapper = ObjectMapper()


    val optionalTypeQuery = Query.string().optional("type")
    val optionalIdQuery = Query.string().optional("id")

    val app: HttpHandler = routes(

        "/suppliers" bind GET to {request ->
            val type: String? = optionalTypeQuery(request)
            val supplierId: String? = optionalIdQuery(request)
            val supplierIDs = domain.getDirectSuppliersForUser(userID)
            val supplierDetails = supplierId?.let { domain.getDetailsForSupplier(userID, it) }

            when (type) {
                "direct" -> Response(OK).body(mapper.writeValueAsString(supplierIDs))
                "indirect" -> Response(OK).body("Feature not available yet")
                else -> Response(OK).body(mapper.writeValueAsString(supplierIDs))
            }

            if (supplierId != null) {
                val mapOfSupplierDetails = mapOf(supplierId to supplierDetails)
                Response(OK).body(mapOfSupplierDetails.toString())
            } else {
                TODO()// todo: error handling
            }

        }
    )

}



