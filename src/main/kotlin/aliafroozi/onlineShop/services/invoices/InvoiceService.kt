package aliafroozi.onlineShop.services.invoices

import aliafroozi.onlineShop.models.invoice.Invoice
import aliafroozi.onlineShop.repositories.invoices.InvoiceRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class InvoiceService {

    @Autowired
    lateinit var repository: InvoiceRepo

     fun insert(Invoice: Invoice): Invoice {
        return repository.save(Invoice)
    }

     fun update(Invoice: Invoice): Invoice? {
        val data = getById(Invoice.id)
        if (data == null)
            return null
        else{
            data.Status = Invoice.Status
            data.paymentDate = Invoice.paymentDate
            return repository.save(data)
        }
    }

    fun getById(InvoiceId: Long): Invoice? {
        val data = repository.findById(InvoiceId)
        if (data.isEmpty)
            return null
        else
            return data.get()
    }


    fun getAllByUserId(userId : Long , pageIndex :Int , pageSize : Int): List<Invoice> {
        val pageRequest = PageRequest.of(pageIndex , pageSize , Sort.by("id"))
        return repository.findByUserId(userId , pageRequest)
    }


    private fun delete(InvoiceId: Long): Boolean {
        repository.deleteById(InvoiceId)
        return true
    }

}