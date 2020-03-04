package cs4720.cs.virginia.edu.coreskills;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Map;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

/**

Assignment Notes: This activity should pull data from Firebase based upon
the course requested in the EditText.  However, note that CS 4720 is
the only course in this database right now.  If you are going to use
.getReference() or .getChild(), you'll need to get "CS/4720" to get the
proper data entry.  Also, consider using the .addListenerForSingleValueEvent()
method for pulling data.

Reference this material for guidance:
https://firebase.google.com/docs/database/android/read-and-write

*/

public class FirebaseActivity extends AppCompatActivity {

    EditText courseEditText;
    TextView courseNameTextView;
    TextView instructorTextView;
    TextView locationTextView;

    String FIREBASE_URL = "https://coreskillsapp.firebaseio.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        courseEditText = (EditText) findViewById(R.id.fbcourseEditText);
        courseNameTextView = (TextView) findViewById(R.id.fbcourseNameTextView);
        instructorTextView = (TextView) findViewById(R.id.fbinstructorTextView);
        locationTextView = (TextView) findViewById(R.id.fblocationTextView);

    }

    public void downloadFirebaseData(View view) {
        // Add code here to pull the data from Firebase
        final FirebaseDatabase db = getInstance(FIREBASE_URL);
        DatabaseReference ref = db.getReference("CS/4720");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snap) {
                Section section = snap.getValue(Section.class);
                courseNameTextView.setText(section.getCourseName());
                instructorTextView.setText(section.getInstructor());
                locationTextView.setText(section.getLocation());
            }

            @Override
            public void onCancelled(DatabaseError e) {
                Log.d("Firebase", "error code is " + e.getCode());
            }
        });


    }
}
