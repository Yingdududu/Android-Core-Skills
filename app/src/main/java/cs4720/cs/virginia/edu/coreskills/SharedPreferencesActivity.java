package cs4720.cs.virginia.edu.coreskills;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**

 Assignment Notes: For this activity, the user should be able to
 save and load the username and computing ID from SharedPreferences,
 the key/value store built into Android.

 Reference:
 https://github.com/marksherriff/StorageExample

 */

public class SharedPreferencesActivity extends AppCompatActivity {

    public static final String PREFS_NAME = "CoreSkillsPrefsFile";

    EditText spnameEditText;
    EditText spcompIDEditText;

    TextView spnameTextView;
    TextView spcompIDTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preferences);

        spnameEditText = (EditText) findViewById(R.id.spnameEditText);
        spcompIDEditText = (EditText) findViewById(R.id.spcompIDEditText);

        spnameTextView = (TextView) findViewById(R.id.spnameTextView);
        spcompIDTextView = (TextView) findViewById(R.id.spcompIDTextView);
    }

    public void saveToSharedPreferences(View view) {

        // Add your code here to save

        // Restore preferences
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("name", spnameEditText.getText().toString());
        editor.putString("compID", spcompIDEditText.getText().toString());

        // Commit the edits!
        editor.commit();

    }

    public void loadFromSharedPreferences(View view) {

        // Add your code here to load

        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        String nameValue = settings.getString("name", "none");
        String compIDValue = settings.getString("compID", "none");
        spcompIDTextView.setText("Computing ID: "+ compIDValue);
        spnameTextView.setText("Name: "+ nameValue);


    }
}
