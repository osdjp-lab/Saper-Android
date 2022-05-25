package com.example.saper

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.saper.SquaresRecyclerViewAdapter.ItemClickListener
import com.example.saper.SquaresRecyclerViewAdapter.ItemLongClickListener

class SaperSquaresActivity : AppCompatActivity(), ItemClickListener, ItemLongClickListener {
    private var adapter: SquaresRecyclerViewAdapter? = null
    private val x = 6
    private val y = 10
    private val nrMines = 10
    private lateinit var board: SaperSquaresModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saper_squares)

        // set up board
        board = SaperSquaresModel(x, y, nrMines)

        // set up the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.board)
        val numberOfColumns = x
        recyclerView.layoutManager = GridLayoutManager(this, numberOfColumns)
        adapter = SquaresRecyclerViewAdapter(this, board)
        adapter?.setClickListener(this)
        adapter?.setLongClickListener(this)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(view: View?, position: Int) {
        val tx = position.mod(x)
        val ty = position.floorDiv(x)
        val uncoveredSquareTiles: List<SquareTile> = board.uncover(tx, ty)
        adapter?.notifyDataSetChanged()
//        Log.println(Log.INFO,"INFO","onitemClick is running")
//        for (tile in uncoveredSquareTiles) {
//            Log.println(Log.INFO, "Position", "${x * tile.y + tile.x}, $x, $y")
//            adapter?.notifyItemChanged(x * tile.y + tile.x)
//        }
    }

    override fun onItemLongClick(view: View?, position: Int) {
        val tx = position.mod(x)
        val ty = position.floorDiv(x)
        board.flag(tx, ty)
        adapter?.notifyItemChanged(position)
    }
}