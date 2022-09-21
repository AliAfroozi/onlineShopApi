package aliafroozi.onlineShop.models.person

import aliafroozi.onlineShop.models.invoice.Invoice
import javax.persistence.*

@Entity
@Table(name = "users")
data class User(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0,
    var userName : String,
    var password : String,


    @OneToOne
    @JoinColumn(name = "person_id")
    var person : Person? = null,

    @OneToMany(mappedBy = "user")
    var invoices : Set<Invoice>? = null
)
