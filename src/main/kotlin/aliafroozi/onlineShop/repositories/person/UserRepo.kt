package aliafroozi.onlineShop.repositories.person

import aliafroozi.onlineShop.models.person.User
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepo : PagingAndSortingRepository<User, Long> {
    fun findFirstByUserNameAndPassword(userName: String, Password: String): User?
    fun findFirstByUserName(userName: String): User?
}
