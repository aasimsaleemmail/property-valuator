package com.vehicleidentification;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
    private static final int CAMERA = 100;
    private static final int GALLERY = 101;
    private TextView result, network;

    @Override public void onCreate(Bundle b) {
        super.onCreate(b);
        setContentView(R.layout.activity_main);

        result = findViewById(R.id.result);
        network = findViewById(R.id.network);
        updateNetwork();

        findViewById(R.id.cameraButton).setOnClickListener(v -> openCamera());
        findViewById(R.id.galleryButton).setOnClickListener(v -> openGallery());
        findViewById(R.id.databaseButton).setOnClickListener(v ->
            show("Vehicle Databases\n\nNHTSA vPIC: planned integration\nAdditional worldwide open datasets: planned\n\nThis MVP is ready for database modules."));
        findViewById(R.id.savedButton).setOnClickListener(v ->
            show("Saved Vehicles\n\nNo confirmed vehicles saved yet."));

    }

    private void openCamera() {
        if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.CAMERA}, CAMERA);
            return;
        }
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (i.resolveActivity(getPackageManager()) != null) startActivityForResult(i, CAMERA);
    }

    private void openGallery() {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, GALLERY);
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            result.setText(requestCode == CAMERA
                ? "Vehicle photo captured.\n\nNext AI module: detect vehicle → classify → match against installed vehicle database."
                : "Vehicle photo selected.\n\nNext AI module: detect vehicle → classify → match against installed vehicle database.");
            Toast.makeText(this, "Photo received", Toast.LENGTH_SHORT).show();
        }
    }

    private void show(String s) { result.setText(s); }

    private void updateNetwork() {
        ConnectivityManager cm = (ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        NetworkCapabilities nc = cm.getNetworkCapabilities(cm.getActiveNetwork());
        boolean online = nc != null && (nc.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
                || nc.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR));
        network.setText(online ? "● Online" : "● Offline");
    }
}
