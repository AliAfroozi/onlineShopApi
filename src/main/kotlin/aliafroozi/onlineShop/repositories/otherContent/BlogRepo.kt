package aliafroozi.onlineShop.repositories.otherContent

import aliafroozi.onlineShop.models.otherContent.Blog
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface BlogRepo : PagingAndSortingRepository<Blog ,Long> {
    override fun findAll(): List<Blog>
}
