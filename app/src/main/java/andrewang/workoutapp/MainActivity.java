package andrewang.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button viewWeeklyPlan;
    private Button inspirationlQuote;
    private Button muscleGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // create the button instances
        viewWeeklyPlan = (Button) findViewById(R.id.viewPlanBtn);
        inspirationlQuote = (Button) findViewById(R.id.quoteBtn);
        muscleGroup = (Button) findViewById(R.id.muscleGroupBtn);

        // When the user clicks on weekly plan navigate there
        viewWeeklyPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Start the intent
                Intent intent = new Intent(MainActivity.this,
                        ViewWeeklyPlanActivity.class);
                startActivity(intent); // startActivity allow you to move
            }
        });

        // when the user clicks on inspirational quote navigate there
        inspirationlQuote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start the intent
                Intent intent = new Intent(MainActivity.this,
                        InspirationQuoteActivity.class);
                startActivity(intent); // startActivity allow you to move
            }
        });

        // when the user clicks on muscle groups navigate there
        muscleGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // start the intent
                Intent intent = new Intent(MainActivity.this,
                        MuscleGroupActivity.class);
                startActivity(intent); // startActivity allow you to move
            }
        });
    }
}
