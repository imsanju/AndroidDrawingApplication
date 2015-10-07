package edu.uc.cech.it.drawingapp;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by admin on 10/7/2015.
 */
public class CustomView extends View {

    public CustomView(Context context, AttributeSet attr)
    {
        super(context, attr);
       //calling the method(which sets up area for drawing)
        CreateDrawing();

    }

    public void CreateDrawing()
    {

    }
}
