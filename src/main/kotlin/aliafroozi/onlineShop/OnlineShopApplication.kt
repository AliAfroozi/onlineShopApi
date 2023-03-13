package aliafroozi.onlineShop

import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.EnableWebMvc

@SpringBootApplication
class OnlineShopApplication

fun main(args: Array<String>) {
	runApplication<OnlineShopApplication>(*args)
}
