package com.iamconanpeter.engine

class GameEngine(val size: Int = 5, val target: Grid) {
    private var grid: Grid = Grid.empty(size)
    private val undoStack: ArrayDeque<Grid> = ArrayDeque()
    var moves = 0
        private set
    var undoUsed = false
        private set

    fun tap(row: Int, col: Int) {
        undoStack.addLast(grid.copy())
        grid.toggleRow(row)
        grid.toggleCol(col)
        moves++
    }

    fun undo() {
        if (!undoUsed && undoStack.isNotEmpty()) {
            grid = undoStack.removeLast()
            undoUsed = true
        }
    }

    fun isSolved(): Boolean = grid == target

    fun currentGrid(): Grid = grid
}
