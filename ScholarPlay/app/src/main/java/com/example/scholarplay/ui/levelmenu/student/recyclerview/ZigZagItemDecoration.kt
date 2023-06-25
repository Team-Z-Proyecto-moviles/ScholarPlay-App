package com.example.scholarplay.ui.levelmenu.student.recyclerview

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.Shader
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlin.random.Random

class ZigZagItemDecoration() : RecyclerView.ItemDecoration() {

    private val pathPaint = Paint().apply {
        style = Paint.Style.STROKE
        strokeWidth = 60f
        isAntiAlias = true // Enable anti-aliasing
    }

    //   private val gradientColors = intArrayOf(Color.BLUE, Color.CYAN) // Set your desired gradient colors

    private val gradientColors = intArrayOf(
        Color.parseColor("#9EF6AF"), // Start color (RED)
        Color.parseColor("#2AE2E0")  // End color (GREEN)
    )
    private val curveOffset = 100f // Adjust this value to control the curvature

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val row = position / 2
        val column = position % 2

        val topOffset = if (row == 0) 0 else verticalOffset
        val bottomOffset = verticalOffset

        val leftOffset = if (column == 0) 0 else horizontalOffset
        val rightOffset = if (column == 0) horizontalOffset else 0

        outRect.set(leftOffset, topOffset, rightOffset, bottomOffset)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val childCount = parent.childCount

        for (i in 0 until childCount - 1) {
            val currentView = parent.getChildAt(i)
            val nextView = parent.getChildAt(i + 1)

            val startX = currentView.left.toFloat() + currentView.width.toFloat() / 2
            val startY = currentView.top.toFloat()
            val endX = nextView.left.toFloat() + nextView.width.toFloat() / 2
            val endY = nextView.bottom.toFloat()

            val midY = startY + (endY - startY) / 2

            val path = Path()
            path.moveTo(startX, startY)
            path.cubicTo(
                startX, midY - curveOffset, // Control point 1
                endX, midY + curveOffset,   // Control point 2
                endX, endY                   // End point
            )

            val gradient = LinearGradient(startX, startY, endX, endY, gradientColors, null, Shader.TileMode.CLAMP)
            pathPaint.shader = gradient

            c.drawPath(path, pathPaint)
        }
    }

    companion object {
        private const val verticalOffset = 250 // Vertical offset between rows
        private val horizontalOffsetRange = (500..700 step 100).toList() // Random horizontal offset values
        private val random = Random.Default

        private val horizontalOffset: Int
            get() = horizontalOffsetRange[random.nextInt(horizontalOffsetRange.size)]
    }
}