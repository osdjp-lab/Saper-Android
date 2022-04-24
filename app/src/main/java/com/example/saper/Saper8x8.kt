package com.example.saper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageButton
import kotlin.random.Random.Default.nextInt

class Saper8x8 : AppCompatActivity() {
    private val x = 8
    private val y = 8
    private val nrMines = 10

    private var minefield = Array(8) {Array(8) {0}}

    private var buttonRow0 = arrayOfNulls<ImageButton?>(8)
    private var buttonRow1 = arrayOfNulls<ImageButton?>(8)
    private var buttonRow2 = arrayOfNulls<ImageButton?>(8)
    private var buttonRow3 = arrayOfNulls<ImageButton?>(8)
    private var buttonRow4 = arrayOfNulls<ImageButton?>(8)
    private var buttonRow5 = arrayOfNulls<ImageButton?>(8)
    private var buttonRow6 = arrayOfNulls<ImageButton?>(8)
    private var buttonRow7 = arrayOfNulls<ImageButton?>(8)
    var buttonGrid = arrayOf(buttonRow0, buttonRow1, buttonRow2, buttonRow3, buttonRow4, buttonRow5, buttonRow6, buttonRow7)

    private var tileRow0 = arrayOfNulls<Tile?>(8)
    private var tileRow1 = arrayOfNulls<Tile?>(8)
    private var tileRow2 = arrayOfNulls<Tile?>(8)
    private var tileRow3 = arrayOfNulls<Tile?>(8)
    private var tileRow4 = arrayOfNulls<Tile?>(8)
    private var tileRow5 = arrayOfNulls<Tile?>(8)
    private var tileRow6 = arrayOfNulls<Tile?>(8)
    private var tileRow7 = arrayOfNulls<Tile?>(8)
    var tileGrid = arrayOf(tileRow0, tileRow1, tileRow2, tileRow3, tileRow4, tileRow5, tileRow6, tileRow7)

    inner class Tile(px: Int, py: Int, pstate: Int, pcov: Boolean = true) {

        private var x: Int = px
        private var y: Int = py
        private var state: Int = pstate
        private var cov: Boolean = pcov
        var flagged: Boolean = false
        private var triggered: Boolean = false
        private var tmpCalc: Boolean = false
        private var finalCalc: Boolean = false
        var topTile: Tile? = null
        var bottomTile: Tile? = null
        var leftTile: Tile? = null
        var rightTile: Tile? = null
        var topLeftTile: Tile? = null
        var topRightTile: Tile? = null
        var bottomLeftTile: Tile? = null
        var bottomRightTile: Tile? = null

        private fun update(uncover: Boolean) {
            if (uncover) run {
                when (state) {
                    0 -> buttonGrid[x][y]?.setImageResource(R.drawable.tile_0)
                    1 -> buttonGrid[x][y]?.setImageResource(R.drawable.tile_1)
                    2 -> buttonGrid[x][y]?.setImageResource(R.drawable.tile_2)
                    3 -> buttonGrid[x][y]?.setImageResource(R.drawable.tile_3)
                    4 -> buttonGrid[x][y]?.setImageResource(R.drawable.tile_4)
                    5 -> buttonGrid[x][y]?.setImageResource(R.drawable.tile_5)
                    6 -> buttonGrid[x][y]?.setImageResource(R.drawable.tile_6)
                    7 -> buttonGrid[x][y]?.setImageResource(R.drawable.tile_7)
                    8 -> buttonGrid[x][y]?.setImageResource(R.drawable.tile_8)
                    9 -> buttonGrid[x][y]?.setImageResource(R.drawable.mine)
                    else -> buttonGrid[x][y]?.setImageResource(R.drawable.covered_tile)
                }
            } else {
                if (flagged) {
                    buttonGrid[x][y]?.setImageResource(R.drawable.flagged_tile)
                } else {
                    buttonGrid[x][y]?.setImageResource(R.drawable.covered_tile)
                }
            }
        }

        private fun uncoverTile() {
            // Secondary check of cov after uncover method - possibly unnecessary
            if (cov) {
                update(true)
                cov = false
                if (state == 0) {
                    if (topTile != null) {
                        if (topTile!!.state == 0) {
                            topTile!!.uncoverTile()
                        } else {
                            if (topTile!!.state != 9) {
                                topTile!!.update(true)
                                topTile!!.cov = false
                            }
                        }
                    }
                    if (bottomTile != null) {
                        if (bottomTile!!.state == 0) {
                            bottomTile!!.uncoverTile()
                        } else {
                            if (bottomTile!!.state != 9) {
                                bottomTile!!.update(true)
                                bottomTile!!.cov = false
                            }
                        }
                    }
                    if (leftTile != null) {
                        if (leftTile!!.state == 0) {
                            leftTile!!.uncoverTile()
                        } else {
                            if (leftTile!!.state != 9) {
                                leftTile!!.update(true)
                                leftTile!!.cov = false
                            }
                        }
                    }
                    if (rightTile != null) {
                        if (rightTile!!.state == 0) {
                            rightTile!!.uncoverTile()
                        } else {
                            if (rightTile!!.state != 9) {
                                rightTile!!.update(true)
                                rightTile!!.cov = false
                            }
                        }
                    }
                }
            }
        }

        private fun uncoverAll() {
            if (!triggered) {
                update(true)
                cov = false
                triggered = true
                topTile?.uncoverAll()
                bottomTile?.uncoverAll()
                leftTile?.uncoverAll()
                rightTile?.uncoverAll()
            }
        }

        fun uncover() {
            if (cov && !flagged) {
                if (state == 9) {
                    uncoverAll()
                } else {
                    uncoverTile()
                }
            }
        }

        fun flag() {
            if (cov) {
                flagged = !flagged
                update(false)
            }
        }

        private fun value(): Int {
            var tmpValue = 0
            if (this.state == 1) {
                tmpValue = 9
            } else {
                if (topTile != null) {
                    if ((topTile!!.state == 0) or (topTile!!.state == 1)) {
                        tmpValue += topTile!!.state
                    } else {
                        if (topTile!!.state == 19) {
                            tmpValue += 1
                        }
                    }
                }
                if (bottomTile != null) {
                    if ((bottomTile!!.state == 0) or (bottomTile!!.state == 1)) {
                        tmpValue += bottomTile!!.state
                    } else {
                        if (bottomTile!!.state == 19) {
                            tmpValue += 1
                        }
                    }
                }
                if (leftTile != null) {
                    if ((leftTile!!.state == 0) or (leftTile!!.state == 1)) {
                        tmpValue += leftTile!!.state
                    } else {
                        if (leftTile!!.state == 19) {
                            tmpValue += 1
                        }
                    }
                }
                if (rightTile != null) {
                    if ((rightTile!!.state == 0) or (rightTile!!.state == 1)) {
                        tmpValue += rightTile!!.state
                    } else {
                        if (rightTile!!.state == 19) {
                            tmpValue += 1
                        }
                    }
                }
                if (topLeftTile != null) {
                    if ((topLeftTile!!.state == 0) or (topLeftTile!!.state == 1)) {
                        tmpValue += topLeftTile!!.state
                    } else {
                        if (topLeftTile!!.state == 19) {
                            tmpValue += 1
                        }
                    }
                }
                if (topRightTile != null) {
                    if ((topRightTile!!.state == 0) or (topRightTile!!.state == 1)) {
                        tmpValue += topRightTile!!.state
                    } else {
                        if (topRightTile!!.state == 19) {
                            tmpValue += 1
                        }
                    }
                }
                if (bottomLeftTile != null) {
                    if ((bottomLeftTile!!.state == 0) or (bottomLeftTile!!.state == 1)) {
                        tmpValue += bottomLeftTile!!.state
                    } else {
                        if (bottomLeftTile!!.state == 19) {
                            tmpValue += 1
                        }
                    }
                }
                if (bottomRightTile != null) {
                    if ((bottomRightTile!!.state == 0) or (bottomRightTile!!.state == 1)) {
                        tmpValue += bottomRightTile!!.state
                    } else {
                        if (bottomRightTile!!.state == 19) {
                            tmpValue += 1
                        }
                    }
                }
            }
            // Log.println(Log.INFO, "tmpValue", (tmpValue + 10).toString())
            return tmpValue + 10
        }

        private fun info() {
            for (item in tileGrid) {
                var tmp = ""
                for (tile in item) {
                    tmp += tile?.state.toString() + " "
                }
                 Log.println(Log.INFO, "Array", tmp)
            }
        }

        fun calculateValues() {
            tmpValueCalc()
            // info()
            finalValueCalc()
            info()
        }

        private fun tmpValueCalc() {
            if (!tmpCalc) {
                state = value()
                tmpCalc = true
                // recursively check bordering Tiles calculate their
                // transitional state value and continue
                // Log.println(Log.INFO, "Null", (bottomTile != null).toString())
                // Log.println(Log.INFO, "Null", (rightTile != null).toString())
                // top_tile
                topTile?.tmpValueCalc()
                // bottom_tile
                bottomTile?.tmpValueCalc()
                // left_tile
                leftTile?.tmpValueCalc()
                // right_tile
                rightTile?.tmpValueCalc()
            }
        }

        private fun finalValueCalc() {
            if (!finalCalc) {
                state -= 10
                finalCalc = true
                // recursively check bordering Tiles calculate their
                // final state value and continue
                // top_tile
                topTile?.finalValueCalc()
                // bottom_tile
                bottomTile?.finalValueCalc()
                // left_tile
                leftTile?.finalValueCalc()
                // right_tile
                rightTile?.finalValueCalc()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saper8x8)
        initButtonArray()
        initMinefield()
    }

    private fun initMinefield() {
        // addition of mines to the field
        var j = 0
        while (j < nrMines) {
            val a = nextInt(1, x)
            val b = nextInt(1, y)
            if (minefield[a][b] == 0) {
                minefield[a][b] = 1
                j++
            }
        }
        // Tile initialization
        var tmp1 = x-1
        var tmp2 = y-1
        for (i in 0..tmp1) {
            for (j in 0..tmp2) {
                tileGrid[i][j] = Tile(px=i, py=j, pstate=minefield[i][j], pcov=true)
            }
        }
        // Tile linking
        // Horizontal linking
        tmp1 = x-1
        tmp2 = y-2
        for (i in 0..tmp1) {
            for (j in 0..tmp2) {
                tileGrid[i][j]?.rightTile = tileGrid[i][j + 1]
                tileGrid[i][j + 1]?.leftTile = tileGrid[i][j]
            }
        }
        // Vertical linking
        tmp1 = x-2
        tmp2 = y-1
        for (i in 0..tmp1) {
            for (j in 0..tmp2) {
                tileGrid[i][j]?.bottomTile = tileGrid[i + 1][j]
                tileGrid[i+1][j]?.topTile = tileGrid[i][j]
            }
        }
        // Bottom-Right and Top-Left cross linking
        tmp1 = x-2
        tmp2 = y-2
        for (i in 0..tmp1) {
            for (j in 0..tmp2) {
                tileGrid[i][j]?.bottomRightTile = tileGrid[i + 1][j + 1]
                tileGrid[i + 1][j + 1]?.topLeftTile = tileGrid[i][j]
            }
        }
        // Bottom-Left and Top-Right cross linking
        tmp1 = x-1
        tmp2 = y-2
        for (i in 1..tmp1) {
            for (j in 0..tmp2) {
                tileGrid[i][j]?.bottomLeftTile = tileGrid[i - 1][j + 1]
                tileGrid[i - 1][j + 1]?.topRightTile = tileGrid[i][j]
            }
        }
        // Calculation of values for each field
        tileGrid[0][0]?.calculateValues()
    }

    private fun initButtonArray() {
        this.buttonGrid[0][0] = findViewById(R.id.imageButton00)
        this.buttonGrid[0][0]?.setOnLongClickListener {
            tileGrid[0][0]?.flag()
            true
        }
        this.buttonGrid[0][0]?.setOnClickListener {
            tileGrid[0][0]?.uncover()
        }


        this.buttonGrid[0][1] = findViewById(R.id.imageButton01)
        this.buttonGrid[0][1]?.setOnLongClickListener {
            tileGrid[0][1]?.flag()
            true
        }
        this.buttonGrid[0][1]?.setOnClickListener {
            tileGrid[0][1]?.uncover()
        }


        this.buttonGrid[0][2] = findViewById(R.id.imageButton02)
        this.buttonGrid[0][2]?.setOnLongClickListener {
            tileGrid[0][2]?.flag()
            true
        }
        this.buttonGrid[0][2]?.setOnClickListener {
            tileGrid[0][2]?.uncover()
        }


        this.buttonGrid[0][3] = findViewById(R.id.imageButton03)
        this.buttonGrid[0][3]?.setOnLongClickListener {
            tileGrid[0][3]?.flag()
            true
        }
        this.buttonGrid[0][3]?.setOnClickListener {
            tileGrid[0][3]?.uncover()
        }


        this.buttonGrid[0][4] = findViewById(R.id.imageButton04)
        this.buttonGrid[0][4]?.setOnLongClickListener {
            tileGrid[0][4]?.flag()
            true
        }
        this.buttonGrid[0][4]?.setOnClickListener {
            tileGrid[0][4]?.uncover()
        }


        this.buttonGrid[0][5] = findViewById(R.id.imageButton05)
        this.buttonGrid[0][5]?.setOnLongClickListener {
            tileGrid[0][5]?.flag()
            true
        }
        this.buttonGrid[0][5]?.setOnClickListener {
            tileGrid[0][5]?.uncover()
        }


        this.buttonGrid[0][6] = findViewById(R.id.imageButton06)
        this.buttonGrid[0][6]?.setOnLongClickListener {
            tileGrid[0][6]?.flag()
            true
        }
        this.buttonGrid[0][6]?.setOnClickListener {
            tileGrid[0][6]?.uncover()
        }


        this.buttonGrid[0][7] = findViewById(R.id.imageButton07)
        this.buttonGrid[0][7]?.setOnLongClickListener {
            tileGrid[0][7]?.flag()
            true
        }
        this.buttonGrid[0][7]?.setOnClickListener {
            tileGrid[0][7]?.uncover()
        }


        this.buttonGrid[1][0] = findViewById(R.id.imageButton10)
        this.buttonGrid[1][0]?.setOnLongClickListener {
            true
        }
        this.buttonGrid[1][0]?.setOnClickListener {
            tileGrid[1][0]?.uncover()
        }


        this.buttonGrid[1][1] = findViewById(R.id.imageButton11)
        this.buttonGrid[1][1]?.setOnLongClickListener {
            tileGrid[1][1]?.flag()
            true
        }
        this.buttonGrid[1][1]?.setOnClickListener {
            tileGrid[1][1]?.uncover()
        }


        this.buttonGrid[1][2] = findViewById(R.id.imageButton12)
        this.buttonGrid[1][2]?.setOnLongClickListener {
            tileGrid[1][2]?.flag()
            true
        }
        this.buttonGrid[1][2]?.setOnClickListener {
            tileGrid[1][2]?.uncover()
        }


        this.buttonGrid[1][3] = findViewById(R.id.imageButton13)
        this.buttonGrid[1][3]?.setOnLongClickListener {
            tileGrid[1][3]?.flag()
            true
        }
        this.buttonGrid[1][3]?.setOnClickListener {
            tileGrid[1][3]?.uncover()
        }


        this.buttonGrid[1][4] = findViewById(R.id.imageButton14)
        this.buttonGrid[1][4]?.setOnLongClickListener {
            tileGrid[1][4]?.flag()
            true
        }
        this.buttonGrid[1][4]?.setOnClickListener {
            tileGrid[1][4]?.uncover()
        }


        this.buttonGrid[1][5] = findViewById(R.id.imageButton15)
        this.buttonGrid[1][5]?.setOnLongClickListener {
            tileGrid[1][5]?.flag()
            true
        }
        this.buttonGrid[1][5]?.setOnClickListener {
            tileGrid[1][5]?.uncover()
        }


        this.buttonGrid[1][6] = findViewById(R.id.imageButton16)
        this.buttonGrid[1][6]?.setOnLongClickListener {
            tileGrid[1][6]?.flag()
            true
        }
        this.buttonGrid[1][6]?.setOnClickListener {
            tileGrid[1][6]?.uncover()
        }


        this.buttonGrid[1][7] = findViewById(R.id.imageButton17)
        this.buttonGrid[1][7]?.setOnLongClickListener {
            tileGrid[1][7]?.flag()
            true
        }
        this.buttonGrid[1][7]?.setOnClickListener {
            tileGrid[1][7]?.uncover()
        }


        this.buttonGrid[2][0] = findViewById(R.id.imageButton20)
        this.buttonGrid[2][0]?.setOnLongClickListener {
            tileGrid[2][0]?.flag()
            true
        }
        this.buttonGrid[2][0]?.setOnClickListener {
            tileGrid[2][0]?.uncover()
        }


        this.buttonGrid[2][1] = findViewById(R.id.imageButton21)
        this.buttonGrid[2][1]?.setOnLongClickListener {
            tileGrid[2][1]?.flag()
            true
        }
        this.buttonGrid[2][1]?.setOnClickListener {
            tileGrid[2][1]?.uncover()
        }


        this.buttonGrid[2][2] = findViewById(R.id.imageButton22)
        this.buttonGrid[2][2]?.setOnLongClickListener {
            tileGrid[2][2]?.flag()
            true
        }
        this.buttonGrid[2][2]?.setOnClickListener {
            tileGrid[2][2]?.uncover()
        }


        this.buttonGrid[2][3] = findViewById(R.id.imageButton23)
        this.buttonGrid[2][3]?.setOnLongClickListener {
            tileGrid[2][3]?.flag()
            true
        }
        this.buttonGrid[2][3]?.setOnClickListener {
            tileGrid[2][3]?.uncover()
        }


        this.buttonGrid[2][4] = findViewById(R.id.imageButton24)
        this.buttonGrid[2][4]?.setOnLongClickListener {
            tileGrid[2][4]?.flag()
            true
        }
        this.buttonGrid[2][4]?.setOnClickListener {
            tileGrid[2][4]?.uncover()
        }


        this.buttonGrid[2][5] = findViewById(R.id.imageButton25)
        this.buttonGrid[2][5]?.setOnLongClickListener {
            tileGrid[2][5]?.flag()
            true
        }
        this.buttonGrid[2][5]?.setOnClickListener {
            tileGrid[2][5]?.uncover()
        }


        this.buttonGrid[2][6] = findViewById(R.id.imageButton26)
        this.buttonGrid[2][6]?.setOnLongClickListener {
            tileGrid[2][6]?.flag()
            true
        }
        this.buttonGrid[2][6]?.setOnClickListener {
            tileGrid[2][6]?.uncover()
        }


        this.buttonGrid[2][7] = findViewById(R.id.imageButton27)
        this.buttonGrid[2][7]?.setOnLongClickListener {
            tileGrid[2][7]?.flag()
            true
        }
        this.buttonGrid[2][7]?.setOnClickListener {
            tileGrid[2][7]?.uncover()
        }


        this.buttonGrid[3][0] = findViewById(R.id.imageButton30)
        this.buttonGrid[3][0]?.setOnLongClickListener {
            tileGrid[3][0]?.flag()
            true
        }
        this.buttonGrid[3][0]?.setOnClickListener {
            tileGrid[3][0]?.uncover()
        }


        this.buttonGrid[3][1] = findViewById(R.id.imageButton31)
        this.buttonGrid[3][1]?.setOnLongClickListener {
            tileGrid[3][1]?.flag()
            true
        }
        this.buttonGrid[3][1]?.setOnClickListener {
            tileGrid[3][1]?.uncover()
        }


        this.buttonGrid[3][2] = findViewById(R.id.imageButton32)
        this.buttonGrid[3][2]?.setOnLongClickListener {
            tileGrid[3][2]?.flag()
            true
        }
        this.buttonGrid[3][2]?.setOnClickListener {
            tileGrid[3][2]?.uncover()
        }


        this.buttonGrid[3][3] = findViewById(R.id.imageButton33)
        this.buttonGrid[3][3]?.setOnLongClickListener {
            tileGrid[3][3]?.flag()
            true
        }
        this.buttonGrid[3][3]?.setOnClickListener {
            tileGrid[3][3]?.uncover()
        }


        this.buttonGrid[3][4] = findViewById(R.id.imageButton34)
        this.buttonGrid[3][4]?.setOnLongClickListener {
            tileGrid[3][4]?.flag()
            true
        }
        this.buttonGrid[3][4]?.setOnClickListener {
            tileGrid[3][4]?.uncover()
        }


        this.buttonGrid[3][5] = findViewById(R.id.imageButton35)
        this.buttonGrid[3][5]?.setOnLongClickListener {
            tileGrid[3][5]?.flag()
            true
        }
        this.buttonGrid[3][5]?.setOnClickListener {
            tileGrid[3][5]?.uncover()
        }


        this.buttonGrid[3][6] = findViewById(R.id.imageButton36)
        this.buttonGrid[3][6]?.setOnLongClickListener {
            tileGrid[3][6]?.flag()
            true
        }
        this.buttonGrid[3][6]?.setOnClickListener {
            tileGrid[3][6]?.uncover()
        }


        this.buttonGrid[3][7] = findViewById(R.id.imageButton37)
        this.buttonGrid[3][7]?.setOnLongClickListener {
            tileGrid[3][7]?.flag()
            true
        }
        this.buttonGrid[3][7]?.setOnClickListener {
            tileGrid[3][7]?.uncover()
        }


        this.buttonGrid[4][0] = findViewById(R.id.imageButton40)
        this.buttonGrid[4][0]?.setOnLongClickListener {
            tileGrid[4][0]?.flag()
            true
        }
        this.buttonGrid[4][0]?.setOnClickListener {
            tileGrid[4][0]?.uncover()
        }


        this.buttonGrid[4][1] = findViewById(R.id.imageButton41)
        this.buttonGrid[4][1]?.setOnLongClickListener {
            tileGrid[4][1]?.flag()
            true
        }
        this.buttonGrid[4][1]?.setOnClickListener {
            tileGrid[4][1]?.uncover()
        }


        this.buttonGrid[4][2] = findViewById(R.id.imageButton42)
        this.buttonGrid[4][2]?.setOnLongClickListener {
            tileGrid[4][2]?.flag()
            true
        }
        this.buttonGrid[4][2]?.setOnClickListener {
            tileGrid[4][2]?.uncover()
        }


        this.buttonGrid[4][3] = findViewById(R.id.imageButton43)
        this.buttonGrid[4][3]?.setOnLongClickListener {
            tileGrid[4][3]?.flag()
            true
        }
        this.buttonGrid[4][3]?.setOnClickListener {
            tileGrid[4][3]?.uncover()
        }


        this.buttonGrid[4][4] = findViewById(R.id.imageButton44)
        this.buttonGrid[4][4]?.setOnLongClickListener {
            tileGrid[4][4]?.flag()
            true
        }
        this.buttonGrid[4][4]?.setOnClickListener {
            tileGrid[4][4]?.uncover()
        }


        this.buttonGrid[4][5] = findViewById(R.id.imageButton45)
        this.buttonGrid[4][5]?.setOnLongClickListener {
            tileGrid[4][5]?.flag()
            true
        }
        this.buttonGrid[4][5]?.setOnClickListener {
            tileGrid[4][5]?.uncover()
        }


        this.buttonGrid[4][6] = findViewById(R.id.imageButton46)
        this.buttonGrid[4][6]?.setOnLongClickListener {
            tileGrid[4][6]?.flag()
            true
        }
        this.buttonGrid[4][6]?.setOnClickListener {
            tileGrid[4][6]?.uncover()
        }


        this.buttonGrid[4][7] = findViewById(R.id.imageButton47)
        this.buttonGrid[4][7]?.setOnLongClickListener {
            tileGrid[4][7]?.flag()
            true
        }
        this.buttonGrid[4][7]?.setOnClickListener {
            tileGrid[4][7]?.uncover()
        }


        this.buttonGrid[5][0] = findViewById(R.id.imageButton50)
        this.buttonGrid[5][0]?.setOnLongClickListener {
            tileGrid[5][0]?.flag()
            true
        }
        this.buttonGrid[5][0]?.setOnClickListener {
            tileGrid[5][0]?.uncover()
        }


        this.buttonGrid[5][1] = findViewById(R.id.imageButton51)
        this.buttonGrid[5][1]?.setOnLongClickListener {
            tileGrid[5][1]?.flag()
            true
        }
        this.buttonGrid[5][1]?.setOnClickListener {
            tileGrid[5][1]?.uncover()
        }


        this.buttonGrid[5][2] = findViewById(R.id.imageButton52)
        this.buttonGrid[5][2]?.setOnLongClickListener {
            tileGrid[5][2]?.flag()
            true
        }
        this.buttonGrid[5][2]?.setOnClickListener {
            tileGrid[5][2]?.uncover()
        }


        this.buttonGrid[5][3] = findViewById(R.id.imageButton53)
        this.buttonGrid[5][3]?.setOnLongClickListener {
            tileGrid[5][3]?.flag()
            true
        }
        this.buttonGrid[5][3]?.setOnClickListener {
            tileGrid[5][3]?.uncover()
        }


        this.buttonGrid[5][4] = findViewById(R.id.imageButton54)
        this.buttonGrid[5][4]?.setOnLongClickListener {
            tileGrid[5][4]?.flag()
            true
        }
        this.buttonGrid[5][4]?.setOnClickListener {
            tileGrid[5][4]?.uncover()
        }


        this.buttonGrid[5][5] = findViewById(R.id.imageButton55)
        this.buttonGrid[5][5]?.setOnLongClickListener {
            tileGrid[5][5]?.flag()
            true
        }
        this.buttonGrid[5][5]?.setOnClickListener {
            tileGrid[5][5]?.uncover()
        }


        this.buttonGrid[5][6] = findViewById(R.id.imageButton56)
        this.buttonGrid[5][6]?.setOnLongClickListener {
            tileGrid[5][6]?.flag()
            true
        }
        this.buttonGrid[5][6]?.setOnClickListener {
            tileGrid[5][6]?.uncover()
        }


        this.buttonGrid[5][7] = findViewById(R.id.imageButton57)
        this.buttonGrid[5][7]?.setOnLongClickListener {
            tileGrid[5][7]?.flag()
            true
        }
        this.buttonGrid[5][7]?.setOnClickListener {
            tileGrid[5][7]?.uncover()
        }


        this.buttonGrid[6][0] = findViewById(R.id.imageButton60)
        this.buttonGrid[6][0]?.setOnLongClickListener {
            tileGrid[6][0]?.flag()
            true
        }
        this.buttonGrid[6][0]?.setOnClickListener {
            tileGrid[6][0]?.uncover()
        }


        this.buttonGrid[6][1] = findViewById(R.id.imageButton61)
        this.buttonGrid[6][1]?.setOnLongClickListener {
            tileGrid[6][1]?.flag()
            true
        }
        this.buttonGrid[6][1]?.setOnClickListener {
            tileGrid[6][1]?.uncover()
        }


        this.buttonGrid[6][2] = findViewById(R.id.imageButton62)
        this.buttonGrid[6][2]?.setOnLongClickListener {
            tileGrid[6][2]?.flag()
            true
        }
        this.buttonGrid[6][2]?.setOnClickListener {
            tileGrid[6][2]?.uncover()
        }


        this.buttonGrid[6][3] = findViewById(R.id.imageButton63)
        this.buttonGrid[6][3]?.setOnLongClickListener {
            tileGrid[6][3]?.flag()
            true
        }
        this.buttonGrid[6][3]?.setOnClickListener {
            tileGrid[6][3]?.uncover()
        }


        this.buttonGrid[6][4] = findViewById(R.id.imageButton64)
        this.buttonGrid[6][4]?.setOnLongClickListener {
            tileGrid[6][4]?.flag()
            true
        }
        this.buttonGrid[6][4]?.setOnClickListener {
            tileGrid[6][4]?.uncover()
        }


        this.buttonGrid[6][5] = findViewById(R.id.imageButton65)
        this.buttonGrid[6][5]?.setOnLongClickListener {
            tileGrid[6][5]?.flag()
            true
        }
        this.buttonGrid[6][5]?.setOnClickListener {
            tileGrid[6][5]?.uncover()
        }


        this.buttonGrid[6][6] = findViewById(R.id.imageButton66)
        this.buttonGrid[6][6]?.setOnLongClickListener {
            tileGrid[6][6]?.flag()
            true
        }
        this.buttonGrid[6][6]?.setOnClickListener {
            tileGrid[6][6]?.uncover()
        }


        this.buttonGrid[6][7] = findViewById(R.id.imageButton67)
        this.buttonGrid[6][7]?.setOnLongClickListener {
            tileGrid[6][7]?.flag()
            true
        }
        this.buttonGrid[6][7]?.setOnClickListener {
            tileGrid[6][7]?.uncover()
        }


        this.buttonGrid[7][0] = findViewById(R.id.imageButton70)
        this.buttonGrid[7][0]?.setOnLongClickListener {
            tileGrid[7][0]?.flag()
            true
        }
        this.buttonGrid[7][0]?.setOnClickListener {
            tileGrid[7][0]?.uncover()
        }


        this.buttonGrid[7][1] = findViewById(R.id.imageButton71)
        this.buttonGrid[7][1]?.setOnLongClickListener {
            tileGrid[7][1]?.flag()
            true
        }
        this.buttonGrid[7][1]?.setOnClickListener {
            tileGrid[7][1]?.uncover()
        }


        this.buttonGrid[7][2] = findViewById(R.id.imageButton72)
        this.buttonGrid[7][2]?.setOnLongClickListener {
            tileGrid[7][2]?.flag()
            true
        }
        this.buttonGrid[7][2]?.setOnClickListener {
            tileGrid[7][2]?.uncover()
        }


        this.buttonGrid[7][3] = findViewById(R.id.imageButton73)
        this.buttonGrid[7][3]?.setOnLongClickListener {
            tileGrid[7][3]?.flag()
            true
        }
        this.buttonGrid[7][3]?.setOnClickListener {
            tileGrid[7][3]?.uncover()
        }


        this.buttonGrid[7][4] = findViewById(R.id.imageButton74)
        this.buttonGrid[7][4]?.setOnLongClickListener {
            tileGrid[7][4]?.flag()
            true
        }
        this.buttonGrid[7][4]?.setOnClickListener {
            tileGrid[7][4]?.uncover()
        }


        this.buttonGrid[7][5] = findViewById(R.id.imageButton75)
        this.buttonGrid[7][5]?.setOnLongClickListener {
            tileGrid[7][5]?.flag()
            true
        }
        this.buttonGrid[7][5]?.setOnClickListener {
            tileGrid[7][5]?.uncover()
        }


        this.buttonGrid[7][6] = findViewById(R.id.imageButton76)
        this.buttonGrid[7][6]?.setOnLongClickListener {
            tileGrid[7][6]?.flag()
            true
        }
        this.buttonGrid[7][6]?.setOnClickListener {
            tileGrid[7][6]?.uncover()
        }


        this.buttonGrid[7][7] = findViewById(R.id.imageButton77)
        this.buttonGrid[7][7]?.setOnLongClickListener {
            tileGrid[7][7]?.flag()
            true
        }
        this.buttonGrid[7][7]?.setOnClickListener {
            tileGrid[7][7]?.uncover()
        }
    }
}