package aliafroozi.onlineShop.repositories.products

import aliafroozi.onlineShop.models.product.Product
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ProductRepo : JpaRepository<Product, Long> {
    override fun findAll(): List<Product>
    fun findTop6ByOrderByAddDateDesc () : List<Product>
    fun findTop20ByOrderByTitleDesc () : List<Product>
    fun findTop6ByOrderByVisitCountDesc(): List<Product>

    @Query("Select price From Product where id = :id")
    fun findFirstPriceById(id : Long) : Long?

    @Query(value = "SELECT * FROM Product WHERE category_id = ?1",
        countQuery = "SELECT count(*) FROM Product WHERE category_id = ?1",
        nativeQuery = true)
    fun findAllByCategoryId(categoryId: Long, pageable: Pageable) :  Page<Product>
}