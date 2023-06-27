package com.comunidadedevspace.taskbeats

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    private val underTest = MyCountRepositoryImpl()

    @Test
    fun addition_isCorrect() {
        //Given
        val p1 = 3
        val p2 = 2

        //When
        val result = underTest.sum(p1,p2)

        //Then
        val expected = 5
        assertEquals(expected, result)
    }
}