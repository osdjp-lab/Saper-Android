package com.example.saper

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

// https://stackoverflow.com/questions/40587168/simple-android-grid-example-using-recyclerview-with-gridlayoutmanager-like-the

class SaperSquaresRecyclerViewAdapter internal constructor(context: Context?, board: SaperSquaresModel) :
    RecyclerView.Adapter<SaperSquaresRecyclerViewAdapter.ViewHolder>() {
    private val mData: SaperSquaresModel
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null
    private var mLongClickListener: ItemLongClickListener? = null

    // data is passed into the constructor
    init {
        mInflater = LayoutInflater.from(context)
        mData = board
    }

    // inflates the cell layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.recyclerview_square_tile, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each cell
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val text = "${position.mod(mData.x)}, ${position.floorDiv(mData.x)}, $position}"
        Log.println(Log.INFO, "Tag", text)
        val tile: SaperSquaresTile? = mData.tileGrid[position.mod(mData.x)][position.floorDiv(mData.x)]
        if (tile == null) {
            val color = ContextCompat.getColor(holder.myTextView.context, R.color.gray_400)
            holder.myTextView.setBackgroundColor(color)
            holder.myTextView.text = ""
        } else {
            if (tile.isCovered) {
                if (tile.isFlagged) {
                    val color = ContextCompat.getColor(holder.myTextView.context, R.color.white)
                    holder.myTextView.setBackgroundColor(color)
                    holder.myTextView.text = holder.myTextView.context.getString(R.string.flag)
                } else {
                    val color = ContextCompat.getColor(holder.myTextView.context, R.color.gray_400)
                    holder.myTextView.setBackgroundColor(color)
                    holder.myTextView.text = ""
                }
            } else {
                val color = ContextCompat.getColor(holder.myTextView.context, R.color.white)
                holder.myTextView.setBackgroundColor(color)
                when (tile.state) {
                    0 -> holder.myTextView.text = ""
                    1 -> holder.myTextView.text = "1"
                    2 -> holder.myTextView.text = "2"
                    3 -> holder.myTextView.text = "3"
                    4 -> holder.myTextView.text = "4"
                    5 -> holder.myTextView.text = "5"
                    6 -> holder.myTextView.text = "6"
                    7 -> holder.myTextView.text = "7"
                    8 -> holder.myTextView.text = "8"
                    9 -> holder.myTextView.text = holder.myTextView.context.getString(R.string.bomb)
                }
            }
        }
    }

    // total number of cells
    override fun getItemCount(): Int {
        return mData.x * mData.y
    }

    // stores and recycles views as they are scrolled off screen
    inner class ViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener, View.OnLongClickListener {

        var myTextView: TextView

        override fun onClick(view: View?) {
            mClickListener!!.onItemClick(view, bindingAdapterPosition)
        }

        override fun onLongClick(view: View?): Boolean {
            mLongClickListener!!.onItemLongClick(view, bindingAdapterPosition)
            return true
        }

        init {
            myTextView = itemView.findViewById(R.id.info_text)
            itemView.setOnClickListener(this)
            itemView.setOnLongClickListener(this)
        }
    }

    // allows click events to be caught
    fun setClickListener(itemClickListener: ItemClickListener?) {
        mClickListener = itemClickListener
    }

    // allows long click events to be caught
    fun setLongClickListener(itemLongClickListener: ItemLongClickListener?) {
        mLongClickListener = itemLongClickListener
    }

    // parent activity will implement this method to respond to click events
    interface ItemClickListener {
        fun onItemClick(view: View?, position: Int)
    }

    // parent activity will implement this method to respond to long click events
    interface ItemLongClickListener {
        fun onItemLongClick(view: View?, position: Int)
    }
}