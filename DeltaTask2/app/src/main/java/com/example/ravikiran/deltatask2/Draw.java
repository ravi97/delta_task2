package com.example.ravikiran.deltatask2;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Matrix;

import android.view.View;
import android.widget.Toast;

/**
 * Created by Ravikiran on 6/13/2016.
 */
public class Draw extends View {

    private int x,y;
    private int direction=0;
    Bitmap b;
    Canvas canvas;

    public Draw(Context context) {
        super(context);
        x=0;y=0;
        direction=1;
        b=BitmapFactory.decodeResource(getResources(),R.mipmap.square);
        setSizeShape(1,1);

    }



    @Override
    protected void onDraw(Canvas c){
        super.onDraw(c);
        canvas=c;
        if(direction==1){
            if((y-20)<0){

                y=0;
            }
            else{
                y-=20;
            }
            direction=0;
        }
        else if(direction==2){
            if((y+20)>(canvas.getHeight()-b.getHeight())){
                y=canvas.getHeight()-b.getHeight();
            }
            else{
                y+=20;
            }
            direction=0;
        }
        else if(direction==3){
            if((x-20)<0){
                x=0;
            }
            else{
                x-=20;
            }
            direction=0;
        }
        else if(direction==4){
            if((x+20)>(canvas.getWidth()-b.getHeight())){
                x=canvas.getWidth()-b.getWidth();
            }
            else{
                x+=20;
            }
            direction=0;
        }
        else{

        }
        Paint p=new Paint();
        c.drawBitmap(b,x,y,p);
        invalidate();
    }
    public void getDirection(int dir){
        direction=dir;
    }
    public void setSizeShape(int size, int shape){
        if(shape==1) {
            b = BitmapFactory.decodeResource(getResources(), R.mipmap.square);
            if (size == 1) {
                Bitmap bn = resizeBitmap(b, 50, 50);
                b.recycle();
                b = bn;
            }
            else if (size == 2) {
                Bitmap bn = resizeBitmap(b, 100, 100);
                b.recycle();
                b = bn;
            }
            else {
                Bitmap bn = resizeBitmap(b, 150, 150);
                b.recycle();
                b = bn;
            }
        }

        else if(shape==2){
                b=BitmapFactory.decodeResource(getResources(),R.mipmap.circle);
                if(size==1){
                    Bitmap bn=resizeBitmap(b,50,50);
                    b.recycle();
                    b=bn;
                }
                else if(size==2){
                    Bitmap bn=resizeBitmap(b,100,100);
                    b.recycle();
                    b=bn;
                }
                else{
                    Bitmap bn=resizeBitmap(b,150,150);
                    b.recycle();
                    b=bn;
                }
            }

        else{
                b= BitmapFactory.decodeResource(getResources(), R.mipmap.triangle);
                if(size==1){
                    Bitmap bn=resizeBitmap(b,50,50);
                    b.recycle();
                    b=bn;
                }
                else if(size==2){
                    Bitmap bn=resizeBitmap(b,100,100);
                    b.recycle();
                    b=bn;
                }
                else{
                    Bitmap bn=resizeBitmap(b,150,150);
                    b.recycle();
                    b=bn;
                }

        }

        if(canvas!=null) {
            if (x > canvas.getWidth() - b.getWidth())
                x = canvas.getWidth() - b.getWidth();
            if (y > canvas.getHeight() - b.getHeight())
                y = canvas.getHeight() - b.getHeight();
        }
    }
    public Bitmap resizeBitmap(Bitmap bitmapToScale,float newWidth,float newHeight){
        int width=bitmapToScale.getWidth();
        int height=bitmapToScale.getHeight();
        Matrix m = new Matrix();
        m.postScale(((float)newWidth)/width,((float)newHeight)/height);
        Bitmap bnew=Bitmap.createBitmap(bitmapToScale,0,0,bitmapToScale.getWidth(),bitmapToScale.getHeight(),m,false);
        return bnew;
    }

}
