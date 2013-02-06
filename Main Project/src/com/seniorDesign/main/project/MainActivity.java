package com.seniorDesign.main.project;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        
        HomeScreen();
                
    }

    //checks which layout is selected    
    public void layoutSelect(View view) {

            // Is the button now checked?
            boolean checked = ((RadioButton) view).isChecked();
           /* 
            // Check which radio button was clicked
            switch(view.getId()) {
                case R.id.d2x1:
                    if (checked)
                        //a 2x1 layout was selected
                    	//return that or pass on to the next program
                    break;
                case R.id.d2x2:
                    if (checked)
                        // pass parameters into HomeScreen(); :)
                    break;
                case R.id.d3x2:
                    if (checked)
                        // Ninjas rule
                    break;
                case R.id.d3x3:
                    if (checked)
                        // Ninjas rule
                    break;
                case R.id.d4x2:
                    if (checked)
                        // Ninjas rule
                    break;
                case R.id.d4x3:
                    if (checked)
                        // Ninjas rule
                    break;
                case R.id.d5x2:
                    if (checked)
                        // Ninjas rule
                    break;
                case R.id.d5x3:
                    if (checked)
                        // Ninjas rule
                    break;
                	
            } */
            HomeScreen();
    }
    
    public void HomeScreen(){
    	//setContentView(R.layout.home_screen);

        GridView gridview = (GridView) findViewById(R.id.gridView1);
        gridview.setAdapter(new ImageAdapter(this));

        gridview.setOnItemClickListener(new OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Toast.makeText(MainActivity.this, "" + position, Toast.LENGTH_SHORT).show();
            }
        });
    }
    
    
}
