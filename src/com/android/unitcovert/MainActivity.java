package com.android.unitcovert;

import android.app.Activity;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends Activity implements OnItemSelectedListener {
	private EditText inputNum = null;
	private TextView inputUnit = null;
	private TextView result = null;
	private TextView resultUnit =null;
	private Spinner measureSpinner = null;
	private Spinner unitSpinner = null;	
	private double rate = 1.0;
	ArrayAdapter<CharSequence> unitAdapter =null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inputNum = (EditText)findViewById(R.id.input_edittext);
		inputUnit = (TextView)findViewById(R.id.input_unit);
		result = (TextView)findViewById(R.id.result_text);
		resultUnit = (TextView)findViewById(R.id.result_unit);
		
		measureSpinner = (Spinner)findViewById(R.id.measure);
		ArrayAdapter<CharSequence> measureAdapter = ArrayAdapter.createFromResource(this,
		        R.array.measure_array, android.R.layout.simple_spinner_item);
		measureAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		measureSpinner.setAdapter(measureAdapter);	
		
		unitSpinner = (Spinner)findViewById(R.id.unit);
			
		measureSpinner.setOnItemSelectedListener(this);
		unitSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View view, 
		            int pos, long id) {
				// TODO Auto-generated method stub
				CharSequence unit_covert = unitAdapter.getItem(pos);
				if (unit_covert.equals("km-mile")) {
//					Toast.makeText(MainActivity.this, "km-mile", Toast.LENGTH_SHORT).show();
					inputUnit.setText("km");
					resultUnit.setText("mile");
					rate = 0.621;
				} else if (unit_covert.equals("m-yard")) {
					inputUnit.setText("m");
					resultUnit.setText("yard");
					rate = 1.094;
				} else if (unit_covert.equals("m-foot")) {
					inputUnit.setText("m");
					resultUnit.setText("foot");
					rate = 3.281;
				} else if (unit_covert.equals("cm-inch")) {
					inputUnit.setText("cm");
					resultUnit.setText("inch");
					rate = 0.394;
				} else if (unit_covert.equals("kg-pound")) {
					inputUnit.setText("kg");
					resultUnit.setText("pound");				
					rate = 2.205;
				} else if (unit_covert.equals("kg-ounce")) {
					inputUnit.setText("kg");
					resultUnit.setText("ounce");
					rate = 35.274;
				} 
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		inputNum.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before,     
	                int count) {
				// TODO Auto-generated method stub
				String input_str = inputNum.getText().toString();
				if (input_str !=null && !input_str.equals("")) {
					result.setText(""+Double.parseDouble(input_str)*rate);
				}else {
					result.setText("0.0");
				}
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}


	@Override
	public void onItemSelected(AdapterView<?> parent, View view, 
            int pos, long id) {
		// TODO Auto-generated method stub
		switch (pos) {
		case 0:{
				unitAdapter = ArrayAdapter.createFromResource(this,
		        R.array.length_unit_array, android.R.layout.simple_spinner_item);
				unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				unitSpinner.setAdapter(unitAdapter);		
			}
			
			break;
		case 1:{
				unitAdapter = ArrayAdapter.createFromResource(this,
						R.array.weight_unit_array, android.R.layout.simple_spinner_item);
				unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				unitSpinner.setAdapter(unitAdapter);
			}
			
			break;
		default:
			break;
		}
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
	}


	@Override
	public boolean dispatchKeyEvent(KeyEvent event) {
		Log.d("MainActivity", "dispatchKeyEvent->"+event.getKeyCode());
		// TODO Auto-generated method stub
		if (event.getKeyCode()==KeyEvent.KEYCODE_MENU&&!inputNum.getText().toString().equals("")) {
			long now = SystemClock.uptimeMillis(); 
			KeyEvent newKey = new KeyEvent(now, now, event.getAction(), KeyEvent.KEYCODE_NUMPAD_DOT, 0);
			return super.dispatchKeyEvent(newKey);
		}
		return super.dispatchKeyEvent(event);
	}


	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		Log.d("MainActivity", "onKeyDown->"+event.getKeyCode());
		// TODO Auto-generated method stub
		return super.onKeyDown(keyCode, event);
	}
	
}
