import org.junit.Test

import org.junit.Assert.*

class Task03_2KtTest {

    @Test
    fun pay() {
    }

    @Test
    fun calcCommission() {
    }

    @Test
    fun commissionByMasterCardAndMaestro() {
    }

    @Test
    fun commissionByVisaAndMir() {
    }

    @Test
    fun intKopToRubAndKop() {
        // arrange
        val kop = 100_00
        val expectedValue = "100 руб. 0 коп."
        //act
        val actuialValue = ru.netology.intKopToRubAndKop(100_00)
        //assert
        assertEquals(expectedValue,actuialValue)
    }
}