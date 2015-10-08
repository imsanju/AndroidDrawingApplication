package edu.uc.cech.it.drawingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by admin on 10/7/2015.
 */
public class CustomView extends View {
    //initialize/add variables

    private Path drawingPath;
    private int initialColor = 0x010000;
    //drawing paint
    private Paint drawingPaint, canvasPaint;
    private Canvas drawingCanvas;
    private Bitmap canvasBitmap;


    public CustomView(Context context, AttributeSet attr)
    {
        super(context, attr);
       //calling the method(which sets up area for drawing)
        CreateDrawing();

    }

    public void CreateDrawing()
    {

        //instantiate drawing path and paint objects
      drawingPath = new Path();
        drawingPaint = new Paint();

        //set initial color
        drawingPaint.setColor(initialColor);

        //initial path properties
        drawingPaint.setStrokeWidth(20);
        drawingPaint.setStrokeJoin(Paint.Join.ROUND);
        drawingPaint.setAntiAlias(true);

        //instantiate canvas paint object
        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    protected void onSizeChanged(int newh, int neww, int h, int w)
    {
        //do something4
        super.onSizeChanged(newh, neww, h, w);
        canvasBitmap = Bitmap.createBitmap(newh, neww, Bitmap.Config.ARGB_8888);
        drawingCanvas = new Canvas(canvasBitmap);

    }

    protected void onDraw(Canvas canvas)
        //draw view
    {
        canvas.drawBitmap(canvasBitmap, 0,0,canvasPaint);
        canvas.drawPath(drawingPath, drawingPaint);
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        //detect user touch c0ordinates
        float gettouchX = event.getX();
        float gettouchY = event.getY();

        switch(event.getAction())
        {
            //when the user touches the canvas, get the coordinates and set the position
            case MotionEvent.ACTION_DOWN:
                drawingPath.moveTo(gettouchX, gettouchY);
                break;
            //when the user moves his finger on the canvas, a drawing path is formed along the coordinates
            case MotionEvent.ACTION_MOVE:
                drawingPath.lineTo(gettouchX, gettouchY);
                break;
            //if the user removes finger from canvas, reset
            case MotionEvent.ACTION_UP:
                drawingCanvas.drawPath(drawingPath,drawingPaint);
                drawingPath.reset();
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }



}
