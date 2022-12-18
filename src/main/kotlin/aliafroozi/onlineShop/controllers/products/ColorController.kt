package aliafroozi.onlineShop.controllers.products

import aliafroozi.onlineShop.models.product.Color
import aliafroozi.onlineShop.services.products.ColorService
import aliafroozi.onlineShop.utils.exceptions.NotFoundException
import aliafroozi.onlineShop.utils.ServiceResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/color")
class ColorController {

    @Autowired
    lateinit var service: ColorService

    @GetMapping("")
    fun getAllColor(): ServiceResponse<Color> {
        return try {
            ServiceResponse(data = service.getAll(), status = HttpStatus.OK)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ServiceResponse<Color> {
        return try {
            val data = service.getById(id)
            if (data == null)
                throw NotFoundException("color not found") else
                ServiceResponse(data = listOf(data), status = HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
