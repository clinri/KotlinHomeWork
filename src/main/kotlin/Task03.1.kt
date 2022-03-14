/** Задача №1 - «Только что»
 * В чатах социальных сетей и мессенджерах достаточно часто показывают информацию о том,
 * когда ваш собеседник последний раз был онлайн:
 * Используя when, напишите функцию [agoToText] (скорее всего, вам понадобится не одна функция),
 * которая переводит количество секунд, которые человек был назад в сети в соответствующий текст (String).
 *
 * Нас будет интересовать вся фраза, например, был(а) только что или был(а) в сети 3 часа назад.
 *
 * Какие правила:
 * Если количество секунд от 0 до 60, работает вариант с только что.
 * Если количество секунд от 61 до 60 * 60 (один час), работает вариант с x минут назад.
 * Если количество секунд от 60 * 60 + 1 до 24 * 60 * 60 (сутки) и , работает вариант с x часов назад.
 * Если количество секунд от суток до двух, то - сегодня.
 * Если количество секунд от двух суток до трёх, то - вчера.
 * Если количество секунд больше трёх суток, то - давно.
 * Обратите внимание! Вам понадобятся вспомогательные функции, которые умеют делать:
 *      1 минуту назад.
 *      2 минуты назад.
 *      5 минут назад.
 *      11 минут назад.
 *      21 минуту назад.
 *      25 минут назад.
 * То же самое с часами, потому что с часами будет час, часа, часов.
 *
 * Итог: у вас должен быть репозиторий на GitHub, в котором будет ваш Gradle-проект.
 */

val w = "назад"

fun main() {
    println(agoToText(40))
    println(agoToText(60 * 15))
    println(agoToText(3600 * 16))
    println(agoToText(24 * 3600 * 2))
    println(agoToText((24 * 3600 * 0.5).toInt()))
}

fun agoToText(seconds: Int): String = when (seconds) {
    in 0..60 -> "Только что"
    in 61..60 * 60 -> minToText(seconds / 60)
    in 60 * 60 + 1..24 * 60 * 60 -> hoursToText(seconds / 3600)
    in 24 * 3600 + 1..24 * 3600 * 2 -> "сегодня"
    in 24 * 3600 * 2 + 1..24 * 3600 * 3 -> "вчера"
    in 24 * 3600 * 3 + 1..Int.MAX_VALUE -> "давно"
    else -> ""
}

fun minToText(min: Int): String = when (min % 10) {
    1 -> "минуту назад"
    in 2..4 -> "$min минуты $w"
    0, in 5..9 -> "$min минут $w"
    else -> ""
}

fun hoursToText(hours: Int): String = when (hours) {
    1, 21 -> "час $w"
    in 2..4, 22, 23 -> "$hours часа $w"
    in 5..20 -> "$hours часов $w"
    else -> ""
}


