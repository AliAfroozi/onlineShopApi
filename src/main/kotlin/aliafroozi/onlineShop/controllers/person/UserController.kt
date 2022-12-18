package aliafroozi.onlineShop.controllers.person

import aliafroozi.onlineShop.config.JwtTokenUtil
import aliafroozi.onlineShop.models.person.User
import aliafroozi.onlineShop.services.person.UserService
import aliafroozi.onlineShop.utils.exceptions.NotFoundException
import aliafroozi.onlineShop.utils.ServiceResponse
import aliafroozi.onlineShop.utils.UserUtil.Companion.getCurrentUsernameFromToken
import aliafroozi.onlineShop.viewModels.UserViewModel
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest

@RestController
@RequestMapping("api/user")
class UserController {



    @Autowired
    lateinit var service: UserService

    @Autowired
    private lateinit var jwtUtil : JwtTokenUtil


    @PostMapping("/register")
    fun addUser(@RequestBody userViewModel: UserViewModel): ServiceResponse<User> {
        return try {
            val data = service.insert(userViewModel.convertToUser())
            ServiceResponse(data = listOf(data), status = HttpStatus.OK)
        } catch (e: NotFoundException) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PutMapping("/update")
    fun updateUser(@RequestBody userViewModel: UserViewModel , request : HttpServletRequest ): ServiceResponse<User> {
        return try {
            val currentUser = getCurrentUsernameFromToken(jwtUtil , request)
            val data = service.update(userViewModel.convertToUser() , currentUser)
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
    fun changePass(@RequestBody userViewModel: UserViewModel , request: HttpServletRequest): ServiceResponse<UserViewModel> {
        return try {
            val currentUser = getCurrentUsernameFromToken(jwtUtil , request)
            val data = service.changePassword(userViewModel.convertToUser() , userViewModel.oldPassword ,  userViewModel.repeatPass , currentUser)
            if (data == null)
                throw NotFoundException("user not found") else{
                val vm = UserViewModel(data)
                vm.token  = jwtUtil.generateToken(vm)
                ServiceResponse(data = listOf(vm), status = HttpStatus.OK)

            }
        } catch (e: NotFoundException) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }

    @PostMapping("/login")
    fun login(@RequestBody userViewModel: UserViewModel): ServiceResponse<UserViewModel> {
        return try {
            val data = service.getByUsernameAndPass(userViewModel.userName , userViewModel.password)

            if (data == null)
                throw NotFoundException("user not found") else{
                val vm = UserViewModel(data)
                vm.token = jwtUtil.generateToken(vm)
                ServiceResponse(data = listOf(vm), status = HttpStatus.OK)
            }

        } catch (e: NotFoundException) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.NOT_FOUND)
        } catch (e: Exception) {
            ServiceResponse(message = "${e.message}", status = HttpStatus.INTERNAL_SERVER_ERROR)
        }
    }


    @GetMapping("")
    fun getByUsername(@PathVariable username: String , request: HttpServletRequest): ServiceResponse<User> {
        return try {
            val currentUser = getCurrentUsernameFromToken(jwtUtil , request)
            val data = service.getByUsername(currentUser)
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
