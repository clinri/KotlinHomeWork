package ru.netology

/** Задача №3 - "Меломан"
 * Вы решили мотивировать пользователей генерировать покупать больше музыки.
 * Схема достаточно простая: чем большую сумму потратил пользователь,
 * тем большую скидку на приобретение следующих композиций вы ему даёте.
 *
 * Условия следующие:
 * Если предыдущая сумма покупок от 0 до 1 000 рублей, то никаких скидок нет (как в лекции).
 * Если предыдущая сумма покупок от 1 001 до 10 000 рублей, то стандартная скидка - 100 рублей (как в лекции).
 * Если предыдущая сумма покупок от 10 001 рубль и выше то % составляет 5% от суммы.
 * Все цены указаны в рублях.
 *
 * При этом постоянные пользователи — те, кто покупает ежемесячно, назовём их «меломаны»,
 * дополнительно получают 1% скидки сверху.
 *
 * Важно: скидки не суммируются, а применяются сверху. Например, у пользователя скидка 5%, значит 1% применяется к 95%:
 *      покупка - 100 рублей →
 *      после применения 5% скидки - 95 рублей.
 *      после применения 1% скидки - 94 рубля 05 копеек.
 * Подумайте о том, как вы будете хранить информацию о том, постоянно покупает пользователь музыку или нет.
 * Итог: у вас должен быть репозиторий на GitHub, в котором будет ваш Gradle-проект.
 */
var isMeloman: Boolean = false;
var currentAmount = 0
val minPriceDiscount = 1_001_00
val maxPriceDiscount = 10_001_00

fun main() {
    cashReceipt(1_100_00)
    cashReceipt(1_200_00)
    cashReceipt(3_200_00)
    cashReceipt(2_500_00)
    isMeloman = true
    cashReceipt(7_200_00)
    cashReceipt(9_600_00)
}

fun cashReceipt(costBasket: Int) {
    var discount = 0
    discount = calcDiscountCost(costBasket)
    println(
        "Текущая сумма покупок ${currentAmount / 100}.${currentAmount % 100} руб. " +
                "Заказ музыки стоимостью ${costBasket / 100}.${costBasket % 100} руб. " +
                "Скидка составляет ${discount / 100}.${discount % 100} руб."
    )
    val pay = costBasket - discount
    println("К оплате ${pay / 100}.${pay % 100} руб.")
    currentAmount += costBasket - discount
}

fun calcDiscountCost(costBasket: Int): Int {
    var discount = 0
    var newCostBasket = costBasket
    if (currentAmount >= minPriceDiscount && currentAmount <= maxPriceDiscount) {
        discount += 100_00
    } else if (currentAmount > maxPriceDiscount) {
        discount += costBasket / 100 * 5
    }
    newCostBasket -= discount
    if (isMeloman) {
        discount = newCostBasket / 100
        newCostBasket -= discount
    }
    discount = costBasket - newCostBasket
    return discount
}