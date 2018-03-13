package andrewang.workoutapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Created by andrewang on 3/7/18.
 */

public class CustomAdapter extends ArrayAdapter<String> {
    private String[] liftType; // holds an array of lift types
    // constructor for class
    public CustomAdapter(Context context, String[] days, String[] lift) {
        super(context, R.layout.activity_week_listview, days);
        liftType = lift;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // make an inflator so we are able to fill in the custom created view
        LayoutInflater inflateIt = LayoutInflater.from(getContext());
        View customView = inflateIt.inflate(R.layout.activity_week_listview, parent, false);

        // get and set the day
        String singleDay = getItem(position);
        TextView dayTextView = (TextView) customView.findViewById(R.id.currentDayText);
        dayTextView.setText(singleDay);

        // get and set the workout
        String liftKind = liftType[position];
        TextView muscleTextView = (TextView) customView.findViewById(R.id.muscleGroupTextView);
        muscleTextView.setText(liftKind);


        return customView; // return the custom view that was created
    }
}
