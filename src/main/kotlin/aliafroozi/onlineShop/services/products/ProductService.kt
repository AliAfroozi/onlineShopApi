package aliafroozi.onlineShop.services.products

import aliafroozi.onlineShop.models.product.Product
import aliafroozi.onlineShop.repositories.products.ProductRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ProductService {

    @Autowired
    lateinit var repository: ProductRepo

    private fun insert(Product: Product): Product {
        return repository.save(Product)
    }

    private fun update(Product: Product): Product? {
        val data = getById(Product.id)
        if (data == null)
            return null
        else{
            data.image = Product.image
            data.title = Product.title
            data.description= Product.description
            data.size = Product.size
            data.colors = Product.colors
            return repository.save(data)
        }
    }

    fun getById(ProductId: Long): Product? {
        val data = repository.findById(ProductId)
        if (data.isEmpty)
            return null
        else
            return data.get()
    }

    fun getAll(): List<Product> {
        return repository.findAll()
    }

    private fun delete(ProductId: Long): Boolean {
        repository.deleteById(ProductId)
        return true
    }

    fun getNewProducts(): List<Product> {
        return repository.findTop6ByOrderByAddDateDesc()
    }

    fun getPopularProducts() : List<Product>{
        return repository.findTop6ByOrderByVisitCountDesc()
    }

    fun getAllCount(): Long {
        return repository.count()
    }
}