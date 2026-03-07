package com.iamconanpeter.switchboard

import org.junit.Assert.*
import org.junit.Test

class EngineTest {
    @Test
    fun testFlipDoesNotCrash() {
        val e = Engine()
        val res = e.flip(3, 3)
        assertEquals(3, res.first)
        assertEquals(3, res.second)
    }
}
