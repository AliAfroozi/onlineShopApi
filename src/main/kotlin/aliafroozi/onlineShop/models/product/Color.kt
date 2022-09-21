package aliafroozi.onlineShop.models.product

import org.springframework.data.annotation.Id
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.ManyToMany

@Entity
data class Color(


    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Long = 0,

    var title: String,
    var hexValue : String,


)
