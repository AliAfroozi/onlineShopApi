package aliafroozi.onlineShop.controllers.otherContent

import aliafroozi.onlineShop.models.otherContent.Blog
import aliafroozi.onlineShop.services.otherContent.BlogService
import aliafroozi.onlineShop.utils.exceptions.NotFoundException
import aliafroozi.onlineShop.utils.ServiceResponse
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/blog")
class BlogController {

    @Autowired
    lateinit var service: BlogService

    @GetMapping("")
    fun getAllBlogPaged(@RequestParam pageIndex :Int, @RequestParam pageSize: Int): ServiceResponse<Blog> {
        return try {
            ServiceResponse(data = service.getAll(pageIndex , pageSize), status = HttpStatus.OK)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/all")
    fun getAllBlog(): ServiceResponse<Blog> {
        return try {
            ServiceResponse(data = service.getAll(), status = HttpStatus.OK)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ServiceResponse<Blog> {
        return try {
            val data = service.getById(id)
            if (data == null)
                throw NotFoundException("blog not found") else
                ServiceResponse(data = listOf(data), status = HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }
}
