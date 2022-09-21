package aliafroozi.onlineShop.repositories.person

import aliafroozi.onlineShop.models.person.Person
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface PersonRepo : PagingAndSortingRepository<Person ,Long> {
    override fun findAll(): List<Person>

}
