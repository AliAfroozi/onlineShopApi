package aliafroozi.onlineShop.controllers.products

import aliafroozi.onlineShop.models.product.Product
import aliafroozi.onlineShop.services.products.ProductService
import aliafroozi.onlineShop.utils.NotFoundException
import aliafroozi.onlineShop.utils.ServiceResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/product")
class ProductController {

    @Autowired
    lateinit var service: ProductService


    @GetMapping("/all")
    fun getAllProduct(): ServiceResponse<Product> {
        return try {
            ServiceResponse(data = service.getAll(), status = HttpStatus.OK)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("")
    fun getAllProductPaged(@RequestParam pageIndex :Int, @RequestParam pageSize: Int): ServiceResponse<Product> {
        return try {
            ServiceResponse(data = service.getAllPaged(pageIndex , pageSize), status = HttpStatus.OK)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/new")
    fun getNewProducts(): ServiceResponse<Product> {
        return try {
            ServiceResponse(data = service.getNewProducts(), status = HttpStatus.OK)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/popular")
    fun getPopularProducts(): ServiceResponse<Product> {
        return try {
            ServiceResponse(data = service.getPopularProducts(), status = HttpStatus.OK)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ServiceResponse<Product> {
        return try {
            val data = service.getById(id)
            if (data == null)
                throw NotFoundException("product not found") else
                ServiceResponse(data = listOf(data), status = HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
