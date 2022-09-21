package aliafroozi.onlineShop.services.person

import aliafroozi.onlineShop.models.person.User
import aliafroozi.onlineShop.repositories.person.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

    @Autowired
    lateinit var repository: UserRepo

    fun insert(User: User): User {
        return repository.save(User)
    }

     fun update(user: User): User? {
        val data = getById(user.id)
        if (data == null)
            return null
        else{
            data.password = user.password
            return repository.save(data)
        }
    }

    private fun getById(UserId: Long): User? {
        val data = repository.findById(UserId)
        if (data.isEmpty)
            return null
        else
            return data.get()
    }

     fun delete(UserId: Long): Boolean {
        repository.deleteById(UserId)
        return true
    }

    fun getByUsernameAndPass(username: String , password : String): User? {
        return repository.findFirstByUserNameAndPassword(username , password)
    }
}