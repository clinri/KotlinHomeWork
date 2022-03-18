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

const val VK_PAY = 0
const val VISA = 1
const val MASTER_CARD = 2
const val MAESTRO = 3
const val MIR = 4
fun main() {
    println(pay(5_200_11))
    println(pay(15_700_05, VISA))
    println(pay(1_800_00))
    println(pay(100_00))
    println(pay(20_700_00, MASTER_CARD))
    println(pay(20_700_00, MASTER_CARD))
    println(pay(20_700_00, MASTER_CARD))
    println(pay(20_700_00, MASTER_CARD))
    println(pay(9_200_00))
    println(pay(40_909_00, MIR))
}

fun pay(amount: Int, currentSumPay: Int = 0, typeCard: Int = VK_PAY): String {
    return "С суммы ${intKopToRubAndKop(amount)}, " +
            "будет взята комиссия ${intKopToRubAndKop(calcCommission(amount, currentSumPay, typeCard))}"
}

fun calcCommission(amount: Int, currentSumPay: Int = 0, typeCard: Int = VK_PAY): Int = when (typeCard) {
    VISA, MIR -> commissionByVisaAndMir(amount)
    MASTER_CARD, MAESTRO -> commissionByMasterCardAndMaestro(amount, currentSumPay)
    VK_PAY -> 0
    else -> 0
}

fun commissionByMasterCardAndMaestro(amount: Int, currentSumPay: Int = 0): Int {
    val max = 75_000_00
    val persentComission = 6 //0,6%
    val constPartComission = 20
    return if (
        currentSumPay >= max ||
        currentSumPay + amount >= max
    )
        amount * persentComission / 1000 + constPartComission else 0
}


fun commissionByVisaAndMir(amount: Int): Int {
    val minCommission = 35_00
    val persentCommission = 75 //0,75%
    return if (amount * persentCommission / 10000 > minCommission) {
        amount * persentCommission / 10000
    } else minCommission
}

fun intKopToRubAndKop(kop: Int): String {
    val kopeiki = String.format("%02d", kop % 100)
    return "${kop / 100} руб. $kopeiki коп."
}