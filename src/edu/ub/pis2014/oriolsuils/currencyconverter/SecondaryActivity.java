package edu.ub.pis2014.oriolsuils.currencyconverter;

import edu.ub.pis2014.oriolsuils.currencyconverter.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class SecondaryActivity extends Activity {
	
	//It is declared all the objects like Button, Spinner...
	private Button btnAccept;
	private Spinner spinnerCurrency;
	private int pos=0;
	private EditText editConversion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secondary);
		
		spinnerCurrency = (Spinner) findViewById(R.id.spinnerCurrency);
		addListenerOnSpinner();
		
		editConversion = (EditText) findViewById(R.id.editConversion);
		
		btnAccept = (Button) findViewById(R.id.btnAccept);
		btnAccept.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//If the conversion EditText is empty an error message is displayed
				if(editConversion.getText().toString().trim().equalsIgnoreCase("")){
					editConversion.setError(String.valueOf(getResources().getString(R.string.errorConversion)));
				} else {
					Conversion conversion = new Conversion();
					conversion.setConversion(Double.parseDouble(editConversion.getText().toString()));
					conversion.setCurrency(pos);
					Intent i = new Intent();
					i.putExtra("CONVERSION", conversion);
					setResult(RESULT_OK, i);
					finish();
				}
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.secondary, menu);
		return true;
	}
	
	/**
	 * This function creates Spinner listener for every change of value (Dollar, Euro)
	 */
	public void addListenerOnSpinner(){
		spinnerCurrency = (Spinner) findViewById(R.id.spinnerCurrency);
		spinnerCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				pos = spinnerCurrency.getSelectedItemPosition();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				//By default it selected the Dollar
				pos = 0;
			}
		});
	}

}
