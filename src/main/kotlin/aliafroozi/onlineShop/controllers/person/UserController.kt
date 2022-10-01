package aliafroozi.onlineShop.controllers.person

import aliafroozi.onlineShop.models.person.User
import aliafroozi.onlineShop.services.person.UserService
import aliafroozi.onlineShop.utils.NotFoundException
import aliafroozi.onlineShop.utils.ServiceResponse
import aliafroozi.onlineShop.viewModels.UserViewModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/user")
class UserController {

    @Autowired
    lateinit var service: UserService


    @PostMapping("/register")
    fun addUser(@RequestBody userViewModel: UserViewModel): ServiceResponse<User> {
        return try {
            val data = service.insert(userViewModel.convertToUser())
            if (data == null)
                throw NotFoundException("user not found") else
                ServiceResponse(data = listOf(data), status = HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("/update")
    fun updateUser(@RequestBody userViewModel: UserViewModel): ServiceResponse<User> {
        return try {
            val data = service.update(userViewModel.convertToUser())
            if (data == null)
                throw NotFoundException("user not found") else
                ServiceResponse(data = listOf(data), status = HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("/changePassword")
    fun changePass(@RequestBody userViewModel: UserViewModel): ServiceResponse<User> {
        return try {
            val data = service.changePassword(userViewModel.convertToUser() , userViewModel.repeatPass)
            if (data == null)
                throw NotFoundException("user not found") else
                ServiceResponse(data = listOf(data), status = HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody userViewModel: UserViewModel): ServiceResponse<User> {
        return try {
            val data = service.getByUsernameAndPass(userViewModel.userName , userViewModel.password)
            if (data == null)
                throw NotFoundException("user not found") else
                ServiceResponse(data = listOf(data), status = HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @GetMapping("/{userId}")
    fun getById(@PathVariable userId: Long): ServiceResponse<User> {
        return try {
            val data = service.getById(userId)
            if (data == null)
                throw NotFoundException("user not found") else
                ServiceResponse(data = listOf(data), status = HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }



}
