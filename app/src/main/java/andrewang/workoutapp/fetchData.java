package andrewang.workoutapp;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by andrewang on 3/12/18.
 */

public class fetchData extends AsyncTask<Void, Void, Void> {
    private static final String TAG = "fetchData";
    String data = ""; // the entire json file
    String quote = ""; // store the quote
    String author = ""; // store the author
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            // Hold the URL of the JSON information
            URL url = new URL("https://talaikis.com/api/quotes/random/");
            //Create the http connection from the specified URL above
            HttpsURLConnection httpURLconnection = (HttpsURLConnection) url.openConnection();
            // get the information / read info
            InputStream inputStream = httpURLconnection.getInputStream();
            // read data from input stream
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            // Read the information
            String line = "";
            while (line != null) {
                line = bufferedReader.readLine(); // read line by line
                data += line; // add the entire json
            }
            Log.d(TAG, "Data: " + data); // log the json file
            JSONObject json = new JSONObject(data); // create a json object with the json string
            quote = json.getString("quote"); // get the quote
            Log.d(TAG, "Quote: " + quote);
            author = json.getString("author"); // get the author
            Log.d(TAG, "Author: " + author);

        } catch (MalformedURLException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return null;
    }
    // show the user the data.
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        // put the information into the textview
        InspirationQuoteActivity.quoteDesc.setText(this.quote);
        InspirationQuoteActivity.authorDesc.setText(this.author);
    }
}
