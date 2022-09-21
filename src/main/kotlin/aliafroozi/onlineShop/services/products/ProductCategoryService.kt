package aliafroozi.onlineShop.services.products

import aliafroozi.onlineShop.models.product.ProductCategory
import aliafroozi.onlineShop.repositories.products.ProductCategoryRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductCategoryService {

    @Autowired
    lateinit var repository: ProductCategoryRepo

    fun getById(ProductCategoryId: Long): ProductCategory? {
        val data = repository.findById(ProductCategoryId)
        if (data.isEmpty)
            return null
        else
            return data.get()
    }

    fun getAll(): List<ProductCategory> {
        return repository.findAll()
    }


    fun getAllCount(): Long {
        return repository.count()
    }
}