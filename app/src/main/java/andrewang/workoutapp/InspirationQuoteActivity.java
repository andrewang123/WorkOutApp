package andrewang.workoutapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by andrewang on 2/28/18.
 */

public class InspirationQuoteActivity extends AppCompatActivity {
    private Button backToHomeBtn;
    private Button generateBtn;
    public static TextView quoteDesc; // make public to allow access to the view
    public static TextView authorDesc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inspire_quote);
        // set all of the widget to their views
        quoteDesc = (TextView) findViewById(R.id.actualQuote);
        authorDesc = (TextView) findViewById(R.id.authorNameTextView);
        backToHomeBtn = (Button) findViewById(R.id.backToHomeBtn);
        generateBtn = (Button) findViewById(R.id.generateBtn);

        // when the user clicks on the back to home button send them back to the home
        backToHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InspirationQuoteActivity.this,
                        MainActivity.class);
                startActivity(intent); // startActivity allow you to move
            }
        });

        // when the user clicks on the generate button display the grabbed information
        generateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fetchData process = new fetchData(); // create object that creates request
                process.execute(); // call the object that grabs the data
            }
        });




    }
}
