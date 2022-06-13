package com.example.saper


import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.saper.SaperSquaresRecyclerViewAdapter.ItemClickListener
import com.example.saper.SaperSquaresRecyclerViewAdapter.ItemLongClickListener
import kotlin.properties.Delegates

class SaperSquaresActivity : AppCompatActivity(),
                             ItemClickListener,
                             ItemLongClickListener,
                             SaperSquaresGameLossDialog.LossDialogListener,
                             SaperSquaresGameWinDialog.WinDialogListener
{
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SaperSquaresRecyclerViewAdapter
    private var nrOfColumns by Delegates.notNull<Int>()
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
        recyclerView = findViewById<RecyclerView>(R.id.board)
        nrOfColumns = x
        initBoardView()
    }

    private fun initBoardView() {
        recyclerView.layoutManager = GridLayoutManager(this, nrOfColumns)
        adapter = SaperSquaresRecyclerViewAdapter(this, board)
        adapter.setClickListener(this)
        adapter.setLongClickListener(this)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(view: View?, position: Int) {
        val tx = position.mod(x)
        val ty = position.floorDiv(x)
        if (!board.isInitialized) {
            board.setup(tx, ty)
            val uncoveredSaperSquaresTiles: List<SaperSquaresTile> = board.uncover(tx, ty)
        } else {
            val uncoveredSaperSquaresTiles: List<SaperSquaresTile> = board.uncover(tx, ty)
        }
        adapter.notifyDataSetChanged()
//        Log.println(Log.INFO,"INFO","onitemClick is running")
//        for (tile in uncoveredSquareTiles) {
//            Log.println(Log.INFO, "Position", "${x * tile.y + tile.x}, $x, $y")
//            adapter?.notifyItemChanged(x * tile.y + tile.x)
//        }
//        Log.println(Log.INFO, "nrUncoveredFields", board.nrUncoveredFields.toString())
        if (board.nrUncoveredFields == x * y) {
            val dialog = SaperSquaresGameLossDialog()
            dialog.show(this.supportFragmentManager, "Game Over")
        } else if (board.nrUncoveredFields == x * y - nrMines) {
            val dialog = SaperSquaresGameWinDialog()
            dialog.show(this.supportFragmentManager, "Game Win")
        }
    }

    override fun onItemLongClick(view: View?, position: Int) {
        val tx = position.mod(x)
        val ty = position.floorDiv(x)
        board.flag(tx, ty)
        adapter.notifyItemChanged(position)
    }

    private fun onPositiveClick(dialog: DialogFragment) {
        board = SaperSquaresModel(x, y)
        initBoardView()
        adapter.notifyDataSetChanged()
    }

    private fun onNegativeClick(dialog: DialogFragment) {
        finish()
    }

    override fun onLossPositiveClick(dialog: DialogFragment) {
        onPositiveClick(dialog)
    }

    override fun onLossNegativeClick(dialog: DialogFragment) {
        onNegativeClick(dialog)
    }

    override fun onWinPositiveClick(dialog: DialogFragment) {
        onPositiveClick(dialog)
    }

    override fun onWinNegativeClick(dialog: DialogFragment) {
        onNegativeClick(dialog)
    }
}
