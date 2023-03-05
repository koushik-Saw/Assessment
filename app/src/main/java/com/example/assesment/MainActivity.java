package com.example.assesment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import com.example.assesment.viewmodel.SymbolViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private SymbolViewModel symbolViewModel;
    TextView amount;
    private List<String> symbolsList;
    private Spinner symbol_spinner, base_spinner;
    private Map<String, String> stringStringMap;
    private String spinerName, basecur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        symbol_spinner = (Spinner) findViewById(R.id.Symbols_spinner);
        base_spinner = (Spinner) findViewById(R.id.Base_spinner);
        amount = (TextView) findViewById(R.id.amount);
        symbolViewModel = new ViewModelProvider(this).get(SymbolViewModel.class);
        symbolViewModel.getsymbols().observe(this, new Observer<Map<String, String>>() {
            @Override
            public void onChanged(Map<String, String> symbols) {
                stringStringMap = symbols;
                symbolsList = new ArrayList<String>(stringStringMap.keySet());
                initSpinner();
            }
        });
    }

    private void initSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                symbolsList
        );
        symbol_spinner.setAdapter(adapter);
        base_spinner.setAdapter(adapter);
        symbol_spinner.setSelection(1);
        base_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                basecur = base_spinner.getSelectedItem().toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        symbol_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                spinerName = adapterView.getItemAtPosition(position).toString();
                symbolViewModel.getRate(spinerName, basecur).observe(MainActivity.this, new Observer<Map<String, String>>() {
                    @Override
                    public void onChanged(Map<String, String> stringStringMap) {

                        amount.setText(stringStringMap.get(spinerName));
                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}