fun f(x: Double): Double = when {
    x <= 0 -> f1(x)
    x >= 1 -> f3(x)
    else -> f2(x)
}

fun f1(x: Double): Double = x * x + 1

fun f2(x: Double): Double = 1 / (x * x)

fun f3(x: Double): Double = x * x - 1