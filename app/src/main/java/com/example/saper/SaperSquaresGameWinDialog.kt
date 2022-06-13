package com.example.saper

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class SaperSquaresGameWinDialog : DialogFragment() {
    internal lateinit var listener: WinDialogListener

    interface WinDialogListener {
        fun onWinPositiveClick(dialog: DialogFragment)
        fun onWinNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as WinDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.game_win)
                .setPositiveButton(R.string.replay,
                    DialogInterface.OnClickListener { dialog, id ->
                        // Restart game
                        listener.onWinPositiveClick(this)
                    })
                .setNegativeButton(R.string.menu_return,
                    DialogInterface.OnClickListener { dialog, id ->
                        // Return to menu
                        listener.onWinNegativeClick(this)
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}