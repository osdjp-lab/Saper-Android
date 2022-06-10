package com.example.saper

data class SaperSquaresTile(
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
    var topTile: SaperSquaresTile? = null
    var bottomTile: SaperSquaresTile? = null
    var leftTile: SaperSquaresTile? = null
    var rightTile: SaperSquaresTile? = null
    var topLeftTile: SaperSquaresTile? = null
    var topRightTile: SaperSquaresTile? = null
    var bottomLeftTile: SaperSquaresTile? = null
    var bottomRightTile: SaperSquaresTile? = null
}