package com.example.logginapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class RegisterActivity extends AppCompatActivity {

    private Button btn;
    private EditText nombre, apellido, correo, password;
    private  Button btnRegistrar;

    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);

        dbHelper = new DatabaseHelper(this);

        nombre = findViewById(R.id.nombreEditText);
        apellido = findViewById(R.id.apellidoEditText);
        correo = findViewById(R.id.correoEditText);
        password = findViewById(R.id.passwordEditText);
        btn = findViewById(R.id.btnregistrer);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreText = nombre.getText().toString();
                String apellidoText = apellido.getText().toString();
                String correoText = correo.getText().toString();
                String passwordText = password.getText().toString();

                if (!nombreText.isEmpty() && !apellidoText.isEmpty() && !correoText.isEmpty() && !passwordText.isEmpty()) {
                    insertUser(nombreText, apellidoText, correoText, passwordText);
                    Toast.makeText(RegisterActivity.this, "Usuario Rigistrado exitosamente",Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(RegisterActivity.this, "Por favor complete todos los campos", Toast.LENGTH_LONG).show();

                }

            }
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void insertUser(String nombre, String apellido, String correo, String password) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, nombre);
        values.put(DatabaseHelper.COLUMN_SURNAME, apellido);
        values.put(DatabaseHelper.COLUMN_EMAIL, correo);
        values.put(DatabaseHelper.COLUMN_PASSWORD, password);

        long newRowId = db.insert(DatabaseHelper.TABLE_USERS, null, values);
        if (newRowId == -1) {
            Toast.makeText(this, "Error al registrar el usuario", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Usuario registrado con ID: " + newRowId, Toast.LENGTH_SHORT).show();
        }
    }

    public void Atras (View view){
        Intent atras = new Intent(this, LoginActivity.class);
        startActivity(atras);
    }
}