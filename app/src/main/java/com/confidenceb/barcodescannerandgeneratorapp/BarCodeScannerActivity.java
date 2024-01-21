package com.confidenceb.barcodescannerandgeneratorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

public class BarCodeScannerActivity extends AppCompatActivity {

    private TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar_code_scanner);

        resultTextView = findViewById(R.id.scannedResultTextView);

        // Initiate barcode scanning
        new IntentIntegrator(this).initiateScan();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null) {
            if (result.getContents() != null) {
                // Handle the scanned barcode result here
                String scannedData = result.getContents();

                // Display the scanned barcode content in a TextView
                resultTextView.setText("Scanned Result: " + scannedData);

                // You can perform other actions with the scannedData as needed
                // For example, you may want to save it, send it to a server, etc.
            } else {
                resultTextView.setText("No Barcode Detected");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}