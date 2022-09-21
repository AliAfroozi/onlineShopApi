package aliafroozi.onlineShop.repositories.products

import aliafroozi.onlineShop.models.product.ProductCategory
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductCategoryRepo : PagingAndSortingRepository<ProductCategory, Long> {
    override fun findAll(): List<ProductCategory>
}