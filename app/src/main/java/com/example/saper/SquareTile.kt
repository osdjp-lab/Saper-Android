package com.example.saper

data class SquareTile(
    var x: Int,
    var y: Int,
    var state: Int,
) {

    /** State variables */
    var isCovered: Boolean = true
    var isFlagged: Boolean = false
    var triggered: Boolean = false
    var tmpCalc: Boolean = false
    var finalCalc: Boolean = false

    /** Surrounding SquareTiles */
    var topTile: SquareTile? = null
    var bottomTile: SquareTile? = null
    var leftTile: SquareTile? = null
    var rightTile: SquareTile? = null
    var topLeftTile: SquareTile? = null
    var topRightTile: SquareTile? = null
    var bottomLeftTile: SquareTile? = null
    var bottomRightTile: SquareTile? = null
}