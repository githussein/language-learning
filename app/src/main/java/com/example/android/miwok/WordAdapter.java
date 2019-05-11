package com.example.android.miwok;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    /**
     * Prepares the data
     * This is our own custom constructor (it doesn't mirror a superclass constructor).
     * The context is used to inflate the layout file, and the list is the data we want
     * to populate into the lists.
     *
     * @param context The current context. Used to inflate the layout file.
     * @param words A List of Word objects to display in a list
     */
    WordAdapter(Activity context, ArrayList<Word> words, int colorResourceId) {
        // Here, we initialize the ArrayAdapter's internal storage for the context and the list.
        // the second argument is used when the ArrayAdapter is populating a single TextView.
        // Because this is a custom adapter for two TextViews and an ImageView, the adapter is not
        // going to use this second argument, so it can be any value. Here, we used 0.
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, spinner, etc.)
     *
     * @param position The position in the list of data that should be displayed in the
     *                 list item view.
     * @param convertView The recycled view to populate.
     * @param parent The parent ViewGroup that is used for inflation.
     * @return The View for the position in the AdapterView.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Get the {@link Word} object located at this position in the list
        Word currentWordObject = getItem(position);

        //Create a new recycleView or populate a NEWLY CREATED VIEW

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        // Find the TextView in the list_item.xml layout with the ID arabic_text_view
        TextView arabicTextView = listItemView.findViewById(R.id.arabic_text_view);
        // Get the Arabic Translation from the current Word object and
        // set this text on the Arabic TextView
        assert currentWordObject != null;
        arabicTextView.setText(currentWordObject.getArabicTranslation());

        // Find the TextView in the list_item.xml layout with the ID german_text_view
        TextView germanTextView = listItemView.findViewById(R.id.german_text_view);
        // Get the German Translation from the current Word object and
        // set this text on the German TextView
        germanTextView.setText(currentWordObject.getDeutschTranslation());

        // Find the ImageView in the list_item.xml layout with the ID image
        ImageView imageView = listItemView.findViewById(R.id.image);
        // Get the image resource ID from the current Word object and
        // set this text on the image ImageView
        if(currentWordObject.hasImage()) {
            imageView.setImageResource(currentWordObject.getImageResourceId());
        } else {
            imageView.setVisibility(View.GONE);
        }

        //Find the ListView that contains the 2 texts
        final LinearLayout textContainer = listItemView.findViewById(R.id.text_container);
        //Find the color that the resource ID maps
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        //Change it's background color
        textContainer.setBackgroundColor(color);


        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
