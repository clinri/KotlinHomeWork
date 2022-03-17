/** Задача №2 - Разная комиссия
 * В прошлый раз мы рассматривали упрощённый вариант вычисления комиссии. Пришла пора сделать нормальный.
 *
 * Q: Почему?
 *
 * A: Потому что так дешевле пользователям. За MasterCard и Maestro вообще не нужно платить пока
 * не превысили лимит (замечание от 300 убираем), а для VK Pay всегда бесплатно.
 * Напишите алгоритм расчёта в виде функции, передавая в функцию:
 *
 * Тип карты/счёта (по умолчанию - Vk Pay).
 * Сумму предыдущих переводов в этом месяце (по умолчанию - 0).
 * Сумму совершаемого перевода.
 * Функция по-прежнему должна возвращать комиссию в копейках.
 *
 * Итог: у вас должен быть репозиторий на GitHub, в котором будет ваш Gradle-проект.
 */

package ru.netology

val VK_PAY = 0
val VISA = 1
val MASTER_CARD = 2
val MAESTRO = 3
val MIR = 4
var sumInMonthForMcAndMaestro = 0
fun main() {
    pay(5_200_00)
    pay(15_700_00, VISA)
    pay(1_800_00)
    pay(100_00)
    pay(20_700_00, MASTER_CARD)
    pay(20_700_00, MASTER_CARD)
    pay(20_700_00, MASTER_CARD)
    pay(20_700_00, MASTER_CARD)
    pay(9_200_00)
    pay(40_909_00, MIR)
}

fun pay(amount: Int, typeCard: Int = VK_PAY) {
    println(
        "С суммы ${intKopToRubAndKop(amount)}, " +
                "будет взята комиссия ${intKopToRubAndKop(calcComission(amount, typeCard))}"
    )
}

fun calcComission(amount: Int, typeCard : Int = VK_PAY): Int = when (typeCard) {
    VISA, MIR -> comissionByVisaAndMir(amount)
    MASTER_CARD, MAESTRO -> comissionByMasterCardAndMaestro(amount)
    VK_PAY -> 0
    else -> 0
}

fun comissionByMasterCardAndMaestro(amount: Int): Int {
    val max = 75_000_00
    val persentComission = 6 //0,6
    sumInMonthForMcAndMaestro += amount
    return if (sumInMonthForMcAndMaestro < max) 0 else amount * persentComission / 10000 + 20
}


fun comissionByVisaAndMir(amount: Int): Int {
    val minCommission = 35_00
    val persentCommission = 75 //0,75
    return if (amount * persentCommission / 10000 > minCommission) {
        amount * persentCommission / 10000
    } else minCommission
}

fun intKopToRubAndKop(kop: Int): String {
    return "${kop / 100} руб. ${kop % 100} коп."
}