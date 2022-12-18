package aliafroozi.onlineShop.config.filters

import aliafroozi.onlineShop.config.JwtTokenUtil
import aliafroozi.onlineShop.services.person.UserService
import aliafroozi.onlineShop.utils.exceptions.JwtTokenException
import aliafroozi.onlineShop.viewModels.UserViewModel
import io.jsonwebtoken.ExpiredJwtException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.servlet.Filter
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class JwtRequestFilter : Filter {

    @Autowired
    private lateinit var jwtTokenUtil: JwtTokenUtil

    @Autowired
    private lateinit var userService: UserService

    private  var excludeUrls : ArrayList<String>  = ArrayList<String>()
    private  var containsExcludeUrls : ArrayList<String> = ArrayList<String>()

    init {
        excludeUrls.add("/api/user/login")
        excludeUrls.add("/api/user/register")

        containsExcludeUrls.add("/api/color")
        containsExcludeUrls.add("/api/product")
        containsExcludeUrls.add("/api/blog")
        containsExcludeUrls.add("/api/content")
        containsExcludeUrls.add("/api/slider")
        containsExcludeUrls.add("/api/productCategory")
    }

    override fun doFilter(request: ServletRequest?, response: ServletResponse?, chain: FilterChain?) {
        try {
            val url = (request as HttpServletRequest).requestURI.lowercase()
            if (excludeUrls.stream().anyMatch { x-> x == url.lowercase() } || containsExcludeUrls.stream().anyMatch{
                x -> url.startsWith(x.lowercase())
                }){
                chain!!.doFilter(request , response)
                return
            }

            val header = request.getHeader("Authorization")
            if (header == null || !header.startsWith("bearer"))
                throw JwtTokenException("jwt is not set.")
            val token = header.substring(7)
            val userName = jwtTokenUtil.getUsernameFromToken(token)
            val user = userService.getByUsername(userName)
            if (user == null){
                throw JwtTokenException("this token is not correct")
            }else {
                val userViewModel = UserViewModel(user)
                if (!jwtTokenUtil.validateToken(token ,  userViewModel))
                    throw JwtTokenException("invalid token")
            }
            chain!!.doFilter(request , response)
        }catch (e: JwtTokenException){
            (response as HttpServletResponse).sendError(HttpServletResponse.SC_UNAUTHORIZED , e.message)
        }catch (e: ExpiredJwtException){
            (response as HttpServletResponse).sendError(HttpServletResponse.SC_EXPECTATION_FAILED , e.message)
        }catch (e : Exception){
            (response as HttpServletResponse).sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR , e.message)
        }
    }
}