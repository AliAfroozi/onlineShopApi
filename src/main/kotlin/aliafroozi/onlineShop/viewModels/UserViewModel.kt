package aliafroozi.onlineShop.viewModels

import aliafroozi.onlineShop.models.person.Person
import aliafroozi.onlineShop.models.person.User

class UserViewModel(
    var id: Long = 0,
    var userName: String = "",
    var password: String = "",
    var repeatPass: String = "",
    var oldPassword : String = "",
    var personId: Long = 0,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var phone: String = "",
    var postalCode: String = "",
    var token : String = ""

    ) {
    constructor(user : User) : this(
        id = user.id,
        userName = user.userName,
        firstName = user.person!!.firstName,
        lastName = user.person!!.lastName,
        address = user.person!!.address,
        phone = user.person!!.phone,
        postalCode = user.person!!.postalCode   ,
    )

    fun convertToUser(): User {
        return User(id, userName, password, convertToPerson())
    }

    fun convertToPerson(): Person {
        return Person(personId , firstName , lastName , address ,phone ,postalCode)
    }
}