package com.example.register;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class activity_perfil extends AppCompatActivity {
    private ImageView profileImageView;
    private Button selectImageButton;
    private EditText nameEditText;
    private EditText emailEditText;
    private Button saveButton;
    private Button backButton;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int EDIT_PROFILE_REQUEST = 2;
    private static final int REQUEST_PERMISSION_READ_STORAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        profileImageView = findViewById(R.id.profile_image_view);
        selectImageButton = findViewById(R.id.select_image_button);
        nameEditText = findViewById(R.id.name_edit_text);
        emailEditText = findViewById(R.id.email_edit_text);
        saveButton = findViewById(R.id.save_button);
        backButton = findViewById(R.id.back_button);

        SharedPreferences sharedPref = getSharedPreferences("Perfil", Context.MODE_PRIVATE);
        String text = sharedPref.getString("1", "");
        String text1 = sharedPref.getString("2", "");

        emailEditText.setText(text);
        nameEditText.setText(text1);

        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int REQUEST_PERMISSION_READ_STORAGE = 100;
                if (ContextCompat.checkSelfPermission(activity_perfil.this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(activity_perfil.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, REQUEST_PERMISSION_READ_STORAGE);
                } else {
                    openGallery();
                }
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = emailEditText.getText().toString();
                String text1 = nameEditText.getText().toString();
                SharedPreferences sharedPref = getSharedPreferences("Perfil", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putString("1", text);
                editor.putString("2", text1);
                editor.apply();
            }
        });
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_PERMISSION_READ_STORAGE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                openGallery();
            } else {
                Toast.makeText(this, "Permiso denegado para leer el almacenamiento externo", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
    }

    private void saveProfileChanges() {
        String newName = nameEditText.getText().toString();
        String newEmail = emailEditText.getText().toString();

        // You can save the newName and newEmail in your preferred storage (e.g., SharedPreferences).

        // For now, let's just update the UI.
        nameEditText.setText(newName);
        emailEditText.setText(newEmail);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData();
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profileImageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (requestCode == EDIT_PROFILE_REQUEST && resultCode == RESULT_OK && data != null) {
            // Update the profile information based on the edited data
            String newName = data.getStringExtra("newName");
            String newEmail = data.getStringExtra("newEmail");

            nameEditText.setText(newName);
            emailEditText.setText(newEmail);
        }
    }

    private void openEditProfileActivity() {
        Intent intent = new Intent(this, activity_perfil.class);
        startActivityForResult(intent, EDIT_PROFILE_REQUEST);
    }

    public void vuelves(View view) {
        finish(); // Cierra la actividad actual y vuelve a la actividad anterior
    }
}