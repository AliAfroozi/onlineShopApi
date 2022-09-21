package aliafroozi.onlineShop.models.invoice

import javax.persistence.*

@Entity
data class Transaction(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0,
    var authority : String ,
    var status: Int,
    var refId : String ,

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    var invoice : Invoice
)
