package aliafroozi.onlineShop.utils

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import java.io.Serializable
import javax.swing.ListCellRenderer

data class ServiceResponse<T>(
    var data: List<T>? = null,
    var message: String = "",
    var status: HttpStatus,
    var totalCount : Long = 0
) : Serializable