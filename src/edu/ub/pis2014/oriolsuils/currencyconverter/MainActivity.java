package edu.ub.pis2014.oriolsuils.currencyconverter;

import edu.ub.pis2014.oriolsuils.currencyconverter.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements OnSeekBarChangeListener{
	
	public final static int SECONDARY_ACTIVITY = 1;
	private Conversion c;
	//It is declared all the objects like EditText, TextView...
	private EditText editAmount;
	private TextView numBar, textResult;
	private Button btnCalculate, btnCurrency;
	private SeekBar barCommission;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//It is linked the interface objects to the code
		editAmount = (EditText) findViewById(R.id.editAmount);
		numBar = (TextView) findViewById(R.id.numBar);
		textResult = (TextView) findViewById(R.id.textResult);
		barCommission = (SeekBar) findViewById(R.id.barCommission);
		//It is created a SeekBar listener, for every change of value
		barCommission.setOnSeekBarChangeListener(this);
		btnCurrency = (Button) findViewById(R.id.btnCurrency);
		//It is declared a button listener for the currency button
		btnCurrency.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//If the button is pressed it is shown the secondary screen 
				Intent i = new Intent(MainActivity.this, SecondaryActivity.class);
				startActivityForResult(i, SECONDARY_ACTIVITY);
			}
		});
		
		btnCalculate = (Button) findViewById(R.id.btnCalculate);
		//It is declared a button listener for the calculate button
		btnCalculate.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//If the amount EditText is empty an error message is displayed
				if(editAmount.getText().toString().trim().equalsIgnoreCase("")){
					editAmount.setError(getResources().getString(R.string.errorAmount));
				//If the currency button is not pressed an error message is displayed 
				} else if(c == null) {
					Toast toast = Toast.makeText(getApplicationContext(), getResources().getString(R.string.errorCurrency), Toast.LENGTH_LONG);
					toast.show();
				} else {
					double amount = Double.parseDouble(editAmount.getText().toString());
					double commission = (barCommission.getProgress()/100.);
					//It is called the function that displays the result
					showResult(c.getCurrency(), c.getConversion(), commission, amount);
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
	
	/**
	 * This function displys the result of the operation
	 */
	public void showResult(int currency, double conversion, double commission, double amount){
		double result = 0.;
		String res, com;
		//The case of Dollar
		if(currency == 0){
			result = (amount*conversion);
			com = String.format("%.2f", result*commission);
			result = result - (result*commission);
			res = String.format("%.2f", result);
			textResult.setText(getResources().getString(R.string.totalDollar)+res+getResources().getString(R.string.commissionDollar)+com);
		//The case of Euro
		} else if(currency == 1){
			result = (amount*(c.getConversion()));
			com = String.format("%.2f", result*commission);
			result = result - (result*commission);
			res = String.format("%.2f", result);
			textResult.setText(getResources().getString(R.string.totalEuro)+res+getResources().getString(R.string.euro)+getResources().getString(R.string.commissionEuro)+com+getResources().getString(R.string.euro));
		}
		textResult.setVisibility(1);
	}
	
	@SuppressWarnings("static-access")
	@Override 
	protected void onActivityResult(int requestCode, int resultCode, Intent intent) { 
		if(resultCode == this.RESULT_OK){
			// the requestCode lets us know which Activity it was 
			switch(requestCode) { 
				case SECONDARY_ACTIVITY: 
					// get the Event from the Intent object 
					c = (Conversion)(intent.getExtras().get("CONVERSION")); 
					break; 
			} 
		}
	} 

}
