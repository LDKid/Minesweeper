import kotlin.random.Random

fun generatePredictablePassword(seed: Int): String {
    var randomPassword = ""
    val rng = Random(seed)
    repeat(10) {
        randomPassword += rng.nextInt(33, 127).toChar()
    }
    return randomPassword
}