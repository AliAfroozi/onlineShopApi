package aliafroozi.onlineShop.models.invoice

import aliafroozi.onlineShop.models.product.Product
import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class InvoiceItems(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var quantity: Int = 0,
    var unitPrice: String = "0",

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    var invoice: Invoice?,

    @OneToOne
    @JoinColumn(name = "product_id")
    var product: Product? = null
)
