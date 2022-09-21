package aliafroozi.onlineShop.services.otherContent

import aliafroozi.onlineShop.models.otherContent.Blog
import aliafroozi.onlineShop.repositories.otherContent.BlogRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service

@Service
class BlogService  {

    @Autowired
    lateinit var repository : BlogRepo

    fun getById(blogId : Long): Blog? {
        val data =  repository.findById(blogId)
        if (data.isEmpty)
            return null
        else
            return data.get()
    }

    fun getAll(): List<Blog> {
        return repository.findAll()
    }

    fun getAll(pageIndex :Int , pageSize : Int): List<Blog> {
        val pageRequest = PageRequest.of(pageIndex , pageSize , Sort.by("id"))
        return repository.findAll(pageRequest).toList()
    }

    fun getAllCount(): Long {
        return repository.count()
    }
}