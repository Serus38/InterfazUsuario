package com.example.logginapplication;

import android.os.Bundle;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.logginapplication.Adapter.UserAdapter;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private DatabaseHelper dbHelper;
    private UserAdapter userAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list);

        dbHelper = new DatabaseHelper(this);
        ArrayList<User> users = dbHelper.getAllUsers();

        userAdapter = new UserAdapter(this, users, dbHelper);
        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(userAdapter);
    }
}