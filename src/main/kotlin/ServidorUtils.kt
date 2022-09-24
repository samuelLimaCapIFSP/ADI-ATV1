object ServidorUtils {

    fun calcSomatorio(x: Int, y: Int): Int {
        var soma = 0;
        for (i in x..y) {
            if (i % 2 == 0) soma += i
        }
        return soma
    }

}