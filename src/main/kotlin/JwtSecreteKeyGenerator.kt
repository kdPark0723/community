import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys

class JwtSecreteKeyGenerator {

    fun generate(): ByteArray {
        val key = Keys.secretKeyFor(SignatureAlgorithm.HS512)
        return key.encoded
    }
}

fun main(args: Array<String>) {
    val keyGenerator = JwtSecreteKeyGenerator()
    val key = keyGenerator.generate()

    print("Secrete Key: ")
    for (i in 0 until key.size)
        print(key[i])
    println()

}