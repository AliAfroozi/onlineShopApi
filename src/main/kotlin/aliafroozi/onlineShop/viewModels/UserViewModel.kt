package aliafroozi.onlineShop.viewModels

import aliafroozi.onlineShop.models.person.Person
import aliafroozi.onlineShop.models.person.User

class UserViewModel(
    var id: Long = 0,
    var userName: String = "",
    var password: String = "",
    var repeatPass: String = "",

    var personId: Long = 0,
    var firstName: String = "",
    var lastName: String = "",
    var address: String = "",
    var phone: String = "",
    var postalCode: String = "",

    ) {

    fun convertToUser(): User {
        return User(id, userName, password, convertToPerson())
    }

    fun convertToPerson(): Person {
        return Person(personId , firstName , lastName , address ,phone ,postalCode)
    }
}