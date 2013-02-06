package com.seniorDesign.main.project;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.RadioButton;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);
        
        HomeScreen();
                
        //now that set up is done, wait for button clicks
        //setButtonClickListener();
    }
    
    public void onDestroy(Bundle savedInstanceState){
    	super.onDestroy();
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
                switch(position)
                {
                case 0:	//phone
                	makeCall(v);
                	
                case 1:	//calendar
                	openCalendar(v);
                	
                case 2:	//calculator
                	openCalc(v);
                	
                case 3:	//gallery
                	openGallery(v);
                case 4: //browser
                	openBrowser(v);
                	
                case 5:	//email
                	openEmail(v);
                	
                case 6:	//maps
                	
                case 7:	//sms
                	openText(v);
                	
                case 8:	//camera
                	openCamera(v);
                	
                case 9:	//contacts
                	openContacts(v);
                }      	
            	
            	closeApp(v);
            }
        });
    }
    
	//call method
	public void makeCall(View view) {
    	Intent callIntent = new Intent(Intent.ACTION_DIAL);
    	startActivity(callIntent);
    	}
	//text method
    public void openText(View view) {
    	Intent textIntent = new Intent(Intent.ACTION_VIEW);
    	textIntent.setData(Uri.parse("sms:"));
    	textIntent.setClassName("com.android.mms", "com.android.mms.ui.ConversationList");
    	startActivity(textIntent);
    	
    }
    //camera method
    public void openCamera(View view) {
    Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    startActivity(cameraIntent);
    }
    //video method
    public void openVideo(View view) {
        Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
        startActivity(videoIntent);
        }
    //contacts method
    public void openContacts(View view) {
    Intent contactsIntent = new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
    startActivity(contactsIntent);
    }
   
    //facebook method
    public void openFacebook(View view) {
    Intent facebookIntent = new Intent();
    PackageManager manager = getPackageManager();
    facebookIntent = manager.getLaunchIntentForPackage("com.facebook.katana");
    facebookIntent.addCategory(Intent.CATEGORY_LAUNCHER);
    startActivity(facebookIntent);
    }
    //calendar method
    public void openCalendar(View view) {
    	ComponentName componentName = new ComponentName("com.android.calendar",
    			"com.android.calendar.LaunchActivity");
    			if (componentName != null) {
    			Intent intent = new Intent(android.content.Intent.ACTION_VIEW);
    			// com.android.providers.calendar.CalendarProvider
    			intent.setFlags(android.content.Intent.FLAG_ACTIVITY_NEW_TASK);

    			intent.setComponent(componentName);
    			startActivity(intent);
    			}
    	
    }
    
    //alarmclock method
    public void openAlarmClock(View view) {
    	Intent alarmIntent = new Intent("android.intent.action.SET_ALARM");
    	startActivity(alarmIntent);
    }
    
    //gmail method
	public void openEmail(View view) {
		Intent gmailIntent = new Intent();
	    PackageManager manager = getPackageManager();
	    gmailIntent = manager.getLaunchIntentForPackage("com.google.android.gm");
	    gmailIntent.addCategory(Intent.CATEGORY_LAUNCHER);
	    startActivity(gmailIntent);
	
	}
    
    //browser method
	public void openBrowser(View view) {
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
		startActivity(browserIntent);
	
	}
	
	//calculator method
	public void openCalc(View view){
		Intent calcIntent = new Intent();
		calcIntent.setAction(Intent.ACTION_MAIN);
		calcIntent.addCategory(Intent.CATEGORY_LAUNCHER);
		calcIntent.setComponent(new ComponentName("com.android.calculator2", "com.android.calculator2.Calculator"));
		MainActivity.this.startActivity(calcIntent);
	}
	
	public void openGallery(View view){
		Intent galleryIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(
                "content://media/internal/images/media"));
		startActivity(galleryIntent);
	}
	
	 //close app method
    public void closeApp(View view) {

    	Intent intent = new Intent(Intent.ACTION_MAIN);
    	intent.addCategory(Intent.CATEGORY_HOME);
    	intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    	startActivity(intent);

    }
    
}
