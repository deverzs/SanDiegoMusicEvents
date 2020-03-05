package edu.miracosta.cs134.sandiegomusicevents;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import edu.miracosta.cs134.sandiegomusicevents.model.JSONLoader;
import edu.miracosta.cs134.sandiegomusicevents.model.MusicEvent;

public class EventDetailsActivity extends AppCompatActivity {

    //Controller => get intent

    //Wire up TV and IV:
    ImageView eventImageView;
    TextView eventArtistTextView;
    TextView eventDateDayTextView;
    TextView eventTimeTextView;
    TextView eventVenueTextView;
    TextView eventCityTextView;
    TextView eventStateTextView;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        eventImageView = findViewById(R.id.eventImageView);
        eventArtistTextView = findViewById(R.id.eventArtistTextView);
        eventDateDayTextView = findViewById(R.id.eventDateDayTextView);
        eventTimeTextView = findViewById(R.id.eventTimeTextView);
        eventVenueTextView = findViewById(R.id.eventVenueTextView);
        eventCityTextView = findViewById(R.id.eventCityTextView);
        eventStateTextView = findViewById(R.id.eventStateTextView);


        //Extract the intent from MainActivity
        Intent intent = getIntent();
        String artist = intent.getStringExtra("Artist");
        String date = intent.getStringExtra("Date");
        String day = intent.getStringExtra("Day");
        String time = intent.getStringExtra("Time");
        String venue = intent.getStringExtra("Venue");
        String city = intent.getStringExtra("City");
        String state = intent.getStringExtra("State");
        String imageName = intent.getStringExtra("ImageName");

        Log.i("EventDetailsActivity", "Artist name:" + imageName);
        //AssetManager am = getActivity().getAssets();
        try {
            InputStream stream = getAssets().open(imageName);
            Drawable image = Drawable.createFromStream(stream, artist);
            eventImageView.setImageDrawable(image);
        } catch (IOException e)
        {
            Log.e("SD Music Events", "Error loading " + artist, e);
        }

        //Want these above to show up in TextViews
        //so wire up all TV and one IV

        //Set all text in each of the text views
        eventArtistTextView.setText(artist);
        String dayDate = date + "-" + day;
        eventDateDayTextView.setText(dayDate);
        eventTimeTextView.setText(time);
        eventVenueTextView.setText(venue);
        eventCityTextView.setText(city);
        eventStateTextView.setText(state);
        //eventImageView.setImageDrawable(eventImageView.getDrawable());


    }
}
