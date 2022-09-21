package aliafroozi.onlineShop.repositories.invoices

import aliafroozi.onlineShop.models.invoice.InvoiceItems
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface InvoiceItemsRepo : PagingAndSortingRepository<InvoiceItems , Long> {
    @Query("From InvoiceItems Where invoice.id = :invoiceId")
    fun findByInvoiceId(invoiceId : Long) : List<InvoiceItems>
}