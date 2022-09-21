package aliafroozi.onlineShop.models.invoice

import aliafroozi.onlineShop.models.enums.InvoiceStatus
import aliafroozi.onlineShop.models.person.User
import javax.persistence.*

@Entity
data class Invoice(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0,
    var number : Int,
    var Status : InvoiceStatus,
    var addDate : String,
    var paymentDate : String,


    @ManyToOne
    @JoinColumn(name =  "user_id")
    var user : User? = null,

    @OneToMany(mappedBy = "invoice")
    val invoiceItems: Set<InvoiceItems>? = null,

    @OneToMany(mappedBy = "invoice")
    val transactions: Set<Transaction>? = null
)
