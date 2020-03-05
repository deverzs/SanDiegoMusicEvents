package edu.miracosta.cs134.sandiegomusicevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import edu.miracosta.cs134.sandiegomusicevents.model.JSONLoader;
import edu.miracosta.cs134.sandiegomusicevents.model.MusicEvent;

public class MainActivity extends AppCompatActivity {

    private List<MusicEvent> eventsList;
    private MusicEventListAdapter musicEventListAdapter;
    private ListView musicEventsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //fromJason
        try {
            eventsList = JSONLoader.loadJSONFromAsset(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        musicEventsListView = findViewById(R.id.musicEventListView);
        musicEventListAdapter = new MusicEventListAdapter(this, R.layout.music_event_list_item, eventsList);
        musicEventsListView.setAdapter(musicEventListAdapter);


    }
    //focusable and clickable here from LinearLayout custom
    public void openEventDetails(View v)
    {
        //extract tag, can put anything in tag (Object) so downcast
        MusicEvent clickedEvent = (MusicEvent) v.getTag();

        //intent
        Intent intent = new Intent(this, EventDetailsActivity.class);


        //Fill the intent with the details about the clicked event
        intent.putExtra("Artist",clickedEvent.getArtist() ); //key value pair
        intent.putExtra("Date", clickedEvent.getDate());
        intent.putExtra("Day", clickedEvent.getDay());
        intent.putExtra("Time",clickedEvent.getTime() ); //key value pair
        intent.putExtra("Venue", clickedEvent.getVenue());
        intent.putExtra("City", clickedEvent.getCity());
        intent.putExtra("State", clickedEvent.getState());
        intent.putExtra("ImageName", clickedEvent.getImageName());

        //go to (startActivity) event details
        startActivity(intent);
        //so far no data for intent
    }
}
