package aliafroozi.onlineShop.utils

import com.google.common.hash.HashCode
import com.google.common.hash.HashFunction
import com.google.common.hash.Hashing

class Sha256Hash {
    companion object{
        fun encryptSha256(plainText: String): String {
            val hashFunction: HashFunction = Hashing.sha256()
            val hc: HashCode = hashFunction
                .newHasher()
                .putString(plainText, Charsets.UTF_8)
                .hash()
            return hc.toString()
        }
    }
}