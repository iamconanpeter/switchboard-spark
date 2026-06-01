package com.iamconanpeter.engine

/**
 * Immutable grid of booleans representing lights.
 * true = on, false = off.
 */
class Grid(private val cells: Array<BoolArray>) {
    val size: Int get() = cells.size

    companion object {
        fun empty(size: Int): Grid = Grid(Array(size) { BoolArray(size) })
    }

    fun toggleRow(row: Int) {
        for (col in 0 until size) cells[row][col] = !cells[row][col]
    }

    fun toggleCol(col: Int) {
        for (row in 0 until size) cells[row][col] = !cells[row][col]
    }

    fun copy(): Grid = Grid(Array(size) { cells[it].clone() })

    operator fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Grid) return false
        if (size != other.size) return false
        for (r in 0 until size) {
            if (!cells[r].contentEquals(other.cells[r])) return false
        }
        return true
    }
}
