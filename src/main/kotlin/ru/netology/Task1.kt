/** за переводы с любых карт взимается комиссия 0.75%, минимум 35 рублей
 * Что нужно сделать: напишите небольшую программу, в которой в переменной [amount] хранится сумма перевода в копейках.
 * Ваше приложение должно высчитывать комиссию, которую заплатит пользователь при переводе — комиссия также должна быть в копейках.
 */
package ru.netology

val amount = 4_000_00
fun main() {
    println(
        "С суммы ${amount / 100} руб. ${amount % 100} коп., " +
                "будет взята комиссия ${commissionCalculation(amount) / 100} руб. ${commissionCalculation(amount) % 100} коп,"
    )
}

fun commissionCalculation(amount: Int): Int {
    val minComission = 35_00
    val persentComission = 75 //0,75
    return if (amount / 10000 * persentComission > minComission) {
        amount / 10000 * persentComission
    } else minComission
}


