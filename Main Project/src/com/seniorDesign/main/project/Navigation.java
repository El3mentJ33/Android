package com.seniorDesign.main.project;

import java.io.IOException;
import java.util.List;
import java.util.Locale; 
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
 
public class Navigation extends Activity {
 
    private String DestinationAddress;
    private TextView destinationText;
    private String SourceAddress;
    private TextView sourceText;
 
    /** Called when the activity is first created. */
      public void abc()
      {
          setContentView(R.layout.navigation);
          sourceText = (EditText) findViewById(R.id.editbox1);
          destinationText = (EditText) findViewById(R.id.editbox2);
          Button cancelRecordButton = (Button) findViewById(R.id.Button);
            cancelRecordButton.setOnClickListener(new OnClickListener() {
                public void onClick(View v) {
                checkAddress();
                }
            });
      }
      void checkAddress()
        {
            Geocoder geoCoder = new Geocoder(Navigation.this, Locale.getDefault());
            DestinationAddress = destinationText.getText().toString();
            SourceAddress = sourceText.getText().toString();
            List<Address> source_addressList = null;
            List<Address> destination_addressList = null;
            try {
            	source_addressList = geoCoder.getFromLocationName(SourceAddress, 1);
                destination_addressList = geoCoder.getFromLocationName(DestinationAddress, 1);
            } catch (IOException e) {
                Toast.makeText(Navigation.this,"Location not found!!! Please change address and try again.",Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }
            if(source_addressList.isEmpty() || destination_addressList.isEmpty() )
            {
                AlertDialog.Builder dlgAlert  = new AlertDialog.Builder(Navigation.this);  
                dlgAlert.setMessage("Location not found!!! Please change address and try again."); 
                dlgAlert.setTitle("GecoderActivity"); 
                dlgAlert.setPositiveButton("OK", null); 
                dlgAlert.setCancelable(true); 
                dlgAlert.create().show(); 
                destinationText.requestFocus();
            }
            else
            {
            Address source_address = source_addressList.get(0);
            Address destination_address = destination_addressList.get(0);
            
            if(source_address.hasLatitude() && source_address.hasLongitude() && destination_address.hasLatitude() && destination_address.hasLongitude()){
            	String uri = "http://maps.google.com/maps?f=d&hl=en&saddr="+source_address.getLatitude()+","+source_address.getLongitude()+"&daddr="+destination_address.getLatitude()+","+destination_address.getLongitude();
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse(uri));
                
                // Below line will open the app in google maps by default. If you don't use the following line of code, then a dialog box will appear from which user can choose which option to select.
                intent.setClassName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity");
                startActivity(intent);
                //Toast.makeText(GeocoderActivity.this,"Address : "+address.getAddressLine(0)+"\nCountry : "+address.getCountryName()+"\nLatitute : "+address.getLatitude()+"\nLongitute : "+address.getLongitude(),Toast.LENGTH_LONG).show();
            }
            }
        }
}