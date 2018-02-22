package com.example.android.miwok;

import android.app.Activity;
import android.graphics.Color;
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

    int mColorResourceId;

    public WordAdapter(Activity context, ArrayList<Word> arrayList, int color){
        super(context, 0, arrayList);
        this.mColorResourceId = color;
    }

    /**
     * Provides a view for an AdapterView (ListView, GridView, etc.)
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
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        // Get the @Word object located at this position in the list
        Word currentWord = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID miwok_text_view
        TextView miwokTextView = (TextView)listItemView.findViewById(R.id.miwok_text_view);
        // Get the miwok translation from the current Word object and set this text on the textview
        miwokTextView.setText(currentWord.getMiwokTranslation());

        // Find the TextView in the list_item.xml layout with the ID default_text_view
        TextView defaultTextView = (TextView)listItemView.findViewById(R.id.default_text_view);
        // Get the default translation from the current Word object and set this text on the textview
        defaultTextView.setText(currentWord.getDefaultTranslation());

        // Find the ImageView in the list_item.xml layout with the ID image
        ImageView imageView = (ImageView)listItemView.findViewById(R.id.image);
        // Set the imageView to the image resource specified in the current Word
        // If there is no valid image, hide it
        if (!currentWord.hasImage())
            imageView.setVisibility(View.GONE);
        else {
            imageView.setImageResource(currentWord.getmImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        }

        // Find the view the color needs to be applied onto
        LinearLayout linearLayout = (LinearLayout)listItemView.findViewById(R.id.translations_layout);
        // Apply the color
        linearLayout.setBackgroundColor(ContextCompat.getColor(getContext(), mColorResourceId));

        return listItemView;
    }
}
