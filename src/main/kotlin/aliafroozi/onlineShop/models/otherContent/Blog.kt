package aliafroozi.onlineShop.models.otherContent

import org.springframework.context.annotation.Primary
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Blog(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    var image: String = "",
    var title: String = "",
    var subTitle: String = "",
    var description: String = "",
    var visitCount: Int =0,
    var addDate: String = ""
)