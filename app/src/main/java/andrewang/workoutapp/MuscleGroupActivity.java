package andrewang.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

/**
 * Created by andrewang on 2/28/18.
 */

public class MuscleGroupActivity extends AppCompatActivity {
    private Button backToHomeBtn;
    private Spinner muscleGroupSpinner;
    private ImageView musclePicture;
    private ArrayAdapter<CharSequence> adapter;
    private TextView listOfTypeOfLifts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_muscle_group);

        // set the instance of the widget with actual instance
        backToHomeBtn = (Button) findViewById(R.id.backToHomeBtn);
        muscleGroupSpinner = (Spinner) findViewById(R.id.muscleSpinner);
        musclePicture = (ImageView) findViewById(R.id.musclePicture);
        listOfTypeOfLifts = (TextView) findViewById(R.id.typeOfLifts);
        // Create an ArrayAdapter using the string array and a default spinner layout
        adapter = ArrayAdapter.createFromResource(this,
                R.array.muscle_groups, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        muscleGroupSpinner.setAdapter(adapter);

        // Creates a listener to check if user has selected a different option
        muscleGroupSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                // change the picture
                // change the types of lifts
               if(l == 0) // legs
               {
                musclePicture.setImageResource(R.drawable.legs); // change the picture
                listOfTypeOfLifts.setText(R.string.leg_exercises);
               } else if(l == 1) // abs
               {
                   musclePicture.setImageResource(R.drawable.abs); // change the picture
                   listOfTypeOfLifts.setText(R.string.ab_exercises);
               } else if (l == 2) // chest
               {
                   musclePicture.setImageResource(R.drawable.chest); // change the picture
                   listOfTypeOfLifts.setText(R.string.chest_exercises);
               } else if(l == 3) // triceps
               {
                   musclePicture.setImageResource(R.drawable.tri_bi); // change the picture
                   listOfTypeOfLifts.setText(R.string.tri_exercises);
               } else if(l == 4) // biceps
               {
                   musclePicture.setImageResource(R.drawable.tri_bi);// change the picture
                   listOfTypeOfLifts.setText(R.string.bi_exercises);
               }
               else if (l == 5) // shoulder
               {
                   musclePicture.setImageResource(R.drawable.shoulder);// change the picture
                   listOfTypeOfLifts.setText(R.string.shoulder_exercises);
               } else { // back
                   musclePicture.setImageResource(R.drawable.back);
                   listOfTypeOfLifts.setText(R.string.back_exercises);
               }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // Once the user clicks on the back button
        backToHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // allow the user to get back to the previous screen
                Intent intent = new Intent(MuscleGroupActivity.this,
                        MainActivity.class);
                startActivity(intent); // startActivity allow you to move back home
            }
        });
    }
}
