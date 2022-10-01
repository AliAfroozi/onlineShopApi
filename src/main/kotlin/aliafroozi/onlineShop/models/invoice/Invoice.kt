package aliafroozi.onlineShop.models.invoice

import aliafroozi.onlineShop.models.enums.InvoiceStatus
import aliafroozi.onlineShop.models.person.User
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Invoice(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var Status: InvoiceStatus? = InvoiceStatus.NotPayed,
    var addDate: String = "",
    var paymentDate: String = "",


    @ManyToOne
    @JoinColumn(name = "user_id")
    var user: User? = null,

    @OneToMany(mappedBy = "invoice")
    val invoiceItems: Set<InvoiceItems>? = null,

    @OneToMany(mappedBy = "invoice")
    var transactions: Set<Transaction>? = null
)
