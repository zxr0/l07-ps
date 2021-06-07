package sg.edu.rp.c346.id20000892.simpletodo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView list;
    EditText txt;
    Button add, clr, rm;
    Spinner spn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);
        txt = findViewById(R.id.etext);
        add = findViewById(R.id.button);
        clr = findViewById(R.id.button2);
        rm = findViewById(R.id.button3);
        spn = findViewById(R.id.spinner);

        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                    add.setEnabled(true);
                    rm.setEnabled(false);
                    txt.setHint("Type in a new task here");
                    break;
                    case 1:
                    add.setEnabled(false);
                    rm.setEnabled(true);
                    txt.setHint("Enter index of task to be removed");
                    break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayList<String> todo;
        todo = new ArrayList<String>();

        ArrayAdapter a = new ArrayAdapter<>(
                this, android.R.layout.simple_list_item_1, todo
        );

        list.setAdapter(a);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = txt.getText().toString();
                todo.add(str);
                a.notifyDataSetChanged();
            }
        });

        clr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                todo.clear();
                a.notifyDataSetChanged();
            }
        });

        rm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String str = txt.getText().toString();
                int index = Integer.parseInt(str);

                if(todo.isEmpty()){
                    Toast.makeText(MainActivity.this,"You have no tasks to remove", Toast.LENGTH_SHORT).show();
                }
                else if (index > todo.size() || index < 0){
                    Toast.makeText(MainActivity.this,"Wrong index number", Toast.LENGTH_SHORT).show();
                }
                else {
                    todo.remove(index);
                    a.notifyDataSetChanged();
                }
            }
        });
    }
}