fun polynomial(x: Int, c: Int = 0, b: Int = 0, a: Int = 0): Int {
    return when {
        b == 0 -> c
        a == 0 -> b * x + c
        else -> a * x * x + b * x + c
    }
}