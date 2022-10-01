package aliafroozi.onlineShop.models.otherContent

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Slider(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var image: String = "",
    var title: String = "",
    var subTitle: String = "",
    var link: String = ""
)
