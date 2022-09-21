package aliafroozi.onlineShop.repositories.otherContent

import aliafroozi.onlineShop.models.otherContent.Slider
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SliderRepo : PagingAndSortingRepository<Slider ,Long> {
    override fun findAll(): List<Slider>
}
