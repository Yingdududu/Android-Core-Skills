package cs4720.cs.virginia.edu.coreskills;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**

 Assignment Notes: For this activity, the user should be able to
 save and load the username and computing ID from SQLite.  Several
 helper classes (DatabaseHelper and Section) are included to make
 this a bit easier.

 NOTE: YOU MUST ONLY SHOW THE LAST RECORD FROM THE DATABASE.  i.e.
 the record that was most recently added!

 Reference:
 https://github.com/marksherriff/StorageExample
 https://developer.android.com/training/basics/data-storage/databases.html

 */

public class SQLiteActivity extends AppCompatActivity {

    EditText nameEditText;
    EditText compIDEditText;

    TextView nameTextView;
    TextView compIDTextView;
    DatabaseHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        nameEditText = (EditText) findViewById(R.id.nameEditText);
        compIDEditText = (EditText) findViewById(R.id.compIDEditText);

        nameTextView = (TextView) findViewById(R.id.nameTextView);
        compIDTextView = (TextView) findViewById(R.id.compIDTextView);
        mDbHelper = new DatabaseHelper(this);
    }

    public void saveToDatabase(View view) {
        // Add code here to save to the database

        // Gets the data repository in write mode
        DatabaseHelper mDbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        String compid = compIDEditText.getText().toString();
        String name = nameEditText.getText().toString();
        values.put("compid", compid);
        values.put("name", name);

        // Insert the new row, returning the primary key value of the new row
        long newRowId;
        newRowId = db.insert(
                "person",
                null,
                values);

    }

    public void loadFromDatabase(View view) {

        // Add code here to load from the database

        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                "compid",
                "name"
        };
        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                "compid" + " DESC";

        Cursor cursor = db.query(
                "person",  // The table to query
                projection,                               // The columns to return
                null,                                // The columns for the WHERE clause
                null,                            // The values for the WHERE clause
                null,                                     // don't group the rows
                null,                                     // don't filter by row groups
                sortOrder                                 // The sort order
        );

        String currID = "";
        String currName = "";
        //cursor.moveToFirst();
        while(cursor.moveToNext()) {
            currID = cursor.getString(
                    cursor.getColumnIndexOrThrow("compid")
            );
            currName = cursor.getString(
                    cursor.getColumnIndexOrThrow("name")
            );
            Log.i("DBData", currID);
        }
        cursor.close();
        nameTextView.setText(currName);
        compIDTextView.setText(currID);


    }
}
