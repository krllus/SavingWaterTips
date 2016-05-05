package gyndroids.com.savingwatertips.utils;

import gyndroids.com.savingwatertips.R;

/**
 * Saving Water Tips
 * Created by joao on 4/19/16.
 */
public class Configs {
    // shared preferences key for the boolean 'first run';
    public static final String PREFERENCE_FIRST_RUN = "first_run";

    // used for shared preferences identifier;
    public static final String APP_IDENTIFICATION = "gyndroids.com.savingwatertips";

    public static final boolean isTesting = false;

    public static final int NUM_PAGES = 4;

    /**
     * Returns the id of the image that are chosen by position
     *
     * @param position requested position
     * @return id of image
     */
    public static int getImageResourceByPosition(int position) {
        switch (position) {
            case 0:
                return R.drawable.water_00;
            case 1:
                return R.drawable.water_01;
            case 2:
                return R.drawable.water_02;
            case 3:
                return R.drawable.water_03;
            case 4:
                return R.drawable.water_04;
            case 5:
                return R.drawable.water_05;
            case 6:
                return R.drawable.water_06;
            case 7:
                return R.drawable.water_07;
            case 8:
                return R.drawable.water_08;
            case 9:
                return R.drawable.water_09;
            case 10:
                return R.drawable.water_10;
            case 12:
                return R.drawable.water_11;
            case 13:
                return R.drawable.water_12;
            case 14:
                return R.drawable.water_13;
            case 15:
                return R.drawable.water_14;
            default:
                return R.drawable.water_cup;
        }

    }

    /**
     * Returns the id of the image that are chosen by position
     *
     * @param position requested position
     * @return id of image
     */
    public static int getImageThumbResourceByPosition(int position) {
        switch (position) {
            case 0:
                return R.drawable.water_thumb_00;
            case 1:
                return R.drawable.water_thumb_01;
            case 2:
                return R.drawable.water_thumb_02;
            case 3:
                return R.drawable.water_thumb_03;
            case 4:
                return R.drawable.water_thumb_04;
            case 5:
                return R.drawable.water_thumb_05;
            case 6:
                return R.drawable.water_thumb_06;
            case 7:
                return R.drawable.water_thumb_07;
            case 8:
                return R.drawable.water_thumb_08;
            case 9:
                return R.drawable.water_thumb_09;
            case 10:
                return R.drawable.water_thumb_10;
            case 11:
                return R.drawable.water_thumb_11;
            case 12:
                return R.drawable.water_thumb_12;
            case 13:
                return R.drawable.water_thumb_13;
            case 14:
                return R.drawable.water_thumb_14;
            default:
                return R.drawable.water_cup;
        }

    }

    /**
     * Load the titles from the string file and
     * return the title according with the mPosition
     *
     * @param position requested position
     * @return id of title
     */
    public static int getTitleResourceIdByPosition(int position) {

        switch (position) {
            case 0:
                return R.string.title_tip_01;
            case 1:
                return R.string.title_tip_02;
            case 2:
                return R.string.title_tip_03;
            case 3:
                return R.string.title_tip_04;
            case 4:
                return R.string.title_tip_05;
            case 5:
                return R.string.title_tip_06;
            case 6:
                return R.string.title_tip_07;
            case 7:
                return R.string.title_tip_08;
            case 8:
                return R.string.title_tip_09;
            case 9:
                return R.string.title_tip_10;
            case 10:
                return R.string.title_tip_11;
            case 12:
                return R.string.title_tip_12;
            case 13:
                return R.string.title_tip_13;
            case 14:
                return R.string.title_tip_14;
            case 15:
                return R.string.title_tip_15;
            default:
                return R.string.title_tip_01;
        }
    }

    /**
     * Load the contents from the string file and
     * return the content according with the mPosition
     */
    public static int getContentIdByPosition(int position) {

        switch (position) {
            case 0:
                return R.string.text_tip_01;
            case 1:
                return R.string.text_tip_02;
            case 2:
                return R.string.text_tip_03;
            case 3:
                return R.string.text_tip_04;
            case 4:
                return R.string.text_tip_05;
            case 5:
                return R.string.text_tip_06;
            case 6:
                return R.string.text_tip_07;
            case 7:
                return R.string.text_tip_08;
            case 8:
                return R.string.text_tip_09;
            case 9:
                return R.string.text_tip_10;
            case 10:
                return R.string.text_tip_11;
            case 12:
                return R.string.text_tip_12;
            case 13:
                return R.string.text_tip_13;
            case 14:
                return R.string.text_tip_14;
            case 15:
                return R.string.text_tip_15;
            default:
                return R.string.text_tip_01;
        }
    }
}
