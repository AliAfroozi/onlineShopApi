package aliafroozi.onlineShop.services.person

import aliafroozi.onlineShop.models.person.Person
import aliafroozi.onlineShop.models.person.User
import aliafroozi.onlineShop.repositories.person.UserRepo
import aliafroozi.onlineShop.utils.Sha256Hash
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

        val checkAlreadyExist = getByUsername(user.userName)
        if (checkAlreadyExist != null){
            throw Exception("This username is already Exists.")
        }else{
            personService.insert(user.person)
            user.password = Sha256Hash.encryptSha256(user.password)
            val savedData = repository.save(user)
            savedData.password = ""
            return savedData
        }

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
        if (data.password != Sha256Hash.encryptSha256(oldPass))
            throw Exception(" invalid current password ")
        data.password = Sha256Hash.encryptSha256(user.password)
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
        val encryptedPassword = Sha256Hash.encryptSha256(password)
        return repository.findFirstByUserNameAndPassword(username, encryptedPassword)

    }
}