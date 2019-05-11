package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class ColorsFragment extends Fragment {

    // GLOBAL VARIABLES
    //Create global MediaPlayer object to call it in OnClick() & in Release()
    MediaPlayer mediaPlayer;

    /**Handles audio focus when playing a sound file*/
    private AudioManager audioManager;

    AudioManager.OnAudioFocusChangeListener afChangeListener =
            new AudioManager.OnAudioFocusChangeListener() {
                public void onAudioFocusChange(int focusChange) {
                    if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                            focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK ) {
                        // Pause playback because your Audio Focus was
                        // temporarily stolen, but will be back soon.
                        // i.e. for a phone call
                        mediaPlayer.pause();
                        mediaPlayer.seekTo(0);//start at the beginning of the word
                    } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                        // Stop playback, because you lost the Audio Focus.
                        // i.e. the user started some other playback app
                        // Remember to unregister your controls/buttons here.
                        // And release the kra — Audio Focus!
                        // You’re done.
                        //am.abandonAudioFocus(afChangeListener);
                        releaseMediaPlayer();
                    } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                        // Resume playback, because you hold the Audio Focus
                        // again!
                        // i.e. the phone call ended or the nav directions
                        // are finished
                        // If you implement ducking and lower the volume, be
                        // sure to return it to normal here, as well.
                        mediaPlayer.start();
                    }
                }
            };


    /**
     * This listener gets triggered when the {@link MediaPlayer} has completed playing a file.
     * This is for anonymous class to create the object only once instead of each time.
     */
    private MediaPlayer.OnCompletionListener onCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPlayer) {
            releaseMediaPlayer();
            //Regardless of whether or not we ere granted Audio Focus, abandon it.
            //This also unregisters the AudioFocusChangeListener so we don't get more callbacks
            audioManager.abandonAudioFocus(afChangeListener);
        }
    };

    public ColorsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.word_list, container, false);

        /*Create and setup the {@link AudioManager to request Audio Focus*/
        audioManager = (AudioManager)
                Objects.requireNonNull(getActivity()).getSystemService(Context.AUDIO_SERVICE);

//        LinearLayout rootView = findViewById(R.id.rootView);

        //ArrayList of the Word object that is a word and it's translation
        //final?? because we want to access it from the anonymous OnItemClickListener
        final ArrayList<Word> words = new ArrayList<>();
        words.add(new Word("Abyad",    "Weiß",    R.drawable.color_white, R.raw.song));
        words.add(new Word("Aswad",    "schwarz", R.drawable.color_black, R.raw.song));
        words.add(new Word("Ramady",   "Grau",    R.drawable.color_gray, R.raw.song));
        words.add(new Word("Ahmar",    "Rot",     R.drawable.color_red, R.raw.song));
        words.add(new Word("Azrak",    "Blau",    R.drawable.color_black, R.raw.song));
        words.add(new Word("Asfar",    "Gelb",    R.drawable.color_mustard_yellow, R.raw.song));
        words.add(new Word("Akhdar",   "Grün",    R.drawable.color_green, R.raw.song));
        words.add(new Word("Bonny",    "Braun",   R.drawable.color_brown, R.raw.song));
        words.add(new Word("Bortokaly","Orange",  R.drawable.color_mustard_yellow, R.raw.song));
        words.add(new Word("Banafseg", "Violett", R.drawable.color_dusty_yellow, R.raw.song));

        // Create a {@link WordAdapter} object, whose data source is a list of
        // {@link Word}s objects. The adapter knows how to create list item views for each item
        // in the list.
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_colors);

        // Get a reference to the ListView, and attach the adapter to the listView.
        ListView listView = rootView.findViewById(R.id.list);
        listView.setAdapter(adapter);

        //Play sound for each word
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                //Get the word object from the list item that was clicked
                Word word = words.get(position);

                //Release memory before preparing a new media player
                releaseMediaPlayer();


                // Request audio focus for playback
                int result = audioManager.requestAudioFocus(
                        //Specify the listener
                        afChangeListener,
                        // Use the music stream.
                        AudioManager.STREAM_MUSIC,
                        // Request permanent focus.
                        AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    //We have the Audio Focus now.
                    //Using Factory Method, create a new media player
                    mediaPlayer = MediaPlayer.create(getActivity(), word.getSoundId());

                    mediaPlayer.start();    //no need to call prepare(); create does that for you

                    //Release memory when a media file is finished
                    //But I don't want to create a new OnCompletionListener object every time a user
                    //click a list item layout, because that would take up new resources
                    mediaPlayer.setOnCompletionListener(onCompletionListener);
                }

            }//onItemClick
        });//setOnItemClickListener




        // Inflate the layout for this fragment
        return rootView;
    }




    private void releaseMediaPlayer() {
        // If the media player is not null, then it may be currently playing a sound.
        if (mediaPlayer != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mediaPlayer.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mediaPlayer = null;
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
        //Regardless of whether or not we ere granted Audio Focus, abandon it.
        //This also unregisters the AudioFocusChangeListener so we don't get more callbacks
        audioManager.abandonAudioFocus(afChangeListener);
    }
}
