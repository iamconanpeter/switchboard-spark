package com.iamconanpeter.switchboardspark

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.GridLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var engine: SwitchboardEngine
    private lateinit var progress: SparkProgressManager

    private lateinit var titleText: TextView
    private lateinit var helpText: TextView
    private lateinit var statsText: TextView
    private lateinit var statusText: TextView
    private lateinit var resultText: TextView
    private lateinit var targetGrid: GridLayout
    private lateinit var boardGrid: GridLayout

    private var dailyIndex: Int = 0
    private var solvedAwarded: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        progress = SparkProgressManager(this)
        engine = SwitchboardEngine()
        dailyIndex = progress.dailyPuzzleIndex(engine.puzzleCount())
        engine.loadPuzzle(dailyIndex)

        titleText = findViewById(R.id.titleText)
        helpText = findViewById(R.id.helpText)
        statsText = findViewById(R.id.statsText)
        statusText = findViewById(R.id.statusText)
        resultText = findViewById(R.id.resultText)
        targetGrid = findViewById(R.id.targetGrid)
        boardGrid = findViewById(R.id.boardGrid)

        findViewById<Button>(R.id.undoBtn).setOnClickListener {
            if (engine.undoLastMove()) {
                solvedAwarded = false
                renderAll("Undo consumed")
            } else {
                statusText.setTextColor(Color.parseColor("#FCA5A5"))
                statusText.text = "No undo charge left"
            }
        }

        findViewById<Button>(R.id.resetBtn).setOnClickListener {
            engine.resetPuzzle()
            solvedAwarded = false
            renderAll("Puzzle reset")
        }

        findViewById<Button>(R.id.nextBtn).setOnClickListener {
            engine.nextPuzzle()
            solvedAwarded = false
            renderAll("Loaded next puzzle")
        }

        renderAll()
    }

    private fun renderAll(prefix: String? = null) {
        titleText.text = "Switchboard Spark - ${engine.puzzleName()}"
        helpText.text = "Tap node: flips full row + column. Match target under par."
        renderTarget()
        renderBoard(prefix)
        renderStatus(prefix)
    }

    private fun renderTarget() {
        targetGrid.removeAllViews()
        val size = engine.size()
        targetGrid.columnCount = size
        targetGrid.rowCount = size

        for (row in 0 until size) {
            for (col in 0 until size) {
                val cell = TextView(this)
                val on = engine.targetValue(row, col) == 1
                cell.text = if (on) "1" else "0"
                cell.textSize = 16f
                cell.gravity = Gravity.CENTER
                cell.setTextColor(Color.parseColor(if (on) "#EAFB5A" else "#64748B"))
                cell.setBackgroundColor(Color.parseColor(if (on) "#1E293B" else "#0F172A"))

                val params = GridLayout.LayoutParams().apply {
                    width = 0
                    height = 0
                    columnSpec = GridLayout.spec(col, 1f)
                    rowSpec = GridLayout.spec(row, 1f)
                    setMargins(3, 3, 3, 3)
                }
                cell.layoutParams = params
                targetGrid.addView(cell)
            }
        }
    }

    private fun renderBoard(prefix: String? = null) {
        boardGrid.removeAllViews()
        val size = engine.size()
        boardGrid.columnCount = size
        boardGrid.rowCount = size

        for (row in 0 until size) {
            for (col in 0 until size) {
                val button = Button(this)
                val on = engine.boardValue(row, col) == 1
                button.text = if (on) "1" else "0"
                button.textSize = 18f
                button.gravity = Gravity.CENTER
                button.setTextColor(Color.parseColor(if (on) "#F8FAFC" else "#94A3B8"))
                button.setBackgroundColor(Color.parseColor(if (on) "#0284C7" else "#1E293B"))

                button.setOnClickListener {
                    if (engine.tap(row, col)) {
                        solvedAwarded = false
                        renderAll(prefix)
                    }
                }

                val params = GridLayout.LayoutParams().apply {
                    width = 0
                    height = 0
                    columnSpec = GridLayout.spec(col, 1f)
                    rowSpec = GridLayout.spec(row, 1f)
                    setMargins(4, 4, 4, 4)
                }
                button.layoutParams = params
                boardGrid.addView(button)
            }
        }
    }

    private fun renderStatus(prefix: String?) {
        if (engine.isSolved() && !solvedAwarded) {
            progress.updateBestMedal(engine.puzzleId(), engine.medalStars())
            if (engine.currentPuzzleIndex() == dailyIndex) {
                progress.recordDailySolve()
            }
            solvedAwarded = true
        }

        if (!engine.isSolved()) {
            solvedAwarded = false
        }

        val statusLine = when (engine.state()) {
            PuzzleState.IN_PROGRESS -> "Routing in progress"
            PuzzleState.CLEARED_UNDER_PAR -> "Board restored under par"
            PuzzleState.CLEARED_OVER_PAR -> "Board restored over par"
        }

        statusText.setTextColor(
            Color.parseColor(
                when (engine.state()) {
                    PuzzleState.IN_PROGRESS -> "#7DD3FC"
                    PuzzleState.CLEARED_UNDER_PAR -> "#4ADE80"
                    PuzzleState.CLEARED_OVER_PAR -> "#FACC15"
                }
            )
        )

        statusText.text = if (prefix == null) statusLine else "$prefix - $statusLine"

        val best = progress.bestMedalFor(engine.puzzleId())
        val dailyTag = if (engine.currentPuzzleIndex() == dailyIndex) "Daily live" else "Campaign"

        statsText.text =
            "Puzzle ${engine.currentPuzzleIndex() + 1}/${engine.puzzleCount()} ($dailyTag) | " +
                "Moves ${engine.movesUsed()}/${engine.parMoves()} | Undo ${engine.remainingUndos()} | " +
                "Best ${stars(best)} | Streak ${progress.currentStreak()}"

        resultText.text = when (engine.state()) {
            PuzzleState.IN_PROGRESS -> "Target and board must match exactly."
            PuzzleState.CLEARED_UNDER_PAR -> "Medal ${stars(engine.medalStars())}: strong efficiency."
            PuzzleState.CLEARED_OVER_PAR -> "Medal ${stars(engine.medalStars())}: solved, can optimize further."
        }
    }

    private fun stars(count: Int): String {
        if (count <= 0) {
            return "-"
        }
        return "*".repeat(count)
    }
}
