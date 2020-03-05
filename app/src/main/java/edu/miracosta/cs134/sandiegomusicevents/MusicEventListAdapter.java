package edu.miracosta.cs134.sandiegomusicevents;

import android.app.Activity;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import edu.miracosta.cs134.sandiegomusicevents.model.MusicEvent;

public class MusicEventListAdapter extends ArrayAdapter<MusicEvent> {

    private Context mContext;

    private int mResourceId;
    private List<MusicEvent> mEventsList;

    public MusicEventListAdapter(@NonNull Context context, int resource, @NonNull List<MusicEvent> objects) {
        super(context, resource, objects);
        mContext = context;
        mResourceId = resource;
        mEventsList = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResourceId, null);

        //wire up linear layout and set tag to be the selected music event
        LinearLayout musicEventLinearLayout = view.findViewById(R.id.musicEventListLinearLayout);

        //connect the tag after 52
        ImageView musicEventImageView = view.findViewById(R.id.musicEventListImageView);
        TextView musicEventTextView = view.findViewById(R.id.musicEventListTextView);
        TextView musicEventDateTextView = view.findViewById(R.id.musicEventDateTextView);
        MusicEvent selectedEvent = mEventsList.get(position);

        //connect the tag
        musicEventLinearLayout.setTag(selectedEvent); //now have whole object to sent to it

        musicEventTextView.setText(selectedEvent.getArtist());
        musicEventDateTextView.setText(selectedEvent.getDate());

        AssetManager am = mContext.getAssets();
        try {
            InputStream stream = am.open(selectedEvent.getImageName());
            Drawable image = Drawable.createFromStream(stream, selectedEvent.getArtist());
            musicEventImageView.setImageDrawable(image);
        } catch (IOException e)
        {
            Log.e("SD Music Events", "Error loading " + selectedEvent.getArtist(), e);
        }
        return view;
    }
}
