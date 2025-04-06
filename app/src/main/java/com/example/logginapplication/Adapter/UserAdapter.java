package com.example.logginapplication.Adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.logginapplication.DatabaseHelper;
import com.example.logginapplication.R;
import com.example.logginapplication.User;

import java.util.ArrayList;

public class UserAdapter extends ArrayAdapter<User> {

    private DatabaseHelper dbHelper;

    public UserAdapter(Context context, ArrayList<User> users, DatabaseHelper dbHelper) {
        super(context, 0, users);
        this.dbHelper = dbHelper;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        User user = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.activity_list, parent, false);
        }

        TextView userInfoTextView = convertView.findViewById(R.id.userInfoTextView);
        Button updateButton = convertView.findViewById(R.id.updateButton);
        Button deleteButton = convertView.findViewById(R.id.deleteButton);

        userInfoTextView.setText(user.getName() + " " + user.getSurname() + " - " + user.getEmail());

        updateButton.setOnClickListener(v -> {
            // Lógica para actualizar el usuario
        });

        deleteButton.setOnClickListener(v -> {
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.delete(DatabaseHelper.TABLE_USERS, DatabaseHelper.COLUMN_ID + " = ?", new String[]{String.valueOf(user.getId())});
            remove(user);
            notifyDataSetChanged();
        });

        return convertView;
    }
}