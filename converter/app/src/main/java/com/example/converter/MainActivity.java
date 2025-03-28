package com.example.converter;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    private EditText num1;
    private TextView ans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        num1 = findViewById(R.id.editnum1);
        Button btn = findViewById(R.id.btn);
        ans = findViewById(R.id.ans);

        // for program logic
        final String[] item = new String[2];

        // options for spinner to select
        Spinner spinner = findViewById(R.id.dropdown);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // gets the item at the selected position
                item[0] = parent.getItemAtPosition(position).toString();
            }

            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        // same thing for second spinner...
        Spinner spinner2 = findViewById(R.id.dropdown2);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // gets the item at the selected position
                item[1] = parent.getItemAtPosition(position).toString();
            }

            @Override public void onNothingSelected(AdapterView<?> parent) {}
        });

        // The drop down list
        String[] arrayList = {"Celsius", "Fahrenheit", "Kelvin"};

        // connecting it to our spinner
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner.setAdapter(adapter);

        // connecting it to our spinner again
        ArrayAdapter<String> adapter2 =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.select_dialog_singlechoice);
        spinner2.setAdapter(adapter2);

        // add button on click functionality after the spinner on item select
        btn.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {
                double temp1 = Integer.parseInt(num1.getText().toString());
                double converted = 0;

                // check what the user has selected in the array and compare and perform calculation based on it.
                if (Objects.equals(item[0], "Celsius") && Objects.equals(item[1], "Fahrenheit")) converted = (temp1 * 1.8) + 32; // F = (C * 1.8) + 32
                if (Objects.equals(item[0], "Fahrenheit") && Objects.equals(item[1], "Celsius")) converted = (temp1 - 32) / 1.8; // C = (F - 32) / 1.8
                if (Objects.equals(item[0], "Celsius") && Objects.equals(item[1], "Kelvin")) converted = temp1 + 273.15; // K = C + 273.15
                if (Objects.equals(item[0], "Kelvin") && Objects.equals(item[1], "Celsius")) converted = temp1 - 273.15; // C = K - 273.15

                // plug answer into textView
                ans.setText(
                        "Converted!: " + converted +
                                "\nFrom " + item[0] + " to " + item[1]);
            }
        });
    }
}
