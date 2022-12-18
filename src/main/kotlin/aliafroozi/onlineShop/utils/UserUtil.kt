package aliafroozi.onlineShop.utils

import aliafroozi.onlineShop.config.JwtTokenUtil
import aliafroozi.onlineShop.utils.exceptions.JwtTokenException
import javax.servlet.http.HttpServletRequest

class UserUtil {
    companion object{
         fun getCurrentUsernameFromToken(jwtUtil : JwtTokenUtil , request: HttpServletRequest): String {
            val header = request.getHeader("Authorization")
            if (header == null || !header.lowercase().startsWith("bearer")) {
                throw JwtTokenException("jwt token is not set correctly")
            }
            val token = header.substring(7)
            return jwtUtil.getUsernameFromToken(token)
        }
    }
}