package com.example.android.miwok;

/**
 * {@link Word} represents a vocabulary word that the user wants to learn.
 * It contains a word, translation, image, and sound.
 */
public class Word {

    /** Arabic translation for the word */
    private String mArabicTranslation;

    /** German translation for the word */
    private String mGermanTranslation;

    private final static int NO_IMAGE_PROVIDED = -1;
    /** photo for each word */
    private int mImageResourceId = NO_IMAGE_PROVIDED;

    /** sound of the word */
    private  int mSoundId;


    /**
     * Create a new Word object.
     *
     * @param arabicTranslation is the word in the Arabic language
     * @param germanTranslation is the word in the German language
     */
    public Word(String arabicTranslation, String germanTranslation){
        mArabicTranslation = arabicTranslation;
        mGermanTranslation = germanTranslation;
    }

    /**
     * Create a new Word object.
     *
     * @param arabicTranslation is the word in the Arabic language
     * @param germanTranslation is the word in the German language
     * @param imageResourceId is the drawable resource ID for the image asset
     */
    public Word(String arabicTranslation, String germanTranslation, int imageResourceId){
        mArabicTranslation = arabicTranslation;
        mGermanTranslation = germanTranslation;
        mImageResourceId = imageResourceId;
    }

    /**
     * Create a new Word object.
     * I want to try 4 parameters with Numbers only
     * @param arabicTranslation is the word in the Arabic language
     * @param germanTranslation is the word in the German language
     * @param imageResourceId is the drawable resource ID for the image asset
     * @param soundID is the sound resource ID for the audio asset
     */
    Word(String arabicTranslation, String germanTranslation, int imageResourceId, int soundID){
        mArabicTranslation = arabicTranslation;
        mGermanTranslation = germanTranslation;
        mImageResourceId = imageResourceId;
        mSoundId = soundID;
    }

    /**
     * Get the Arabic translation of the word.
     */
    String getArabicTranslation(){
        return mArabicTranslation;
    }

    /**
     * Get the German translation of the word.
     */
    String getDeutschTranslation() {
        return mGermanTranslation;
    }

    int getImageResourceId() {
        return mImageResourceId;
    }

    boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }

    int getSoundId() {
        return mSoundId;
    }

    @Override
    public String toString() {
        return "Word{" +
                "mArabicTranslation='" + mArabicTranslation + '\'' +
                ", mGermanTranslation='" + mGermanTranslation + '\'' +
                ", mImageResourceId=" + mImageResourceId +
                ", mSoundId=" + mSoundId +
                '}';
    }
}
