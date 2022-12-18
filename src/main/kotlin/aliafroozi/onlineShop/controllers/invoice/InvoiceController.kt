package aliafroozi.onlineShop.controllers.invoice

import aliafroozi.onlineShop.config.JwtTokenUtil
import aliafroozi.onlineShop.models.invoice.Invoice
import aliafroozi.onlineShop.services.invoices.InvoiceService
import aliafroozi.onlineShop.utils.exceptions.NotFoundException
import aliafroozi.onlineShop.utils.ServiceResponse
import aliafroozi.onlineShop.utils.UserUtil.Companion.getCurrentUsernameFromToken
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("api/invoice")
class InvoiceController {

    @Autowired
    lateinit var service: InvoiceService

    @Autowired
    lateinit var jwtUtil: JwtTokenUtil


    @PostMapping("")
    fun addInvoice(@RequestBody invoice: Invoice , request: HttpServletRequest): ServiceResponse<Invoice> {
        return try {
            val currentUser = getCurrentUsernameFromToken(jwtUtil , request)
            val data = service.insert(invoice , currentUser)
            if (data == null)
                throw NotFoundException("invoice not found") else
                ServiceResponse(data = listOf(data), status = HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @GetMapping("/{invoiceId}")
    fun getById(@PathVariable invoiceId: Long , request: HttpServletRequest): ServiceResponse<Invoice> {
        return try {
            val currentUser = getCurrentUsernameFromToken(jwtUtil , request)
            val data = service.getById(invoiceId , currentUser)
            if (data == null)
                throw NotFoundException("invoice not found") else
                ServiceResponse(data = listOf(data), status = HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @GetMapping("/user/{userId}")
    fun getAllByUserId(@PathVariable userId: Long , @RequestParam pageSize : Int , @RequestParam pageIndex : Int , request : HttpServletRequest): ServiceResponse<Invoice> {
        return try {
            val currentUser = getCurrentUsernameFromToken(jwtUtil, request)
            val data = service.getAllByUserId(userId , pageIndex , pageSize , currentUser)
            if (data == null)
                throw NotFoundException("invoice not found") else
                ServiceResponse(data = (data), status = HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
