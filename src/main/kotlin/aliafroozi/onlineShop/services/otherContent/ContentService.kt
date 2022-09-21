package aliafroozi.onlineShop.services.otherContent

import aliafroozi.onlineShop.models.otherContent.Content
import aliafroozi.onlineShop.repositories.otherContent.ContentRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ContentService {

    @Autowired
    lateinit var repository: ContentRepo

    fun getById(ContentId: Long): Content? {
        val data = repository.findById(ContentId)
        if (data.isEmpty)
            return null
        else
            return data.get()
    }

    fun getAll(): List<Content> {
        return repository.findAll()
    }

    fun getByTitle(title: String): Content? {
        return repository.findFirstByTitle(title)
    }


}