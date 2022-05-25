package com.example.saper

import kotlin.random.Random.Default.nextInt

class SaperSquaresModel(
    val x: Int = 8,
    val y: Int = 8,
    private val nrMines: Int = 10
) {

    private var minefield = Array(x) {Array(y) {0}}
    var tileGrid = Array(x){Array<SquareTile?>(y){null}}

    init {
        initMinefield()
    }

    private fun initMinefield() {
        // addition of mines to the field
        var n = 0
        while (n < nrMines) {
            val a = nextInt(1, x)
            val b = nextInt(1, y)
            if (minefield[a][b] == 0) {
                minefield[a][b] = 1
                n++
            }
        }
        // Tile initialization
        var tmp1 = x-1
        var tmp2 = y-1
        for (i in 0..tmp1) {
            for (j in 0..tmp2) {
                tileGrid[i][j] = SquareTile(x=i, y=j, state=minefield[i][j])
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
        calculateValues(tileGrid[0][0]!!)
    }

    fun flag(x: Int, y: Int) {
        if (tileGrid[x][y]!!.isCovered) {
            tileGrid[x][y]!!.isFlagged = !tileGrid[x][y]!!.isFlagged
        }
    }

    // BUG: only returns array of nulls
    fun uncover(x: Int, y: Int): List<SquareTile> {
        var uncoveredSquareTiles: Array<SquareTile?> = arrayOfNulls(x * y)
        if (tileGrid[x][y]!!.isCovered && !tileGrid[x][y]!!.isFlagged) {
            if (tileGrid[x][y]!!.state == 9) {
                uncoveredSquareTiles = uncoverAll(tileGrid[x][y])
            } else {
                uncoveredSquareTiles = uncoverTile(tileGrid[x][y]!!)
            }
        }
        return uncoveredSquareTiles.filterNotNull()
    }

    private fun uncoverTile(tile: SquareTile): Array<SquareTile?> {
        var uncoveredSquareTiles: Array<SquareTile?> = arrayOfNulls(x * y)
        // Secondary check of isCovered after uncover method - possibly unnecessary
        if (tile.isCovered) {
            tile.isCovered = false
            if (tile.state == 0) {
                if (tile.topTile != null) {
                    if (tile.topTile!!.state == 0) {
                        uncoveredSquareTiles += uncoverTile(tile.topTile!!)
                    } else {
                        if (tile.topTile!!.state != 9) {
                            tile.topTile!!.isCovered = false
                        }
                    }
                }
                if (tile.bottomTile != null) {
                    if (tile.bottomTile!!.state == 0) {
                        uncoveredSquareTiles += uncoverTile(tile.bottomTile!!)
                    } else {
                        if (tile.bottomTile!!.state != 9) {
                            tile.bottomTile!!.isCovered = false
                        }
                    }
                }
                if (tile.leftTile != null) {
                    if (tile.leftTile!!.state == 0) {
                        uncoveredSquareTiles += uncoverTile(tile.leftTile!!)
                    } else {
                        if (tile.leftTile!!.state != 9) {
                            tile.leftTile!!.isCovered = false
                        }
                    }
                }
                if (tile.rightTile != null) {
                    if (tile.rightTile!!.state == 0) {
                        uncoveredSquareTiles += uncoverTile(tile.rightTile!!)
                    } else {
                        if (tile.rightTile!!.state != 9) {
                            tile.rightTile!!.isCovered = false
                        }
                    }
                }
            }
        }
        return uncoveredSquareTiles
    }

    private fun uncoverAll(tile: SquareTile?): Array<SquareTile?> {
        var uncoveredSquareTiles: Array<SquareTile?> = arrayOfNulls(x * y)
        if (tile != null) {
            if (!tile.triggered) {
                tile.isCovered = false
                tile.triggered = true
                uncoveredSquareTiles += uncoverAll(tile.topTile)
                uncoveredSquareTiles += uncoverAll(tile.bottomTile)
                uncoveredSquareTiles += uncoverAll(tile.leftTile)
                uncoveredSquareTiles += uncoverAll(tile.rightTile)
            }
        }
        return uncoveredSquareTiles
    }

    private fun value(tile: SquareTile): Int {
        var tmpValue = 0
        if (tile.state == 1) {
            tmpValue = 9
        } else {
            if (tile.topTile != null) {
                if ((tile.topTile!!.state == 0) or (tile.topTile!!.state == 1)) {
                    tmpValue += tile.topTile!!.state
                } else {
                    if (tile.topTile!!.state == 19) {
                        tmpValue += 1
                    }
                }
            }
            if (tile.bottomTile != null) {
                if ((tile.bottomTile!!.state == 0) or (tile.bottomTile!!.state == 1)) {
                    tmpValue += tile.bottomTile!!.state
                } else {
                    if (tile.bottomTile!!.state == 19) {
                        tmpValue += 1
                    }
                }
            }
            if (tile.leftTile != null) {
                if ((tile.leftTile!!.state == 0) or (tile.leftTile!!.state == 1)) {
                    tmpValue += tile.leftTile!!.state
                } else {
                    if (tile.leftTile!!.state == 19) {
                        tmpValue += 1
                    }
                }
            }
            if (tile.rightTile != null) {
                if ((tile.rightTile!!.state == 0) or (tile.rightTile!!.state == 1)) {
                    tmpValue += tile.rightTile!!.state
                } else {
                    if (tile.rightTile!!.state == 19) {
                        tmpValue += 1
                    }
                }
            }
            if (tile.topLeftTile != null) {
                if ((tile.topLeftTile!!.state == 0) or (tile.topLeftTile!!.state == 1)) {
                    tmpValue += tile.topLeftTile!!.state
                } else {
                    if (tile.topLeftTile!!.state == 19) {
                        tmpValue += 1
                    }
                }
            }
            if (tile.topRightTile != null) {
                if ((tile.topRightTile!!.state == 0) or (tile.topRightTile!!.state == 1)) {
                    tmpValue += tile.topRightTile!!.state
                } else {
                    if (tile.topRightTile!!.state == 19) {
                        tmpValue += 1
                    }
                }
            }
            if (tile.bottomLeftTile != null) {
                if ((tile.bottomLeftTile!!.state == 0) or (tile.bottomLeftTile!!.state == 1)) {
                    tmpValue += tile.bottomLeftTile!!.state
                } else {
                    if (tile.bottomLeftTile!!.state == 19) {
                        tmpValue += 1
                    }
                }
            }
            if (tile.bottomRightTile != null) {
                if ((tile.bottomRightTile!!.state == 0) or (tile.bottomRightTile!!.state == 1)) {
                    tmpValue += tile.bottomRightTile!!.state
                } else {
                    if (tile.bottomRightTile!!.state == 19) {
                        tmpValue += 1
                    }
                }
            }
        }
        // Log.println(Log.INFO, "tmpValue", (tmpValue + 10).toString())
        return tmpValue + 10
    }

    private fun calculateValues(tile: SquareTile) {
        tmpValueCalc(tile)
        finalValueCalc(tile)
    }

    private fun tmpValueCalc(tile: SquareTile?) {
        if (tile != null) {
            if (!tile.tmpCalc) {
                tile.state = value(tile)
                tile.tmpCalc = true
                // recursively check bordering Tiles calculate their
                // transitional state value and continue
                // Log.println(Log.INFO, "Null", (bottomTile != null).toString())
                // Log.println(Log.INFO, "Null", (rightTile != null).toString())
                // top_tile
                tmpValueCalc(tile.topTile)
                // bottom_tile
                tmpValueCalc(tile.bottomTile)
                // left_tile
                tmpValueCalc(tile.leftTile)
                // right_tile
                tmpValueCalc(tile.rightTile)
            }
        }
    }

    private fun finalValueCalc(tile: SquareTile?) {
        if (tile != null) {
            if (!tile.finalCalc) {
                tile.state -= 10
                tile.finalCalc = true
                // recursively check bordering Tiles calculate their
                // final state value and continue
                // top_tile
                finalValueCalc(tile.topTile)
                // bottom_tile
                finalValueCalc(tile.bottomTile)
                // left_tile
                finalValueCalc(tile.leftTile)
                // right_tile
                finalValueCalc(tile.rightTile)
            }
        }
    }
}