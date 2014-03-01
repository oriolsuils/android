package edu.ub.pis2014.currencyconverter;

import android.R;
import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class MainActivity extends Activity implements OnSeekBarChangeListener{
	
	public final static int SECONDARY_ACTIVITY = 1;
	private final Context context = this;
	private EditText editAmount;
	private TextView numBar, textResult;
	private Button btnCalculate, btnCurrency;
	private SeekBar barCommission;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editAmount = (EditText) findViewById(R.id.editAmount);
		numBar = (TextView) findViewById(R.id.numBar);
		textResult = (TextView) findViewById(R.id.textResult);
		
		addListenerOnSpinner();
		
		barCommission = (SeekBar) findViewById(R.id.barCommission);
		barCommission.setOnSeekBarChangeListener(this);
		
		btnCurrency = (Button) findViewById(R.id.);
		btnCurrency.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainActivity.this, Secondary.class);
				startActivityForResult(i, SECONDARY_ACTIVITY);
			}
		});
		
		buttonCalculate = (Button) findViewById(R.id.btnCalculate);
		buttonCalculate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if(editAmount.getText().toString().length() == 0){
					AlertDialog.Builder noAmount = new AlertDialog.Builder(context);
					noAmount.setTitle("You haven't entered any amount");
					noAmount.setMessage("Please enter an amount");
					noAmount.setPositiveButton("Accept", new DialogInterface.OnClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which) {
							// TODO Auto-generated method stub
							dialog.cancel();
						}
					});
				} else {
				
					double amount = Double.parseDouble(editAmount.getText().toString());
					double commission = (barCommission.getProgress()/100.);
					double result = 0.;
					String res;
					if(pos == 0){
						result = (amount*(1./conversion));
					} else if(pos == 1){
						result = (amount*(conversion));
					}
					result = result - (result*commission);
					res = String.format("%.2f", result);
					textResult.setText("TOTAL: $"+res);
					textResult.setVisibility(1);
				}
			}
		});
	}

	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void addListenerOnSpinner(){
		spinnerCurrency = (Spinner) findViewById(R.id.spinnerCurrency);
		spinnerCurrency.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
				pos = spinnerCurrency.getSelectedItemPosition();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				pos = 0;
			}
		});
	}
	
	@Override
	public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
		numBar.setText(String.valueOf(progress)+"%");
		numBar.setVisibility(1);
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
	
	@Override 
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) { 
		// the requestCode lets us know which Activity it was 
		switch(requestCode) { 
			case SECONDARY_ACTIVITY: 
				// get the Event from the Intent object 
				Event createdEvent = (Event)(intent.getExtras().get("EVENT")); 
				// display the pop-up 
				Toast.makeText(this, "Created event: " + createdEvent.title, Toast.LENGTH_LONG).show(); 
		
				break; 
		} 
	} 

}
