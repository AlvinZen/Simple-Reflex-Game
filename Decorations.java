package com.example.uasver1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class Decorations extends View {

    public Decorations(Context context, AttributeSet attrs) {
        super(context,attrs);
        this.postInvalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Mengatur warna baru
        Paint red = new Paint();
        Paint green = new Paint();
        Paint randcolor = new Paint();
        Paint black = new Paint();

        //Mengatur variabel warna
        randcolor.setARGB(255,255,255,255);
        black.setARGB(255,0,0,0);
        red.setARGB(255,255,0,0);
        green.setARGB(255,0,255,0);

        //Menggambar berbagai macam lingkaran berwarna hijau
        canvas.drawCircle(150,125,60,green);
        canvas.drawCircle(800,1234,90,green);

        //Menggambar berbagai macam lingkaran berwarna putih
        canvas.drawCircle(500,1000,40,randcolor);
        canvas.drawCircle(780,526,80,randcolor);

        //Menggambar berbagai macam lingkaran berwarna hitam
        canvas.drawCircle(227,300,10,black);
        canvas.drawCircle(714,841,30,black);

        //Menggambar berbagai macam lingkaran berwarna merah
        canvas.drawCircle(300,500,50,red);
        canvas.drawCircle(458,1345,20,red);

    }
}
