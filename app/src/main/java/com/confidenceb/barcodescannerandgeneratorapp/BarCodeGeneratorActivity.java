package com.confidenceb.barcodescannerandgeneratorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class BarCodeGeneratorActivity extends AppCompatActivity {

    private EditText inputDataEditText;
    private Button generateBarcodeButton;
    private ImageView barcodeImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code_generator);

        inputDataEditText = findViewById(R.id.inputDataEditText);
        generateBarcodeButton = findViewById(R.id.generateBarcodeButton);
        barcodeImageView = findViewById(R.id.barcodeImageView);

        generateBarcodeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generateBarcode();
            }
        });
    }

    private void generateBarcode() {
        String inputData = inputDataEditText.getText().toString().trim();

        if (!inputData.isEmpty()) {
            try {
                // Generate QR code
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix bitMatrix = writer.encode(inputData, BarcodeFormat.QR_CODE, 512, 512);
                int width = bitMatrix.getWidth();
                int height = bitMatrix.getHeight();
                Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

                for (int x = 0; x < width; x++) {
                    for (int y = 0; y < height; y++) {
                        bitmap.setPixel(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                    }
                }

                // Display the generated barcode in the ImageView
                barcodeImageView.setImageBitmap(bitmap);

            } catch (WriterException e) {
                e.printStackTrace();
            }
        }
    }
}