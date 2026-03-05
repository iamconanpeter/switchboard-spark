package com.iamconanpeter.switchboardspark

import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class SwitchboardEngineTest {

    @Test
    fun `tap flips row and column with center toggled once`() {
        val engine = SwitchboardEngine(initialPuzzleIndex = 0)
        val before = engine.boardSnapshot()

        assertTrue(engine.tap(1, 1))
        val after = engine.boardSnapshot()

        val changed = before.indices.count { index -> before[index] != after[index] }
        assertEquals(5, changed)
    }

    @Test
    fun `undo restores prior state and consumes token`() {
        val engine = SwitchboardEngine(initialPuzzleIndex = 0)
        val initial = engine.boardSnapshot()

        assertTrue(engine.tap(1, 1))
        assertTrue(engine.undoLastMove())

        assertArrayEquals(initial, engine.boardSnapshot())
        assertEquals(0, engine.remainingUndos())
        assertFalse(engine.undoLastMove())
    }

    @Test
    fun `reference solution clears puzzle under par`() {
        val engine = SwitchboardEngine(initialPuzzleIndex = 0)
        val puzzle = engine.puzzlesForTesting().first()

        puzzle.referenceSolution.forEach { move ->
            assertTrue(engine.tap(move.row, move.col))
        }

        assertTrue(engine.isSolved())
        assertEquals(PuzzleState.CLEARED_UNDER_PAR, engine.state())
        assertEquals(3, engine.medalStars())
    }

    @Test
    fun `all puzzle references are valid and playable`() {
        val engine = SwitchboardEngine(initialPuzzleIndex = 0)
        val definitions = engine.puzzlesForTesting()

        assertTrue(definitions.size >= 10)
        definitions.forEachIndexed { index, puzzle ->
            engine.loadPuzzle(index)
            puzzle.referenceSolution.forEach { move ->
                assertTrue(engine.tap(move.row, move.col))
            }
            assertTrue("Puzzle ${puzzle.id} should be solvable", engine.isSolved())
            assertTrue(engine.medalStars() >= 1)
        }
    }
}
