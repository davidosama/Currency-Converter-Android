package com.example.davidosama.currencyconverter.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerFrom;
    Spinner spinnerTo;
    EditText editTextValue;
    TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonConvert = (Button)findViewById(R.id.buttonConvert);

        String[] arraySpinner = new String[] {
                "EGP", "USD"
        };

        spinnerFrom = (Spinner) findViewById(R.id.spinnerFrom);
        spinnerTo = (Spinner) findViewById(R.id.spinnerTo);
        editTextValue = (EditText) findViewById(R.id.editTextValue);
        textViewResult = (TextView) findViewById(R.id.textViewResult);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerFrom.setAdapter(adapter);
        spinnerTo.setAdapter(adapter);

        buttonConvert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String textFrom = spinnerFrom.getSelectedItem().toString();
                String textTo = spinnerTo.getSelectedItem().toString();
                String textValue = editTextValue.getText().toString();

                if (textValue.equals("")){
                    Toast.makeText(MainActivity.this, "Value field is empty.", Toast.LENGTH_LONG).show();
                    textViewResult.setText("Results");
                }
                else if (textFrom == textTo){
                    Double value = Double.parseDouble(textValue);
                    Toast.makeText(MainActivity.this, "Both units are the same.", Toast.LENGTH_LONG).show();
                    textViewResult.setText(value.toString());
                }
                else if (textFrom.equals("EGP") && textTo.equals("USD")){
                    Double value = Double.parseDouble(textValue);
                    Double result = value / 17.6 ;
                    String res = String.format("%.2f", result);
                    textViewResult.setText(res);
                }
                else if (textFrom.equals("USD") && textTo.equals("EGP")){
                    Double value = Double.parseDouble(textValue);
                    Double result = value * 17.6 ;
                    String res = String.format("%.2f", result);
                    textViewResult.setText(res);
                }
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("Result", textViewResult.getText().toString());
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        textViewResult.setText(savedInstanceState.getString("Result"));
    }
}
