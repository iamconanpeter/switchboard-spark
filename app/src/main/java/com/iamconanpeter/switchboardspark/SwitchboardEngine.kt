package com.iamconanpeter.switchboardspark

data class Pos(val row: Int, val col: Int)

enum class PuzzleState {
    IN_PROGRESS,
    CLEARED_UNDER_PAR,
    CLEARED_OVER_PAR
}

data class SwitchPuzzle(
    val id: String,
    val name: String,
    val size: Int,
    val par: Int,
    val start: IntArray,
    val target: IntArray,
    val referenceSolution: List<Pos>
)

class SwitchboardEngine(
    initialPuzzleIndex: Int = 0,
    private val undoLimit: Int = 1
) {
    private val puzzles: List<SwitchPuzzle> = buildPuzzles()

    private var puzzleIndex: Int = initialPuzzleIndex.mod(puzzles.size)
    private var current: SwitchPuzzle = puzzles[puzzleIndex]
    private var board: IntArray = current.start.copyOf()
    private val history: ArrayDeque<IntArray> = ArrayDeque()

    private var movesUsed: Int = 0
    private var remainingUndos: Int = undoLimit

    init {
        validatePuzzleSet()
    }

    fun puzzleCount(): Int = puzzles.size
    fun currentPuzzleIndex(): Int = puzzleIndex
    fun puzzleId(): String = current.id
    fun puzzleName(): String = current.name
    fun size(): Int = current.size
    fun parMoves(): Int = current.par
    fun movesUsed(): Int = movesUsed
    fun remainingUndos(): Int = remainingUndos

    fun boardValue(row: Int, col: Int): Int = board[idx(row, col, current.size)]
    fun targetValue(row: Int, col: Int): Int = current.target[idx(row, col, current.size)]
    fun boardSnapshot(): IntArray = board.copyOf()

    fun isSolved(): Boolean = board.contentEquals(current.target)

    fun state(): PuzzleState {
        if (!isSolved()) {
            return PuzzleState.IN_PROGRESS
        }
        return if (movesUsed <= current.par) PuzzleState.CLEARED_UNDER_PAR else PuzzleState.CLEARED_OVER_PAR
    }

    fun medalStars(): Int {
        if (!isSolved()) {
            return 0
        }
        return when {
            movesUsed <= current.par && remainingUndos == undoLimit -> 3
            movesUsed <= current.par + 2 -> 2
            else -> 1
        }
    }

    fun tap(row: Int, col: Int): Boolean {
        if (!inBounds(row, col)) {
            return false
        }

        history.addLast(board.copyOf())
        flipAt(board, current.size, row, col)
        movesUsed += 1
        return true
    }

    fun undoLastMove(): Boolean {
        if (remainingUndos <= 0 || history.isEmpty()) {
            return false
        }

        board = history.removeLast()
        movesUsed = maxOf(0, movesUsed - 1)
        remainingUndos -= 1
        return true
    }

    fun resetPuzzle() {
        board = current.start.copyOf()
        history.clear()
        movesUsed = 0
        remainingUndos = undoLimit
    }

    fun loadPuzzle(index: Int) {
        puzzleIndex = index.mod(puzzles.size)
        current = puzzles[puzzleIndex]
        resetPuzzle()
    }

    fun nextPuzzle() {
        loadPuzzle(puzzleIndex + 1)
    }

    fun puzzlesForTesting(): List<SwitchPuzzle> = puzzles.map { puzzle ->
        puzzle.copy(
            start = puzzle.start.copyOf(),
            target = puzzle.target.copyOf(),
            referenceSolution = puzzle.referenceSolution.toList()
        )
    }

    private fun inBounds(row: Int, col: Int): Boolean = row in 0 until current.size && col in 0 until current.size

    private fun validatePuzzleSet() {
        for (puzzle in puzzles) {
            require(puzzle.size >= 3) { "Puzzle ${puzzle.id} must be at least 3x3" }
            require(puzzle.start.size == puzzle.size * puzzle.size) { "Invalid start shape for ${puzzle.id}" }
            require(puzzle.target.size == puzzle.size * puzzle.size) { "Invalid target shape for ${puzzle.id}" }
            require(!puzzle.start.contentEquals(puzzle.target)) { "Puzzle ${puzzle.id} start equals target" }

            val replay = puzzle.start.copyOf()
            puzzle.referenceSolution.forEach { move ->
                require(move.row in 0 until puzzle.size && move.col in 0 until puzzle.size) {
                    "Out-of-bounds move in solution for ${puzzle.id}"
                }
                flipAt(replay, puzzle.size, move.row, move.col)
            }
            require(replay.contentEquals(puzzle.target)) { "Reference solution mismatch for ${puzzle.id}" }
            require(puzzle.par >= puzzle.referenceSolution.size) { "Par is lower than known solution for ${puzzle.id}" }
        }
    }

    private fun buildPuzzles(): List<SwitchPuzzle> = listOf(
        derivedPuzzle(
            id = "boot-sequence",
            name = "Boot Sequence",
            par = 1,
            startRows = listOf(
                "000",
                "010",
                "000"
            ),
            solution = listOf(Pos(1, 1))
        ),
        derivedPuzzle(
            id = "rail-split",
            name = "Rail Split",
            par = 2,
            startRows = listOf(
                "101",
                "000",
                "101"
            ),
            solution = listOf(Pos(0, 1), Pos(2, 1))
        ),
        derivedPuzzle(
            id = "core-ping",
            name = "Core Ping",
            par = 2,
            startRows = listOf(
                "010",
                "111",
                "010"
            ),
            solution = listOf(Pos(0, 0), Pos(2, 2))
        ),
        derivedPuzzle(
            id = "angle-lock",
            name = "Angle Lock",
            par = 3,
            startRows = listOf(
                "110",
                "001",
                "010"
            ),
            solution = listOf(Pos(1, 1), Pos(0, 2), Pos(2, 0))
        ),
        derivedPuzzle(
            id = "twin-bus",
            name = "Twin Bus",
            par = 2,
            startRows = listOf(
                "0110",
                "1001",
                "1001",
                "0110"
            ),
            solution = listOf(Pos(1, 1), Pos(2, 2))
        ),
        derivedPuzzle(
            id = "junction-echo",
            name = "Junction Echo",
            par = 3,
            startRows = listOf(
                "1001",
                "0110",
                "0110",
                "1001"
            ),
            solution = listOf(Pos(0, 1), Pos(3, 2), Pos(1, 3))
        ),
        derivedPuzzle(
            id = "relay-ring",
            name = "Relay Ring",
            par = 4,
            startRows = listOf(
                "10101",
                "01010",
                "11111",
                "01010",
                "10101"
            ),
            solution = listOf(Pos(2, 2), Pos(0, 4), Pos(4, 0), Pos(1, 1))
        ),
        derivedPuzzle(
            id = "bridge-grid",
            name = "Bridge Grid",
            par = 3,
            startRows = listOf(
                "00110",
                "11001",
                "01010",
                "11001",
                "00110"
            ),
            solution = listOf(Pos(0, 0), Pos(2, 4), Pos(4, 2))
        ),
        derivedPuzzle(
            id = "pulse-lattice",
            name = "Pulse Lattice",
            par = 4,
            startRows = listOf(
                "111000",
                "001111",
                "010101",
                "101010",
                "001111",
                "111000"
            ),
            solution = listOf(Pos(0, 5), Pos(5, 0), Pos(2, 2), Pos(3, 3))
        ),
        derivedPuzzle(
            id = "orbit-switch",
            name = "Orbit Switch",
            par = 4,
            startRows = listOf(
                "100001",
                "011110",
                "010010",
                "010010",
                "011110",
                "100001"
            ),
            solution = listOf(Pos(1, 4), Pos(4, 1), Pos(0, 2), Pos(5, 3))
        ),
        derivedPuzzle(
            id = "signal-fork",
            name = "Signal Fork",
            par = 5,
            startRows = listOf(
                "101101",
                "010010",
                "111000",
                "000111",
                "010010",
                "101101"
            ),
            solution = listOf(Pos(0, 3), Pos(2, 1), Pos(3, 4), Pos(5, 2), Pos(1, 5))
        ),
        derivedPuzzle(
            id = "spark-storm",
            name = "Spark Storm",
            par = 5,
            startRows = listOf(
                "011110",
                "100001",
                "101101",
                "101101",
                "100001",
                "011110"
            ),
            solution = listOf(Pos(2, 0), Pos(3, 5), Pos(0, 4), Pos(5, 1), Pos(2, 3))
        )
    )

    private fun derivedPuzzle(
        id: String,
        name: String,
        par: Int,
        startRows: List<String>,
        solution: List<Pos>
    ): SwitchPuzzle {
        val size = startRows.size
        require(size > 0) { "Puzzle $id has no rows" }
        require(startRows.all { it.length == size }) { "Puzzle $id must be square" }

        val start = rowsToBoard(startRows)
        val target = start.copyOf()
        solution.forEach { move ->
            require(move.row in 0 until size && move.col in 0 until size) { "Invalid move in $id" }
            flipAt(target, size, move.row, move.col)
        }

        return SwitchPuzzle(
            id = id,
            name = name,
            size = size,
            par = par,
            start = start,
            target = target,
            referenceSolution = solution
        )
    }

    private fun rowsToBoard(rows: List<String>): IntArray {
        val size = rows.size
        return IntArray(size * size) { index ->
            val row = index / size
            val col = index % size
            when (rows[row][col]) {
                '0' -> 0
                '1' -> 1
                else -> throw IllegalArgumentException("Board rows must only contain 0/1")
            }
        }
    }

    private fun flipAt(board: IntArray, size: Int, row: Int, col: Int) {
        for (c in 0 until size) {
            val index = idx(row, c, size)
            board[index] = 1 - board[index]
        }
        for (r in 0 until size) {
            if (r == row) {
                continue
            }
            val index = idx(r, col, size)
            board[index] = 1 - board[index]
        }
    }

    private fun idx(row: Int, col: Int, size: Int): Int = row * size + col
}
