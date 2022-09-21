package aliafroozi.onlineShop.repositories.invoices

import aliafroozi.onlineShop.models.invoice.Invoice
import aliafroozi.onlineShop.models.person.User
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface InvoiceRepo : PagingAndSortingRepository<Invoice, Long> {

    @Query("From Invoice Where user.id = :userId ")
    fun findByUserId(userId: Long, pageable: Pageable) : List<Invoice>
}
