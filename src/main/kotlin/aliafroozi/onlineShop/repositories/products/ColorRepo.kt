package aliafroozi.onlineShop.repositories.products

import aliafroozi.onlineShop.models.product.Color
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ColorRepo : PagingAndSortingRepository<Color, Long> {
    override fun findAll(): List<Color>
}