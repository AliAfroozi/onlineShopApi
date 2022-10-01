package aliafroozi.onlineShop.services.person

import aliafroozi.onlineShop.models.person.Person
import aliafroozi.onlineShop.repositories.person.PersonRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class PersonService  {

    @Autowired
    private lateinit var repository : PersonRepo

    fun insert(person: Person?): Person {
        if (person == null)
            throw Exception("person is null")
        if (person.firstName.isEmpty())
            throw Exception("firstName is null")
        if (person.lastName.isEmpty())
            throw Exception("lastName is null")
        if (person.phone.isEmpty())
            throw Exception("phone is null")

        return repository.save(person)
    }

     fun  update(Person: Person): Person? {
        val data = getById(Person.id)
        if (data == null)
            return null
        else{
            data.address = Person.address
            data.firstName = Person.firstName
            data.lastName= Person.lastName
            data.phone = Person.phone
            data.postalCode = Person.postalCode
            return repository.save(data)
        }
    }

    fun getById(PersonId: Long): Person? {
        val data = repository.findById(PersonId)
        if (data.isEmpty)
            return null
        else
            return data.get()
    }

}