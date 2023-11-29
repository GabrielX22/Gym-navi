package com.example.register;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EditProfileActivity extends AppCompatActivity {

    private EditText newNameEditText;
    private EditText newEmailEditText;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        newNameEditText = findViewById(R.id.name_edit_text);
        newEmailEditText = findViewById(R.id.email_edit_text);
        saveButton = findViewById(R.id.save_button);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveProfileChanges();
            }
        });
    }

    private void saveProfileChanges() {
        String newName = newNameEditText.getText().toString();
        String newEmail = newEmailEditText.getText().toString();

        Intent resultIntent = new Intent();
        resultIntent.putExtra("newName", newName);
        resultIntent.putExtra("newEmail", newEmail);
        setResult(RESULT_OK, resultIntent);
        finish();
    }
}
