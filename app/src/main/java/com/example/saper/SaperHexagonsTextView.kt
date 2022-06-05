package com.example.saper

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Path
import android.graphics.Region
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatTextView
import kotlin.math.sqrt


class SaperHexagonsTextView : AppCompatTextView {
    private var hexagonPath: Path? = null
    private var hexagonBorderPath: Path? = null
    private var radius = 0f
    private var width = 0f
    private var height = 0f
    private var maskColor = 0

    constructor(context: Context?) : super(context!!) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context!!, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context!!,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        hexagonPath = Path()
        hexagonBorderPath = Path()
//        maskColor = -0xfe0089
    }

    fun setRadius(r: Float) {
        radius = r
        calculatePath()
    }

    fun setMaskColor(color: Int) {
        maskColor = color
        invalidate()
    }

    private fun calculatePath() {
        val triangleHeight = (sqrt(3.0) * radius / 2).toFloat()
        val centerX = width / 2
        val centerY = height / 2
        hexagonPath!!.moveTo(centerX, centerY + radius)
        hexagonPath!!.lineTo(centerX - triangleHeight, centerY + radius / 2)
        hexagonPath!!.lineTo(centerX - triangleHeight, centerY - radius / 2)
        hexagonPath!!.lineTo(centerX, centerY - radius)
        hexagonPath!!.lineTo(centerX + triangleHeight, centerY - radius / 2)
        hexagonPath!!.lineTo(centerX + triangleHeight, centerY + radius / 2)
        hexagonPath!!.moveTo(centerX, centerY + radius)
        val radiusBorder = radius - 5
        val triangleBorderHeight = (sqrt(3.0) * radiusBorder / 2).toFloat()
        hexagonBorderPath!!.moveTo(centerX, centerY + radiusBorder)
        hexagonBorderPath!!.lineTo(centerX - triangleBorderHeight, centerY + radiusBorder / 2)
        hexagonBorderPath!!.lineTo(centerX - triangleBorderHeight, centerY - radiusBorder / 2)
        hexagonBorderPath!!.lineTo(centerX, centerY - radiusBorder)
        hexagonBorderPath!!.lineTo(centerX + triangleBorderHeight, centerY - radiusBorder / 2)
        hexagonBorderPath!!.lineTo(centerX + triangleBorderHeight, centerY + radiusBorder / 2)
        hexagonBorderPath!!.moveTo(centerX, centerY + radiusBorder)
        invalidate()
    }

    public override fun onDraw(c: Canvas) {
        super.onDraw(c)
//        c.clipPath(hexagonBorderPath!!)
        c.clipPath(hexagonBorderPath!!, Region.Op.DIFFERENCE)
        c.drawColor(Color.WHITE)
        c.save()
//        c.clipPath(hexagonPath!!)
        c.clipPath(hexagonPath!!, Region.Op.DIFFERENCE)
        c.drawColor(maskColor)
        c.save()
    }

    // getting the view size and default radius
    public override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        width = MeasureSpec.getSize(widthMeasureSpec).toFloat()
        height = MeasureSpec.getSize(heightMeasureSpec).toFloat()
        radius = height / 2 - 10
        calculatePath()
    }
}