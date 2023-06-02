package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.myapplication.database.IDatabase;
import com.example.myapplication.database.RandomDatabase;
import com.example.myapplication.model.Deliverer;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void OnLogin(View view)
    {
        var text = ((TextInputEditText)findViewById(R.id.editNameText)).getText().toString();
        if(text.equals(""))
            return;
        var hasCar = ((CheckBox)findViewById(R.id.hasCarBox)).isChecked();
        var canDocs = ((CheckBox)findViewById(R.id.canDocsBox)).isChecked();
        var deliverer = new Deliverer(text, hasCar,canDocs);
        IDatabase database = new RandomDatabase(getApplicationContext());
        var databaseDel = database.GetDeliverers().stream().
                filter(del -> del.getName().equals(deliverer.getName())).findAny();

        int id = databaseDel.map(Deliverer::getId).orElseGet(() -> database.AddDeliverer(deliverer));

        Intent intent =  new Intent(this, MainActivity.class);
        intent.putExtra("delId", id);
        startActivity(intent);
    }
}
