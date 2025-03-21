package com.example.graphicalprimitives

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class SampleCanvas @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val paint= Paint()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        paint.setColor(Color.YELLOW)
        canvas.drawPaint(paint)
        //Simple mod. in activity_main.xml
        paint.setColor(Color.RED)
        canvas.drawCircle(200f,200f,100f, paint)
        paint.textSize=50f
        canvas.drawText("Circle",200f,400f,paint)

        paint.setColor(Color.GREEN)
        canvas.drawRect(350f, 100f, 600f, 300f, paint)
        canvas.drawText("Rectangle", 400f, 350f, paint)

        // Draw Square (Same width & height)
        paint.setColor(Color.BLUE)
        canvas.drawRect(100f, 450f, 300f, 650f, paint)
        canvas.drawText("Square", 130f, 700f, paint)

        // Draw Line
        paint.setColor(Color.BLACK)
        paint.strokeWidth = 10f
        canvas.drawLine(400f, 450f, 700f, 650f, paint)
        canvas.drawText("Line", 500f, 700f, paint)


    }
}
