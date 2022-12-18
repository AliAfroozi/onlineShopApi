package aliafroozi.onlineShop.controllers.products

import aliafroozi.onlineShop.models.product.ProductCategory
import aliafroozi.onlineShop.services.products.ProductCategoryService
import aliafroozi.onlineShop.utils.exceptions.NotFoundException
import aliafroozi.onlineShop.utils.ServiceResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/productCategory")
class ProductCategoryController {

    @Autowired
    lateinit var service: ProductCategoryService

    @GetMapping("")
    fun getAllProductCategory(): ServiceResponse<ProductCategory> {
        return try {
            ServiceResponse(data = service.getAll(), status = HttpStatus.OK)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ServiceResponse<ProductCategory> {
        return try {
            val data = service.getById(id)
            if (data == null)
                throw NotFoundException("productCategory not found") else
                ServiceResponse(data = listOf(data), status = HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
