package aliafroozi.onlineShop.services.person

import aliafroozi.onlineShop.models.person.Person
import aliafroozi.onlineShop.models.person.User
import aliafroozi.onlineShop.repositories.person.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class  UserService {

    @Autowired
    lateinit var repository: UserRepo
    @Autowired
    lateinit var personService: PersonService

    fun insert(user: User): User {
        if (user.userName.isEmpty())
            throw Exception("please enter username")
        if (user.password.isEmpty())
            throw Exception("please enter password")
        personService.insert(user.person)

        return repository.save(user)
    }

    fun update(user: User): User? {
        val data = getById(user.id) ?: return null
        personService.update(user.person!!)
        data.password = ""
        return repository.save(data)
    }

    fun changePassword(user: User, repeatPass: String): User? {
        val data = getById(user.id) ?: return null
        if (user.password.isEmpty())
            throw Exception("password is empty")
        if (user.password != repeatPass)
            throw Exception("password is not match with repeat pass ")
        data.password = user.password
        val saveData = repository.save(data)
        saveData.password = ""

        return saveData
    }

    fun getById(UserId: Long): User? {
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

    fun getByUsernameAndPass(username: String, password: String): User? {
        if (username.isEmpty())
            throw Exception("username is empty")
        if (password.isEmpty())
            throw Exception("password is empty")
        return repository.findFirstByUserNameAndPassword(username, password)

    }
}