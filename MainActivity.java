package com.example.uasver1;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public Button startButton, stopButton;
    public RelativeLayout relativeLayout;
    public ImageView imageView;
    public Button imagebutton;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;

    Runnable runnable = new Runnable() {
        @Override
        public void run() {

            // Mengganti warna background
            relativeLayout.setBackgroundResource(R.color.purple_200);

            // Menggunakan waktu ms / millisecond
            final long time = System.currentTimeMillis();

            // Fungsi untuk tombol stop ditekan
            stopButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    long time1 = System.currentTimeMillis();

                    // Menunjukkan hasil reaksi
                    Toast.makeText(getApplicationContext(), "It takes " +
                            (time1 - time) + "ms to react", Toast.LENGTH_LONG).show();

                    // remove the background again
                    relativeLayout.setBackgroundResource(0);
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        relativeLayout = findViewById(R.id.rl1);
        startButton = findViewById(R.id.start);
        stopButton = findViewById(R.id.stop);

        imageView = findViewById(R.id.imageview);
        imagebutton = findViewById(R.id.imagebutton);

        // Fungsi untuk tombol start ditekan
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Nomor random 1-10
                Random random = new Random();
                int num = random.nextInt(10);

                // Menentukan waktu untuk mengganti background
                Handler handler = new Handler();
                handler.postDelayed(runnable, num * 1000);
            }
        });

        imagebutton.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v)
            {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            imageView.setImageBitmap(photo);
        }
    }

    public void PlayBGM(View view) {
        Intent intent = new Intent(MainActivity.this, MusicBGM.class);
        startService(intent);
    }
}