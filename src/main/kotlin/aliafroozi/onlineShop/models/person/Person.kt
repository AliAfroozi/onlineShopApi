package aliafroozi.onlineShop.models.person

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
class Person(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var phone: String = "",
    var postalCode: String = "",

    @JsonIgnore
    @OneToOne(mappedBy = "person", fetch = FetchType.LAZY)
    val user: User? = null

)