package com.example.saper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SaperTrianglesActivity : AppCompatActivity(), SaperSquaresRecyclerViewAdapter.ItemClickListener {
    var adapter: SaperSquaresRecyclerViewAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saper_triangles)

        // data to populate the RecyclerView with
        val data = arrayOf(
            "\uD83D\uDCA5",
            "",
            "3",
            "4",
            "5",
            "6",
            "7",
            "8",
            "9",
            "10",
            "11",
            "12",
            "13",
            "14",
            "15",
            "16",
            "17",
            "18",
            "19",
            "20",
            "21",
            "22",
            "23",
            "24",
            "25",
            "26",
            "27",
            "28",
            "29",
            "30",
            "31",
            "32",
            "33",
            "34",
            "35",
            "36",
            "37",
            "38",
            "39",
            "40",
            "41",
            "42",
            "43",
            "44",
            "45",
            "46",
            "47",
            "48"
        )

        // set up the RecyclerView
        val recyclerView = findViewById<RecyclerView>(R.id.board)
        val numberOfColumns = 6
        recyclerView.layoutManager = GridLayoutManager(this, numberOfColumns)
//        adapter = SquaresRecyclerViewAdapter(this)
        adapter!!.setClickListener(this)
        recyclerView.adapter = adapter
    }

    override fun onItemClick(view: View?, position: Int) {
        val text = "Tile ${position+1} clicked"
        val duration = Toast.LENGTH_SHORT
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
//        Log.i(
//            "TAG",
//            "You clicked number $position, which is at cell position $position"
//        )
    }
}