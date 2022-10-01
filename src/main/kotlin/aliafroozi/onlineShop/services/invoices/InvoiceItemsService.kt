package aliafroozi.onlineShop.services.invoices

import aliafroozi.onlineShop.models.invoice.InvoiceItems
import aliafroozi.onlineShop.repositories.invoices.InvoiceItemsRepo
import aliafroozi.onlineShop.services.products.ProductService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class InvoiceItemsService {

    @Autowired
    private lateinit var repository: InvoiceItemsRepo

    @Autowired
    lateinit var productService: ProductService

    fun insert(  invoiceItems:   InvoiceItems): InvoiceItems {
        if (invoiceItems.quantity<=0)
            throw Exception("invalid invoiceItem quantity")
        if( invoiceItems.product == null)
            throw Exception("product is null")
        if (invoiceItems.product!!.id < 0 || invoiceItems.product!!.id == null )
            throw Exception("product id is not valid")


        val productPrice = productService.getPriceById(invoiceItems.product!!.id)
        invoiceItems.unitPrice = productPrice.toString()

        return repository.save(invoiceItems)
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