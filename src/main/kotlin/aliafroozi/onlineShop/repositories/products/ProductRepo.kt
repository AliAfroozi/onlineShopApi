package aliafroozi.onlineShop.repositories.products

import aliafroozi.onlineShop.models.product.Product
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductRepo : PagingAndSortingRepository<Product, Long> {
    override fun findAll(): List<Product>
    fun findTop6ByOrderByAddDateDesc () : List<Product>
    fun findTop6ByOrderByVisitCountDesc(): List<Product>

    @Query("Select price From Product where id = :id")
    fun findFirstPriceById(id : Long) : Long?
}