package com.example.saper

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class SaperSquaresGameLossDialog : DialogFragment() {
    internal lateinit var listener: LossDialogListener

    interface LossDialogListener {
        fun onLossPositiveClick(dialog: DialogFragment)
        fun onLossNegativeClick(dialog: DialogFragment)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            listener = context as LossDialogListener
        } catch (e: ClassCastException) {
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction
            val builder = AlertDialog.Builder(it)
            builder.setMessage(R.string.game_loss)
                .setPositiveButton(R.string.replay,
                    DialogInterface.OnClickListener { dialog, id ->
                        // Restart game
                        listener.onLossPositiveClick(this)
                    })
                .setNegativeButton(R.string.menu_return,
                    DialogInterface.OnClickListener { dialog, id ->
                        // Return to menu
                        listener.onLossNegativeClick(this)
                    })
            // Create the AlertDialog object and return it
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}