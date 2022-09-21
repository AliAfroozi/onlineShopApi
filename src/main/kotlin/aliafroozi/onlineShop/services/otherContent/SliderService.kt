package aliafroozi.onlineShop.services.otherContent

import aliafroozi.onlineShop.models.otherContent.Slider
import aliafroozi.onlineShop.repositories.otherContent.SliderRepo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class SliderService {

    @Autowired
    lateinit var repository: SliderRepo

    private fun insert(slider: Slider): Slider {
        return repository.save(slider)
    }

    private fun update(slider: Slider): Slider? {
        val data = getById(slider.id)
        if (data == null)
            return null
        else{
            data.image = slider.image
            data.title = slider.title
            data.subTitle= slider.subTitle
            data.link = slider.link
            return repository.save(data)
        }
    }

    fun getById(SliderId: Long): Slider? {
        val data = repository.findById(SliderId)
        if (data.isEmpty)
            return null
        else
            return data.get()
    }

    fun getAll(): List<Slider> {
        return repository.findAll()
    }

    private fun delete(sliderId: Long): Boolean {
        repository.deleteById(sliderId)
        return true
    }

    fun getAllCount(): Long {
        return repository.count()
    }
}