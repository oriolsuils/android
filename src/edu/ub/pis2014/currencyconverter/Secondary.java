package edu.ub.pis2014.currencyconverter;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Secondary extends Activity {
	
	private Button btnAccept;
	private Spinner spinnerCurrency;
	private EditText editConversion;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_secondary);
		
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
		getMenuInflater().inflate(R.menu.secondary, menu);
		return true;
	}

}
