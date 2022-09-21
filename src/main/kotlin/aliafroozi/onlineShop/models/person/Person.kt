package aliafroozi.onlineShop.models.person

import javax.persistence.*

@Entity
class Person(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0,
    var firstName : String,
    var lastName : String,
    var address : String,
    var phone : String,
    var postalCode : String,

    @OneToOne(mappedBy = "person")
    val user : User? = null

)