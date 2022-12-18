package aliafroozi.onlineShop.controllers.otherContent

import aliafroozi.onlineShop.models.otherContent.Content
import aliafroozi.onlineShop.services.otherContent.ContentService
import aliafroozi.onlineShop.utils.exceptions.NotFoundException
import aliafroozi.onlineShop.utils.ServiceResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("api/content")
class ContentController {

    @Autowired
    lateinit var service: ContentService

    @GetMapping("")
    fun getAllContent(): ServiceResponse<Content> {
        return try {
            ServiceResponse(data = service.getAll(), status = HttpStatus.OK)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{title}")
    fun getByTitle(@PathVariable title: String): ServiceResponse<Content> {
        return try {
            val data = service.getByTitle(title)
            if (data == null)
                throw NotFoundException("content not found") else
                ServiceResponse(data = listOf(data), status = HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
