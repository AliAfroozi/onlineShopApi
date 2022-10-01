package aliafroozi.onlineShop.controllers.invoice

import aliafroozi.onlineShop.models.invoice.Invoice
import aliafroozi.onlineShop.services.invoices.InvoiceService
import aliafroozi.onlineShop.utils.NotFoundException
import aliafroozi.onlineShop.utils.ServiceResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/invoice")
class InvoiceController {

    @Autowired
    lateinit var service: InvoiceService


    @PostMapping("")
    fun addInvoice(@RequestBody invoice: Invoice): ServiceResponse<Invoice> {
        return try {
            val data = service.insert(invoice)
            if (data == null)
                throw NotFoundException("invoice not found") else
                ServiceResponse(data = listOf(data), status = HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("")
    fun updateInvoice(@RequestBody invoice: Invoice): ServiceResponse<Invoice> {
        return try {
            val data = service.update(invoice)
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
    fun getById(@PathVariable invoiceId: Long): ServiceResponse<Invoice> {
        return try {
            val data = service.getById(invoiceId)
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
    fun getAllByUserId(@PathVariable userId: Long , @RequestParam pageSize : Int , @RequestParam pageIndex : Int): ServiceResponse<Invoice> {
        return try {
            val data = service.getAllByUserId(userId , pageIndex , pageSize)
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
