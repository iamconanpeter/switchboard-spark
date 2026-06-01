package com.iamconanpeter.engine

import org.junit.Assert.*
import org.junit.Test

class GameEngineTest {
    @Test
    fun testFlipAndUndo() {
        val target = Grid.empty(3)
        val engine = GameEngine(3, target)
        engine.tap(1, 1) // flip row 1 and col 1
        assertFalse(engine.isSolved())
        engine.undo()
        assertTrue(engine.isSolved())
    }

    @Test
    fun testWinCondition() {
        val initial = Grid.empty(2)
        val target = initial.copy()
        val engine = GameEngine(2, target)
        assertTrue(engine.isSolved())
        engine.tap(0, 0)
        assertFalse(engine.isSolved())
    }
}
