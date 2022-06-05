package com.example.saper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// https://stackoverflow.com/questions/40587168/simple-android-grid-example-using-recyclerview-with-gridlayoutmanager-like-the

class HexagonsRecyclerViewAdapter internal constructor(context: Context?, data: Array<String>) :
    RecyclerView.Adapter<HexagonsRecyclerViewAdapter.ViewHolder>() {
    private val mData: Array<String>
    private val mInflater: LayoutInflater
    private var mClickListener: ItemClickListener? = null
    private var mLongClickListener: ItemLongClickListener? = null

    // data is passed into the constructor
    init {
        mInflater = LayoutInflater.from(context)
        mData = data
    }

    // inflates the cell layout from xml when needed
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view: View = mInflater.inflate(R.layout.recyclerview_square_tile, parent, false)
        return ViewHolder(view)
    }

    // binds the data to the TextView in each cell
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.myTextView.text = mData[position]
    }

    // total number of cells
    override fun getItemCount(): Int {
        return mData.size
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