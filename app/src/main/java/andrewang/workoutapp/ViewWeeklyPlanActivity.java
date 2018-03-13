package andrewang.workoutapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.ListView;

/**
 * Created by andrewang on 2/28/18.
 */

public class ViewWeeklyPlanActivity extends AppCompatActivity {
    private static final String TAG = "ViewWeeklyPlanActivity";
    private Button backToHomeBtn;
    private String days[] = { // store all the days of the week
            "Monday",
            "Tuesday",
            "Wednesday",
            "Thursday",
            "Friday",
            "Saturday",
            "Sunday"
    };
    private String workoutType[] = { // store all the workouts selected for each day
                "",
                "",
                "",
                "",
                "",
                "",
                ""
    };
    private ListView myList;
    private SharedPreferences.Editor editor;
    private SharedPreferences dayInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_weekly_plan);
        backToHomeBtn = (Button) findViewById(R.id.backToHomeBtn);
        // get the shared preference
        dayInfo = getSharedPreferences("SaveDailyInfo", Context.MODE_PRIVATE);
        for (int i = 0; i <= 6; i++) // fill all of the workouts for the week
            workoutType[i] = dayInfo.getString("muscleType" + i, "");

        // Creates a list adapter that allows us to fill in the listview
        ListAdapter theDayListAdapter = new CustomAdapter(this, days, workoutType);
        myList = (ListView) findViewById(R.id.dayListView);
        myList.setAdapter(theDayListAdapter);

        // if the user clicks on one of the list view items
        myList.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView < ? > adapterView, View view, int i, long l) {
                        // i is the position number
                        // go to the respective plan.
                        Intent intent = new Intent(ViewWeeklyPlanActivity.this,
                                DailyPlanActivity.class);
                        intent.putExtra("INDEX_KEY",Long.toString(l)); // pass the current day index
                        Log.d(TAG, "Index: " + Long.toString(l) );
                        if(l == 0) // monday
                        {
                            intent.putExtra("MON_KEY", "Monday");
                        } else if(l == 1) // tuesday
                        {
                            intent.putExtra("TUES_KEY", "Tuesday");
                        } else if (l == 2) // wednesday
                        {
                            intent.putExtra("WED_KEY", "Wednesday");
                        } else if(l == 3) // thurs
                        {
                            intent.putExtra("THURS_KEY", "Thursday");
                        } else if(l == 4) // fri
                        {
                            intent.putExtra("FRI_KEY", "Friday");
                        }
                        else if (l == 5) // sat
                        {
                            intent.putExtra("SAT_KEY", "Saturday");
                        } else { // sun
                            intent.putExtra("SUN_KEY", "Sunday");
                        }
                        startActivity(intent); // startActivity allow you to move
                    }

                }
        );
        // if the user click on the back home button
        backToHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewWeeklyPlanActivity.this,
                        MainActivity.class);
                startActivity(intent); // startActivity allow you to move
            }
        });
    }
}