package aliafroozi.onlineShop.services.products

import aliafroozi.onlineShop.models.product.Color
import aliafroozi.onlineShop.repositories.products.ColorRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ColorService {

    @Autowired
    lateinit var repository: ColorRepo


    fun getById(ColorId: Long): Color? {
        val data = repository.findById(ColorId)
        if (data.isEmpty)
            return null
        else
            return data.get()
    }

    fun getAll(): List<Color> {
        return repository.findAll()
    }

    fun getAllCount(): Long {
        return repository.count()
    }

}