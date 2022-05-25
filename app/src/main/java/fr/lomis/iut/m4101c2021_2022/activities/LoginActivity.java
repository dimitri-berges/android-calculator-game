package fr.lomis.iut.m4101c2021_2022.activities;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import fr.lomis.iut.m4101c2021_2022.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button signin_button = findViewById(R.id.sign_in);
        signin_button.setOnClickListener(view -> {
            EditText editTextPseudo = findViewById(R.id.edittext_pseudo);
            Editable editable = editTextPseudo.getText();
            if (editable.length() < 6) {
                Toast.makeText(this, R.string.pseudo_min_length, Toast.LENGTH_SHORT).show();
                return;
            }
            if (editable.length() > 20) {
                Toast.makeText(this, R.string.pseudo_max_length, Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, HomeActivity.class);
            intent.putExtra("pseudo", editable.toString());
            startActivity(intent);
        });
    }
}
