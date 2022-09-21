package aliafroozi.onlineShop.models.product
import javax.persistence.*

@Entity
data class ProductCategory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id : Int = 0,
    var title : String,

    @OneToMany(mappedBy = "category")
    val products : Set<Product>? = null
    )
