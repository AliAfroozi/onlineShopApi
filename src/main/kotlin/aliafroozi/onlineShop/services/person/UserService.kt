package aliafroozi.onlineShop.services.person

import aliafroozi.onlineShop.models.person.Person
import aliafroozi.onlineShop.models.person.User
import aliafroozi.onlineShop.repositories.person.UserRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class UserService {

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

    fun update(user: User, currentUser: String): User? {
        val data = getById(user.id) ?: return null
        val cUser = repository.findFirstByUserName(currentUser)
        if (cUser == null || data.id != cUser.id)
            throw Exception("You do not have permission to edit this user's info.")
        personService.update(user.person!!)
        data.password = ""
        return repository.save(data)
    }

    fun changePassword(user: User, oldPass: String, repeatPass: String, currentUsername: String): User? {
        val data = getById(user.id) ?: return null
        val currentUserFromToken = repository.findFirstByUserName(currentUsername)
        if (currentUserFromToken == null || currentUserFromToken.id != data.id){
            throw Exception("You don't have permission to change this user's password.")
        }
        if (user.password.isEmpty())
            throw Exception("password is empty")
        if (user.password != repeatPass)
            throw Exception("password is not match with repeat pass ")
        if (data.password != oldPass)
            throw Exception(" invalid current password ")
        data.password = user.password
        val saveData = repository.save(data)
        saveData.password = ""

        return saveData
    }

    fun getById(UserId: Long): User? {
        val data = repository.findById(UserId)
        return if (data.isEmpty)
            null
        else
            data.get()
    }

    fun getByUsername(currentUser: String): User? {
        if (currentUser.isEmpty())
            throw Exception("username is empty")
        return repository.findFirstByUserName(currentUser)
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