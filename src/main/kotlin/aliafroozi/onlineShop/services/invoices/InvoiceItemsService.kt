package aliafroozi.onlineShop.services.invoices

import aliafroozi.onlineShop.models.invoice.InvoiceItems
import aliafroozi.onlineShop.repositories.invoices.InvoiceItemsRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InvoiceItemsService {

    @Autowired
    lateinit var repository: InvoiceItemsRepo

    fun insert(  InvoiceItems:   InvoiceItems): InvoiceItems {
        return repository.save(  InvoiceItems)
    }

    fun getById(  InvoiceItemsId: Long):   InvoiceItems? {
        val data = repository.findById(  InvoiceItemsId)
        return if (data.isEmpty)
            null
        else
            data.get()
    }


    fun getAllByInvoiceId(invoiceId : Long): List<  InvoiceItems> {
        return repository.findByInvoiceId(invoiceId = invoiceId)
    }


    private fun delete(  InvoiceItemsId: Long): Boolean {
        repository.deleteById(  InvoiceItemsId)
        return true
    }

}