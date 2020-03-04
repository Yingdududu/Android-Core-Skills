package cs4720.cs.virginia.edu.coreskills;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**

Assignment Notes: For this activity, we are embedding the LocationListener
in this Activity, instead of creating a separate GPS class.  The code is basically
the same, but is a bit easier to reference here.  You can find more info on
how to do a LocationListener in the Service example code we did in class:
https://github.com/marksherriff/SensorExample

*/

public class GPSActivity extends AppCompatActivity implements LocationListener {

    LocationManager locationManager;
    private static final int TAKE_PHOTO_PERMISSION = 1;

    TextView latTextView;
    TextView lonTextView;

    Double currentLat;
    Double currentLon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gps);

        latTextView = (TextView)findViewById(R.id.latTextView);
        lonTextView = (TextView)findViewById(R.id.lonTextView);

    }

    public void startGPS(View view) {

        // Here is the code to handle permissions - you should not need to edit this.
        if ( Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_FINE_LOCATION ) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission( this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] { android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION }, TAKE_PHOTO_PERMISSION);
        }

        // Add code here to register the listener with the Location Manager to receive location updates
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 10, this);

        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        latTextView.setText("Lat: " + location.getLatitude());
        lonTextView.setText("Lon: " + location.getLongitude());
    }


    @Override
    public void onLocationChanged(Location location) {
        // Add code here to do stuff when the location changes
        currentLat = location.getLatitude();
        currentLon = location.getLongitude();
        latTextView.setText("Lat: " + currentLat);
        lonTextView.setText("Lon: " + currentLon);

    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {}

    @Override
    public void onProviderEnabled(String s) {}

    @Override
    public void onProviderDisabled(String s) {}
}
