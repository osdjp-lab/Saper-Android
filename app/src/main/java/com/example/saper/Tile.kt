package com.example.saper

import android.content.Context
import android.view.View
import android.widget.ImageButton
//import androidx.appcompat.widget.AppCompatImageButton

enum class TileState {
    ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, MINE, COVERED, FLAGGED
}

data class TilePosition(var x: Int, var y: Int)

public class Tile(context: Context) : ImageButton(context), View.OnClickListener, View.OnLongClickListener {
    var state = TileState.COVERED
    var position = TilePosition(0, 0)

    init {
        this.setImageResource(R.drawable.covered_tile)
    }

    override fun onClick(p0: View?) {
        this.setImageResource(R.drawable.mine)
        state = TileState.MINE
    }

    override fun onLongClick(p0: View?): Boolean {
        this.setImageResource(R.drawable.flagged_tile)
        state = TileState.FLAGGED
        return true
    }
}