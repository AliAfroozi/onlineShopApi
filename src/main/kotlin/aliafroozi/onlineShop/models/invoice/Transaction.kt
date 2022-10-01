package aliafroozi.onlineShop.models.invoice

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class Transaction(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var authority: String = "",
    var status: Int,
    var refId: String = "",


    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "invoice_id")
    var invoice: Invoice
)
