package aliafroozi.onlineShop.models.product

import aliafroozi.onlineShop.models.invoice.InvoiceItems
import org.hibernate.annotations.ColumnDefault
import org.springframework.boot.context.properties.bind.DefaultValue
import javax.persistence.*
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var description: String = "",
    var image: String = "",
    var visitCount: Int = 0,
    var addDate: String = "",
    var title: String = "",
    @ColumnDefault(value = "0")
    var price: Long = 0,

    @ManyToMany
    var colors: Set<Color>? = null,

    @ManyToOne
    @JoinColumn(name = "category_id")
    var category: ProductCategory? = null,

    @OneToOne(mappedBy = "product")
    var invoiceItem: InvoiceItems? = null,

    @ManyToMany
    var sizes: Set<Size>? = null
)
