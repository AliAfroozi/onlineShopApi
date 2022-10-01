package aliafroozi.onlineShop.models.product

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*

@Entity
data class ProductCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var title: String = "",
    var image : String = "",

    @JsonIgnore
    @OneToMany(mappedBy = "category")
    val products: Set<Product>? = null
)
