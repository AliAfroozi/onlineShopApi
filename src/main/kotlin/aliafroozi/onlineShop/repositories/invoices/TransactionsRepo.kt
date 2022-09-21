package aliafroozi.onlineShop.repositories.invoices

import aliafroozi.onlineShop.models.invoice.Transaction
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactionsRepo : PagingAndSortingRepository<Transaction, Long> {
    @Query("From Transaction Where invoice.id = :invoiceId" )
    fun findByInvoiceId(invoiceId: Long) : List<Transaction>
}