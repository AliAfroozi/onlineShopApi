package aliafroozi.onlineShop.repositories.otherContent

import aliafroozi.onlineShop.models.otherContent.Content
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ContentRepo : PagingAndSortingRepository<Content , Long> {
    override fun findAll(): List<Content>
    fun findFirstByTitle(title : String) : Content?
}