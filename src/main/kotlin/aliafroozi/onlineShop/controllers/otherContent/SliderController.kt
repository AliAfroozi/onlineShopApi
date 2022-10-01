package aliafroozi.onlineShop.controllers.otherContent

import aliafroozi.onlineShop.models.otherContent.Slider
import aliafroozi.onlineShop.services.otherContent.SliderService
import aliafroozi.onlineShop.utils.NotFoundException
import aliafroozi.onlineShop.utils.ServiceResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/slider")
class SliderController {
    @Autowired
    lateinit var service: SliderService

    @GetMapping("")
    fun getAllSlider(): ServiceResponse<Slider> {
        return try {
            ServiceResponse(data = service.getAll(), status = HttpStatus.OK)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ServiceResponse<Slider> {
        return try {
            val data = service.getById(id)
            if (data == null)
                throw NotFoundException("slider not found") else
                ServiceResponse(data = listOf(data), status = HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
