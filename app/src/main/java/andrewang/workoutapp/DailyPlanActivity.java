package andrewang.workoutapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by andrewang on 2/28/18.
 */

public class DailyPlanActivity extends AppCompatActivity {
    private Button backToWeeklyPlanBtn;
    private Spinner muscleGroupSpinner;
    private ArrayAdapter<CharSequence> adapter;
    private TextView dayTitle;
    private String dayOfWeekIndex;
    private String mDurationTime;
    private String mMuscleType;
    private String mDescription;
    private int liftPosition;
    private EditText duration;
    private EditText description;
    private Button saveBtn;
    private static final String TAG = "DailyPlanActivity";
    private SharedPreferences.Editor editor;
    private SharedPreferences dayInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily_plan);
        // set all of the views
        dayTitle = (TextView) findViewById(R.id.dayTextView);
        duration = (EditText) findViewById(R.id.durationEditText);
        description = (EditText) findViewById(R.id.descriptionEditText);
        backToWeeklyPlanBtn = (Button) findViewById(R.id.backToWeeklyBtn);
        muscleGroupSpinner = (Spinner) findViewById(R.id.muscleSpinner);
        saveBtn = (Button) findViewById(R.id.saveInfoBtn);

        // get the intent that was passed from View Weekly Plan Activity
        Intent intent = getIntent();
        // get the index of what day ex) mon = 0, tues = 1... etc
        dayOfWeekIndex = intent.getStringExtra("INDEX_KEY");
        String dayOfWeek = setDayOfWeek(intent, dayOfWeekIndex);
        dayTitle.setText(dayOfWeek);// set the title of the days

        // Initialize the duration, description and what the type of lift from savepref
        SharedPreferences settings = getSharedPreferences("SaveDailyInfo",
                Context.MODE_PRIVATE);
        mDurationTime = settings.getString("durationTime" + dayOfWeekIndex, ""); // get duration
        mMuscleType = settings.getString("muscleType" + dayOfWeekIndex, "");// get type of lift
        mDescription = settings.getString("description" + dayOfWeekIndex, "");// get description
        String liftPos = "liftposition" + dayOfWeekIndex;
        liftPosition = Integer.parseInt(settings.getString(liftPos, "0")); // get the index of lift

        // set the widgets with their respective information
        duration.setText(mDurationTime);
        description.setText(mDescription);

        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(this,
                R.array.muscle_groups, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        muscleGroupSpinner.setAdapter(adapter);
        muscleGroupSpinner.setSelection(liftPosition); // set the spinner to correct index

        // when the user clicks on the back button
        backToWeeklyPlanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myintent = new Intent(DailyPlanActivity.this,
                        ViewWeeklyPlanActivity.class);
                startActivity(myintent); // startActivity allow you to move
            }
        });


        // when the user clicks on the save button it saves their information
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // save it based on the day
                // save the information of the type of lift in the shared view
                dayInfo = getSharedPreferences("SaveDailyInfo",
                        Context.MODE_PRIVATE);
                // get the current liftPosition
                liftPosition = muscleGroupSpinner.getSelectedItemPosition();
                // get the muscle type from spinner
                String muscleType = muscleGroupSpinner.getSelectedItem().toString();
                //int getMuscleTypePosition = muscleGroupSpinner.getPosition()
                // get the durationtime from edit text
                String durationTime = duration.getText().toString();
                // get the description from edit text
                String descriptionOfLifts = description.getText().toString();
                // Allow to edit the current day info shared preference
                editor = dayInfo.edit();
                editor.putString("durationTime" + dayOfWeekIndex, durationTime); // store duration
                editor.putString("muscleType" + dayOfWeekIndex, muscleType);// store type of lift
                editor.putString("description" + dayOfWeekIndex, descriptionOfLifts);// store description
                editor.putString("liftposition" + dayOfWeekIndex, Integer.toString(liftPosition));// store description
                editor.commit();
                // check all of the items were saved
                Log.d(TAG, "Saved duration time: " + durationTime);
                Log.d(TAG, "Saved muscle type: " + muscleType);
                Log.d(TAG, "Saved description: " + descriptionOfLifts);
                Log.d(TAG, "Saved index of lift: " + liftPosition);
                Log.d(TAG,  "durationTime" + dayOfWeekIndex);
                // Show the user that the information has been saved
                Toast.makeText(DailyPlanActivity.this, "Saved!", Toast.LENGTH_LONG).show();
            }
        });
    }

    // sets the day of the week into a text view
    private String setDayOfWeek(Intent intent, String dayOfWeekIndex ){
        String dayOfWeek = "";
        if(dayOfWeekIndex.equals("0")) // monday
        {
            dayOfWeek = intent.getStringExtra("MON_KEY");
        } else if(dayOfWeekIndex.equals("1")) // tuesday
        {
            dayOfWeek = intent.getStringExtra("TUES_KEY");
        } else if (dayOfWeekIndex.equals("2")) // wednesday
        {
            dayOfWeek = intent.getStringExtra("WED_KEY");
        } else if(dayOfWeekIndex.equals("3")) // thurs
        {
            dayOfWeek = intent.getStringExtra("THURS_KEY");
        } else if(dayOfWeekIndex.equals("4")) // fri
        {
            dayOfWeek = intent.getStringExtra("FRI_KEY");
        }
        else if (dayOfWeekIndex.equals("5")) // sat
        {
            dayOfWeek = intent.getStringExtra("SAT_KEY");
        } else { // sun
            dayOfWeek = intent.getStringExtra("SUN_KEY");
        }
        return dayOfWeek;


    }
}
