package aliafroozi.onlineShop.models.product

import org.springframework.data.annotation.Id
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.ManyToMany

@Entity
data class Size(


    @javax.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var value: Int = 0


    )
