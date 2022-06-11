package com.example.saper

import kotlin.random.Random.Default.nextInt

class SaperSquaresModel(
    val x: Int = 8,
    val y: Int = 8,
    private val nrMines: Int = 10
) {

    var isInitialized = false
    var nrInitUncoveredEmptyFields = 4
    private var minefield = Array(x) {Array(y) {0}}
    var tileGrid = Array(x){Array<SaperSquaresTile?>(y){null}}

    class Coordinate (
        val x: Int,
        val y: Int
    )

    /** minefield initialization */
    private fun initMinefield(px: Int, py: Int) {
        isInitialized = true
        // create array of automatically uncovered empty fields
        val uncoveredEmptyFields = Array<Coordinate?>(nrInitUncoveredEmptyFields){null}
        uncoveredEmptyFields[0] = Coordinate(px, py)
        // randomly pick uncoveredEmptyFields
        var i = 1
        while (i < nrInitUncoveredEmptyFields) {
            val a = nextInt(nrInitUncoveredEmptyFields)
            if (uncoveredEmptyFields[a] != null) {
                val tx = uncoveredEmptyFields[a]!!.x
                val ty = uncoveredEmptyFields[a]!!.y
                // randomly pick offset vector
                var dx: Int
                var dy: Int
                if (tx > 0) {
                    if (tx < x - 1) {
                        dx = nextInt(-1, 2)
                    } else { // tx = this.x - 1
                        dx = nextInt(-1, 1)
                    }
                } else { // tx = 0
                    dx = nextInt(0, 2)
                }
                val values = listOf(-1, 1)
                if (ty > 0) {
                    if (ty < y - 1) {
                        if (dx == 0) {
                            dy = values[nextInt(values.size)]
                        } else {
                            dy = 0
                        }
                    } else { // ty = this.y - 1
                        if (dx == 0) {
                            dy = -1
                        } else {
                            dy = 0
                        }
                    }
                } else { // ty = 0
                    if (dx == 0) {
                        dy = 1
                    } else {
                        dy = 0
                    }
                }
                // check if uncovered
                val tmp = Coordinate(uncoveredEmptyFields[a]!!.x + dx, uncoveredEmptyFields[a]!!.y + dy)
                var isContained = false
                for (elem in uncoveredEmptyFields) {
                    if (elem != null) {
                        if (elem.x == tmp.x && elem.y == tmp.y) {
                            isContained = true
                        }
                    }
                }
                if (!isContained) {
                    var j = 0
                    while (uncoveredEmptyFields[j] != null) {
                        j++
                    }
                    uncoveredEmptyFields[j] = tmp
                    i++
                }
            }
        }
        // create array of all automatically uncovered fields
        val uncoveredFields = Array<Coordinate?>(nrInitUncoveredEmptyFields*9){null}
        // copy uncovered empty fields
        for (j in 0 until nrInitUncoveredEmptyFields) {
            uncoveredFields[j] = uncoveredEmptyFields[j]
        }
        // find fields surrounding uncovered empty fields
        // and append to uncoveredFields array
        i = 0
        var j = nrInitUncoveredEmptyFields
        while (i < nrInitUncoveredEmptyFields) {
            if (uncoveredEmptyFields[i]!!.x > 0 && uncoveredEmptyFields[i]!!.y > 0) {
                val tmp = Coordinate(uncoveredEmptyFields[i]!!.x - 1, uncoveredEmptyFields[i]!!.y - 1)
                var isContained = false
                for (elem in uncoveredFields) {
                    if (elem != null) {
                        if (elem.x == tmp.x && elem.y == tmp.y) {
                            isContained = true
                        }
                    }
                }
                if (!isContained) {
                    uncoveredFields[j] = tmp
                    j++
                }
            }
            if (uncoveredEmptyFields[i]!!.y > 0) {
                val tmp = Coordinate(uncoveredEmptyFields[i]!!.x, uncoveredEmptyFields[i]!!.y - 1)
                var isContained = false
                for (elem in uncoveredFields) {
                    if (elem != null) {
                        if (elem.x == tmp.x && elem.y == tmp.y) {
                            isContained = true
                        }
                    }
                }
                if (!isContained) {
                    uncoveredFields[j] = tmp
                    j++
                }
            }
            if (uncoveredEmptyFields[i]!!.x < x && uncoveredEmptyFields[i]!!.y > 0) {
                val tmp = Coordinate(uncoveredEmptyFields[i]!!.x + 1, uncoveredEmptyFields[i]!!.y - 1)
                var isContained = false
                for (elem in uncoveredFields) {
                    if (elem != null) {
                        if (elem.x == tmp.x && elem.y == tmp.y) {
                            isContained = true
                        }
                    }
                }
                if (!isContained) {
                    uncoveredFields[j] = tmp
                    j++
                }
            }
            if (uncoveredEmptyFields[i]!!.x > 0) {
                val tmp = Coordinate(uncoveredEmptyFields[i]!!.x - 1, uncoveredEmptyFields[i]!!.y)
                var isContained = false
                for (elem in uncoveredFields) {
                    if (elem != null) {
                        if (elem.x == tmp.x && elem.y == tmp.y) {
                            isContained = true
                        }
                    }
                }
                if (!isContained) {
                    uncoveredFields[j] = tmp
                    j++
                }
            }
            if (uncoveredEmptyFields[i]!!.x < x) {
                val tmp = Coordinate(uncoveredEmptyFields[i]!!.x + 1, uncoveredEmptyFields[i]!!.y)
                var isContained = false
                for (elem in uncoveredFields) {
                    if (elem != null) {
                        if (elem.x == tmp.x && elem.y == tmp.y) {
                            isContained = true
                        }
                    }
                }
                if (!isContained) {
                    uncoveredFields[j] = tmp
                    j++
                }
            }
            if (uncoveredEmptyFields[i]!!.x > 0 && uncoveredEmptyFields[i]!!.y < y) {
                val tmp = Coordinate(uncoveredEmptyFields[i]!!.x - 1, uncoveredEmptyFields[i]!!.y + 1)
                var isContained = false
                for (elem in uncoveredFields) {
                    if (elem != null) {
                        if (elem.x == tmp.x && elem.y == tmp.y) {
                            isContained = true
                        }
                    }
                }
                if (!isContained) {
                    uncoveredFields[j] = tmp
                    j++
                }
            }
            if (uncoveredEmptyFields[i]!!.y < y) {
                val tmp = Coordinate(uncoveredEmptyFields[i]!!.x, uncoveredEmptyFields[i]!!.y + 1)
                var isContained = false
                for (elem in uncoveredFields) {
                    if (elem != null) {
                        if (elem.x == tmp.x && elem.y == tmp.y) {
                            isContained = true
                        }
                    }
                }
                if (!isContained) {
                    uncoveredFields[j] = tmp
                    j++
                }
            }
            if (uncoveredEmptyFields[i]!!.x < x && uncoveredEmptyFields[i]!!.y < y) {
                val tmp = Coordinate(uncoveredEmptyFields[i]!!.x + 1, uncoveredEmptyFields[i]!!.y + 1)
                var isContained = false
                for (elem in uncoveredFields) {
                    if (elem != null) {
                        if (elem.x == tmp.x && elem.y == tmp.y) {
                            isContained = true
                        }
                    }
                }
                if (!isContained) {
                    uncoveredFields[j] = tmp
                    j++
                }
            }
            i++
        }
        // fill minefield
        var n = 0
        while (n < nrMines) {
            val a = nextInt(0, x)
            val b = nextInt(0, y)
            val tmp = Coordinate(a, b)
            var isContained = false
            for (elem in uncoveredFields) {
                if (elem != null) {
                    if (elem.x == tmp.x && elem.y == tmp.y) {
                        isContained = true
                    }
                }
            }
            if (!isContained) {
                if (minefield[a][b] == 0) {
                    minefield[a][b] = 1
                    n++
                }
            }
        }
    }

    /** tileGrid initialization */
    private fun initTileGrid() {
        // Filling tileGrid with SaperSquaresTile's
        var tmp1 = x-1
        var tmp2 = y-1
        for (i in 0..tmp1) {
            for (j in 0..tmp2) {
                tileGrid[i][j] = SaperSquaresTile(x=i, y=j, state=minefield[i][j])
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
    }

    fun setup(px: Int, py: Int) {
        // Initialize minefield
        initMinefield(px, py)
        // Initialize tileGrid
        initTileGrid()
        // Calculation of values for each field
        calculateValues(tileGrid[0][0]!!)
    }

    fun flag(x: Int, y: Int) {
        if (tileGrid[x][y]!!.isCovered) {
            tileGrid[x][y]!!.isFlagged = !tileGrid[x][y]!!.isFlagged
        }
    }

    // BUG: only returns array of nulls
    fun uncover(x: Int, y: Int): List<SaperSquaresTile> {
        var uncoveredSaperSquaresTiles: Array<SaperSquaresTile?> = arrayOfNulls(x * y)
        if (tileGrid[x][y]!!.isCovered && !tileGrid[x][y]!!.isFlagged) {
            if (tileGrid[x][y]!!.state == 9) {
                uncoveredSaperSquaresTiles = uncoverAll(tileGrid[x][y])
            } else {
                uncoveredSaperSquaresTiles = uncoverTile(tileGrid[x][y]!!)
            }
        }
        return uncoveredSaperSquaresTiles.filterNotNull()
    }

    private fun uncoverTile(tile: SaperSquaresTile): Array<SaperSquaresTile?> {
        var uncoveredSaperSquaresTiles: Array<SaperSquaresTile?> = arrayOfNulls(x * y)
        // Secondary check of isCovered after uncover method - possibly unnecessary
        if (tile.isCovered) {
            tile.isCovered = false
            if (tile.state == 0) {
                if (tile.topTile != null) {
                    if (tile.topTile!!.state == 0) {
                        uncoveredSaperSquaresTiles += uncoverTile(tile.topTile!!)
                    } else {
                        if (tile.topTile!!.state != 9) {
                            tile.topTile!!.isCovered = false
                        }
                    }
                }
                if (tile.bottomTile != null) {
                    if (tile.bottomTile!!.state == 0) {
                        uncoveredSaperSquaresTiles += uncoverTile(tile.bottomTile!!)
                    } else {
                        if (tile.bottomTile!!.state != 9) {
                            tile.bottomTile!!.isCovered = false
                        }
                    }
                }
                if (tile.leftTile != null) {
                    if (tile.leftTile!!.state == 0) {
                        uncoveredSaperSquaresTiles += uncoverTile(tile.leftTile!!)
                    } else {
                        if (tile.leftTile!!.state != 9) {
                            tile.leftTile!!.isCovered = false
                        }
                    }
                }
                if (tile.rightTile != null) {
                    if (tile.rightTile!!.state == 0) {
                        uncoveredSaperSquaresTiles += uncoverTile(tile.rightTile!!)
                    } else {
                        if (tile.rightTile!!.state != 9) {
                            tile.rightTile!!.isCovered = false
                        }
                    }
                }
            }
        }
        return uncoveredSaperSquaresTiles
    }

    private fun uncoverAll(tile: SaperSquaresTile?): Array<SaperSquaresTile?> {
        var uncoveredSaperSquaresTiles: Array<SaperSquaresTile?> = arrayOfNulls(x * y)
        if (tile != null) {
            if (!tile.triggered) {
                tile.isCovered = false
                tile.triggered = true
                uncoveredSaperSquaresTiles += uncoverAll(tile.topTile)
                uncoveredSaperSquaresTiles += uncoverAll(tile.bottomTile)
                uncoveredSaperSquaresTiles += uncoverAll(tile.leftTile)
                uncoveredSaperSquaresTiles += uncoverAll(tile.rightTile)
            }
        }
        return uncoveredSaperSquaresTiles
    }

    private fun value(tile: SaperSquaresTile): Int {
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

    private fun calculateValues(tile: SaperSquaresTile) {
        tmpValueCalc(tile)
        finalValueCalc(tile)
    }

    private fun tmpValueCalc(tile: SaperSquaresTile?) {
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

    private fun finalValueCalc(tile: SaperSquaresTile?) {
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