package com.example.saper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton

class Saper8x8 : AppCompatActivity() {
    private var row0 = arrayOfNulls<ImageButton?>(8)
    private var row1 = arrayOfNulls<ImageButton?>(8)
    private var row2 = arrayOfNulls<ImageButton?>(8)
    private var row3 = arrayOfNulls<ImageButton?>(8)
    private var row4 = arrayOfNulls<ImageButton?>(8)
    private var row5 = arrayOfNulls<ImageButton?>(8)
    private var row6 = arrayOfNulls<ImageButton?>(8)
    private var row7 = arrayOfNulls<ImageButton?>(8)
    private var buttonGrid = arrayOf(row0, row1, row2, row3, row4, row5, row6, row7)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saper8x8)
        initButtonArray()
    }

    private fun initButtonArray() {
        this.buttonGrid[0][0] = findViewById(R.id.imageButton00)
        this.buttonGrid[0][0]?.setOnLongClickListener {
            buttonGrid[0][0]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[0][0]?.setOnClickListener {
            buttonGrid[0][0]?.setImageResource(R.drawable.mine)
        }

        this.buttonGrid[0][1] = findViewById(R.id.imageButton01)
        this.buttonGrid[0][1]?.setOnLongClickListener {
            buttonGrid[0][1]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[0][1]?.setOnClickListener {
            buttonGrid[0][1]?.setImageResource(R.drawable.mine)
        }

        this.buttonGrid[0][2] = findViewById(R.id.imageButton02)
        this.buttonGrid[0][2]?.setOnLongClickListener {
            buttonGrid[0][2]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[0][2]?.setOnClickListener {
            buttonGrid[0][2]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[0][3] = findViewById(R.id.imageButton03)
        this.buttonGrid[0][3]?.setOnLongClickListener {
            buttonGrid[0][3]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[0][3]?.setOnClickListener {
            buttonGrid[0][3]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[0][4] = findViewById(R.id.imageButton04)
        this.buttonGrid[0][4]?.setOnLongClickListener {
            buttonGrid[0][4]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[0][4]?.setOnClickListener {
            buttonGrid[0][4]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[0][5] = findViewById(R.id.imageButton05)
        this.buttonGrid[0][5]?.setOnLongClickListener {
            buttonGrid[0][5]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[0][5]?.setOnClickListener {
            buttonGrid[0][5]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[0][6] = findViewById(R.id.imageButton06)
        this.buttonGrid[0][6]?.setOnLongClickListener {
            buttonGrid[0][6]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[0][6]?.setOnClickListener {
            buttonGrid[0][6]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[0][7] = findViewById(R.id.imageButton07)
        this.buttonGrid[0][7]?.setOnLongClickListener {
            buttonGrid[0][7]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[0][7]?.setOnClickListener {
            buttonGrid[0][7]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[1][0] = findViewById(R.id.imageButton10)
        this.buttonGrid[1][0]?.setOnLongClickListener {
            buttonGrid[1][0]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[1][0]?.setOnClickListener {
            buttonGrid[1][0]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[1][1] = findViewById(R.id.imageButton11)
        this.buttonGrid[1][1]?.setOnLongClickListener {
            buttonGrid[1][1]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[1][1]?.setOnClickListener {
            buttonGrid[1][1]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[1][2] = findViewById(R.id.imageButton12)
        this.buttonGrid[1][2]?.setOnLongClickListener {
            buttonGrid[1][2]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[1][2]?.setOnClickListener {
            buttonGrid[1][2]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[1][3] = findViewById(R.id.imageButton13)
        this.buttonGrid[1][3]?.setOnLongClickListener {
            buttonGrid[1][3]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[1][3]?.setOnClickListener {
            buttonGrid[1][3]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[1][4] = findViewById(R.id.imageButton14)
        this.buttonGrid[1][4]?.setOnLongClickListener {
            buttonGrid[1][4]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[1][4]?.setOnClickListener {
            buttonGrid[1][4]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[1][5] = findViewById(R.id.imageButton15)
        this.buttonGrid[1][5]?.setOnLongClickListener {
            buttonGrid[1][5]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[1][5]?.setOnClickListener {
            buttonGrid[1][5]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[1][6] = findViewById(R.id.imageButton16)
        this.buttonGrid[1][6]?.setOnLongClickListener {
            buttonGrid[1][6]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[1][6]?.setOnClickListener {
            buttonGrid[1][6]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[1][7] = findViewById(R.id.imageButton17)
        this.buttonGrid[1][7]?.setOnLongClickListener {
            buttonGrid[1][7]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[1][7]?.setOnClickListener {
            buttonGrid[1][7]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[2][0] = findViewById(R.id.imageButton20)
        this.buttonGrid[2][0]?.setOnLongClickListener {
            buttonGrid[2][0]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[2][0]?.setOnClickListener {
            buttonGrid[2][0]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[2][1] = findViewById(R.id.imageButton21)
        this.buttonGrid[2][1]?.setOnLongClickListener {
            buttonGrid[2][1]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[2][1]?.setOnClickListener {
            buttonGrid[2][1]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[2][2] = findViewById(R.id.imageButton22)
        this.buttonGrid[2][2]?.setOnLongClickListener {
            buttonGrid[2][2]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[2][2]?.setOnClickListener {
            buttonGrid[2][2]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[2][3] = findViewById(R.id.imageButton23)
        this.buttonGrid[2][3]?.setOnLongClickListener {
            buttonGrid[2][3]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[2][3]?.setOnClickListener {
            buttonGrid[2][3]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[2][4] = findViewById(R.id.imageButton24)
        this.buttonGrid[2][4]?.setOnLongClickListener {
            buttonGrid[2][4]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[2][4]?.setOnClickListener {
            buttonGrid[2][4]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[2][5] = findViewById(R.id.imageButton25)
        this.buttonGrid[2][5]?.setOnLongClickListener {
            buttonGrid[2][5]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[2][5]?.setOnClickListener {
            buttonGrid[2][5]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[2][6] = findViewById(R.id.imageButton26)
        this.buttonGrid[2][6]?.setOnLongClickListener {
            buttonGrid[2][6]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[2][6]?.setOnClickListener {
            buttonGrid[2][6]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[2][7] = findViewById(R.id.imageButton27)
        this.buttonGrid[2][7]?.setOnLongClickListener {
            buttonGrid[2][7]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[2][7]?.setOnClickListener {
            buttonGrid[2][7]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[3][0] = findViewById(R.id.imageButton30)
        this.buttonGrid[3][0]?.setOnLongClickListener {
            buttonGrid[3][0]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[3][0]?.setOnClickListener {
            buttonGrid[3][0]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[3][1] = findViewById(R.id.imageButton31)
        this.buttonGrid[3][1]?.setOnLongClickListener {
            buttonGrid[3][1]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[3][1]?.setOnClickListener {
            buttonGrid[3][1]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[3][2] = findViewById(R.id.imageButton32)
        this.buttonGrid[3][2]?.setOnLongClickListener {
            buttonGrid[3][2]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[3][2]?.setOnClickListener {
            buttonGrid[3][2]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[3][3] = findViewById(R.id.imageButton33)
        this.buttonGrid[3][3]?.setOnLongClickListener {
            buttonGrid[3][3]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[3][3]?.setOnClickListener {
            buttonGrid[3][3]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[3][4] = findViewById(R.id.imageButton34)
        this.buttonGrid[3][4]?.setOnLongClickListener {
            buttonGrid[3][4]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[3][4]?.setOnClickListener {
            buttonGrid[3][4]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[3][5] = findViewById(R.id.imageButton35)
        this.buttonGrid[3][5]?.setOnLongClickListener {
            buttonGrid[3][5]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[3][5]?.setOnClickListener {
            buttonGrid[3][5]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[3][6] = findViewById(R.id.imageButton36)
        this.buttonGrid[3][6]?.setOnLongClickListener {
            buttonGrid[3][6]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[3][6]?.setOnClickListener {
            buttonGrid[3][6]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[3][7] = findViewById(R.id.imageButton37)
        this.buttonGrid[3][7]?.setOnLongClickListener {
            buttonGrid[3][7]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[3][7]?.setOnClickListener {
            buttonGrid[3][7]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[4][0] = findViewById(R.id.imageButton40)
        this.buttonGrid[4][0]?.setOnLongClickListener {
            buttonGrid[4][0]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[4][0]?.setOnClickListener {
            buttonGrid[4][0]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[4][1] = findViewById(R.id.imageButton41)
        this.buttonGrid[4][1]?.setOnLongClickListener {
            buttonGrid[4][1]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[4][1]?.setOnClickListener {
            buttonGrid[4][1]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[4][2] = findViewById(R.id.imageButton42)
        this.buttonGrid[4][2]?.setOnLongClickListener {
            buttonGrid[4][2]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[4][2]?.setOnClickListener {
            buttonGrid[4][2]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[4][3] = findViewById(R.id.imageButton43)
        this.buttonGrid[4][3]?.setOnLongClickListener {
            buttonGrid[4][3]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[4][3]?.setOnClickListener {
            buttonGrid[4][3]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[4][4] = findViewById(R.id.imageButton44)
        this.buttonGrid[4][4]?.setOnLongClickListener {
            buttonGrid[4][4]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[4][4]?.setOnClickListener {
            buttonGrid[4][4]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[4][5] = findViewById(R.id.imageButton45)
        this.buttonGrid[4][5]?.setOnLongClickListener {
            buttonGrid[4][5]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[4][5]?.setOnClickListener {
            buttonGrid[4][5]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[4][6] = findViewById(R.id.imageButton46)
        this.buttonGrid[4][6]?.setOnLongClickListener {
            buttonGrid[4][6]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[4][6]?.setOnClickListener {
            buttonGrid[4][6]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[4][7] = findViewById(R.id.imageButton47)
        this.buttonGrid[4][7]?.setOnLongClickListener {
            buttonGrid[4][7]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[4][7]?.setOnClickListener {
            buttonGrid[4][7]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[5][0] = findViewById(R.id.imageButton50)
        this.buttonGrid[5][0]?.setOnLongClickListener {
            buttonGrid[5][0]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[5][0]?.setOnClickListener {
            buttonGrid[5][0]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[5][1] = findViewById(R.id.imageButton51)
        this.buttonGrid[5][1]?.setOnLongClickListener {
            buttonGrid[5][1]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[5][1]?.setOnClickListener {
            buttonGrid[5][1]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[5][2] = findViewById(R.id.imageButton52)
        this.buttonGrid[5][2]?.setOnLongClickListener {
            buttonGrid[5][2]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[5][2]?.setOnClickListener {
            buttonGrid[5][2]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[5][3] = findViewById(R.id.imageButton53)
        this.buttonGrid[5][3]?.setOnLongClickListener {
            buttonGrid[5][3]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[5][3]?.setOnClickListener {
            buttonGrid[5][3]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[5][4] = findViewById(R.id.imageButton54)
        this.buttonGrid[5][4]?.setOnLongClickListener {
            buttonGrid[5][4]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[5][4]?.setOnClickListener {
            buttonGrid[5][4]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[5][5] = findViewById(R.id.imageButton55)
        this.buttonGrid[5][5]?.setOnLongClickListener {
            buttonGrid[5][5]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[5][5]?.setOnClickListener {
            buttonGrid[5][5]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[5][6] = findViewById(R.id.imageButton56)
        this.buttonGrid[5][6]?.setOnLongClickListener {
            buttonGrid[5][6]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[5][6]?.setOnClickListener {
            buttonGrid[5][6]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[5][7] = findViewById(R.id.imageButton57)
        this.buttonGrid[5][7]?.setOnLongClickListener {
            buttonGrid[5][7]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[5][7]?.setOnClickListener {
            buttonGrid[5][7]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[6][0] = findViewById(R.id.imageButton60)
        this.buttonGrid[6][0]?.setOnLongClickListener {
            buttonGrid[6][0]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[6][0]?.setOnClickListener {
            buttonGrid[6][0]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[6][1] = findViewById(R.id.imageButton61)
        this.buttonGrid[6][1]?.setOnLongClickListener {
            buttonGrid[6][1]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[6][1]?.setOnClickListener {
            buttonGrid[6][1]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[6][2] = findViewById(R.id.imageButton62)
        this.buttonGrid[6][2]?.setOnLongClickListener {
            buttonGrid[6][2]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[6][2]?.setOnClickListener {
            buttonGrid[6][2]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[6][3] = findViewById(R.id.imageButton63)
        this.buttonGrid[6][3]?.setOnLongClickListener {
            buttonGrid[6][3]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[6][3]?.setOnClickListener {
            buttonGrid[6][3]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[6][4] = findViewById(R.id.imageButton64)
        this.buttonGrid[6][4]?.setOnLongClickListener {
            buttonGrid[6][4]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[6][4]?.setOnClickListener {
            buttonGrid[6][4]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[6][5] = findViewById(R.id.imageButton65)
        this.buttonGrid[6][5]?.setOnLongClickListener {
            buttonGrid[6][5]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[6][5]?.setOnClickListener {
            buttonGrid[6][5]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[6][6] = findViewById(R.id.imageButton66)
        this.buttonGrid[6][6]?.setOnLongClickListener {
            buttonGrid[6][6]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[6][6]?.setOnClickListener {
            buttonGrid[6][6]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[6][7] = findViewById(R.id.imageButton67)
        this.buttonGrid[6][7]?.setOnLongClickListener {
            buttonGrid[6][7]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[6][7]?.setOnClickListener {
            buttonGrid[6][7]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[7][0] = findViewById(R.id.imageButton70)
        this.buttonGrid[7][0]?.setOnLongClickListener {
            buttonGrid[7][0]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[7][0]?.setOnClickListener {
            buttonGrid[7][0]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[7][1] = findViewById(R.id.imageButton71)
        this.buttonGrid[7][1]?.setOnLongClickListener {
            buttonGrid[7][1]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[7][1]?.setOnClickListener {
            buttonGrid[7][1]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[7][2] = findViewById(R.id.imageButton72)
        this.buttonGrid[7][2]?.setOnLongClickListener {
            buttonGrid[7][2]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[7][2]?.setOnClickListener {
            buttonGrid[7][2]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[7][3] = findViewById(R.id.imageButton73)
        this.buttonGrid[7][3]?.setOnLongClickListener {
            buttonGrid[7][3]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[7][3]?.setOnClickListener {
            buttonGrid[7][3]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[7][4] = findViewById(R.id.imageButton74)
        this.buttonGrid[7][4]?.setOnLongClickListener {
            buttonGrid[7][4]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[7][4]?.setOnClickListener {
            buttonGrid[7][4]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[7][5] = findViewById(R.id.imageButton75)
        this.buttonGrid[7][5]?.setOnLongClickListener {
            buttonGrid[7][5]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[7][5]?.setOnClickListener {
            buttonGrid[7][5]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[7][6] = findViewById(R.id.imageButton76)
        this.buttonGrid[7][6]?.setOnLongClickListener {
            buttonGrid[7][6]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[7][6]?.setOnClickListener {
            buttonGrid[7][6]?.setImageResource(R.drawable.mine)
        }
        this.buttonGrid[7][7] = findViewById(R.id.imageButton77)
        this.buttonGrid[7][7]?.setOnLongClickListener {
            buttonGrid[7][7]?.setImageResource(R.drawable.flagged_tile)
            true
        }
        this.buttonGrid[7][7]?.setOnClickListener {
            buttonGrid[7][7]?.setImageResource(R.drawable.mine)
        }
    }
}