package com.example.firebasenotesapp;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;

import model.Note;

public class NewNoteActivity extends AppCompatActivity {
    private EditText titleET;
    private EditText descriptionET;
    private NumberPicker numberPicker;
    private CollectionReference noteRef = FirebaseFirestore.getInstance().collection("Notes");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initWidgets();
        numberPicker.setMinValue(1);
        numberPicker.setMaxValue(5);
    }
    private void initWidgets(){
        titleET = findViewById(R.id.titleId);
        descriptionET = findViewById(R.id.descriptionId);
        numberPicker = findViewById(R.id.numberPicker);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.saveId:
                saveNoteToFirebase();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.note_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    private void saveNoteToFirebase(){
        int priority = numberPicker.getValue();
        String title = titleET.getText().toString();
        String description = descriptionET.getText().toString();

        if(title.trim().isEmpty() || description.trim().isEmpty()){
            Toast.makeText(this,"Title and description can not be empty",Toast.LENGTH_SHORT).show();
            return;
        }
        noteRef.add(new Note(title,description,priority));
        Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show();
        finish();



    }
}
