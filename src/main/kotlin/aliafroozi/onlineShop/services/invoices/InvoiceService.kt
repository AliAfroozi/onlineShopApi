package aliafroozi.onlineShop.services.invoices

import aliafroozi.onlineShop.models.enums.InvoiceStatus
import aliafroozi.onlineShop.models.invoice.Invoice
import aliafroozi.onlineShop.repositories.invoices.InvoiceRepo
import aliafroozi.onlineShop.services.person.UserService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
class InvoiceService {

    @Autowired
    lateinit var repository: InvoiceRepo

    @Autowired
    lateinit var invoiceItemsService: InvoiceItemsService

    @Autowired
    lateinit var userService: UserService
    
    fun insert(invoice: Invoice , currentUser: String): Invoice {

        val date = Calendar.getInstance()
        if (invoice.user == null)
            throw Exception("user is null")
        if ( invoice.user!!.id.toInt() == 0 || invoice.user!!.id < 0)
            throw Exception("user id is not valid, it must be greater than 0")
        if (invoice.user!!.id == null)
            throw Exception("user id is null")
        if (invoice.invoiceItems == null || invoice.invoiceItems.isEmpty())
            throw Exception("invoice items are empty")

        val user = userService.getByUsername(currentUser)
        if (user == null || user.id != invoice.user!!.id)
            throw Exception("you don't have permission to see this user's invoices.")

        invoice.Status = InvoiceStatus.NotPayed
        invoice.addDate =  "${date.get(Calendar.YEAR)}-${date.get(Calendar.MONTH)}-${date.get(Calendar.DAY_OF_MONTH)}  ${date.get(
            Calendar.HOUR_OF_DAY)}:${date.get(Calendar.MINUTE)}:${date.get(Calendar.SECOND)}"

        invoice.paymentDate = ""
        invoice.transactions = null
        invoice.invoiceItems?.forEach{
            invoiceItemsService.insert(it)
        }
        return repository.save(invoice)
    }

    fun update(Invoice: Invoice, currentUser: String): Invoice? {
        val data = getById(Invoice.id , currentUser )
        if (data == null)
            return null
        else {
            data.Status = Invoice.Status
            data.paymentDate = Invoice.paymentDate
            return repository.save(data)
        }
    }

    fun getById(InvoiceId: Long, currentUser: String): Invoice? {
        val data = repository.findById(InvoiceId)
        if (data.isEmpty)
            return null
        val user = userService.getByUsername(currentUser)
        if (user == null || user.id != data.get().user!!.id)
            throw Exception("you don't have permission to see this user's invoices.")
        return data.get()
    }


    fun getAllByUserId(userId: Long, pageIndex: Int, pageSize: Int, currentUser: String): List<Invoice> {
        val user = userService.getByUsername(currentUser)
        if (user == null || user.id != userId)
            throw Exception("you don't have permission to see this user's invoices.")
        val pageRequest = PageRequest.of(pageIndex, pageSize, Sort.by("id"))
        return repository.findByUserId(userId, pageRequest)
    }


    private fun delete(InvoiceId: Long): Boolean {
        repository.deleteById(InvoiceId)
        return true
    }

}