package aliafroozi.onlineShop.config

import aliafroozi.onlineShop.models.person.User
import aliafroozi.onlineShop.viewModels.UserViewModel
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.hibernate.usertype.UserType
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.Serializable
import java.util.*
import java.util.function.Function

@Component
class JwtTokenUtil : Serializable {

    val ACCESS_TOKEN_VALIDITY_SECONDS = (5*80*80).toLong()

    @Value("\${jwt.secret}")
    private val SIGNING_KEY : String? = null

    fun getUsernameFromToken(token: String): String {
        return getClaimFromToken(token, Function { it.subject })
    }

    fun getExpirationDateFromToken(token: String): Date {
        return getClaimFromToken(token, Function { it.expiration })
    }

    fun <T> getClaimFromToken(token: String, claimsResolver: Function<Claims, T>): T {
        val claims = getAllClaimsFromToken(token)
        return claimsResolver.apply(claims)
    }

    private fun getAllClaimsFromToken(token: String): Claims {
        return Jwts.parser()
            .setSigningKey(SIGNING_KEY)
            .parseClaimsJws(token)
            .body
    }

    private fun isTokenExpired(token: String): Boolean {
        val expiration = getExpirationDateFromToken(token)
        return expiration.before(Date())
    }

    fun generateToken(user: UserViewModel): String {
        return doGenerateToken(user.userName)
    }

    private fun doGenerateToken(subject: String): String {

        val claims = Jwts.claims().setSubject(subject)
        return Jwts.builder()
            .setClaims(claims)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date(System.currentTimeMillis() + ACCESS_TOKEN_VALIDITY_SECONDS * 1000))
            .signWith(SignatureAlgorithm.HS256, SIGNING_KEY)
            .compact()
    }

    fun validateToken(token: String, userDetails: UserViewModel): Boolean {
        val username = getUsernameFromToken(token)
        return username == userDetails.userName && !isTokenExpired(token)
    }

}