package aliafroozi.onlineShop.models.invoice

import aliafroozi.onlineShop.models.product.Product
import javax.persistence.*

@Entity
data class InvoiceItems(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0,
    var quantity : Int,
    var unitPrice : String,

    @ManyToOne
    @JoinColumn(name = "invoice_id")
    var invoice  : Invoice,

    @OneToOne
    @JoinColumn(name = "product_id")
    var product: Product? = null
    )
