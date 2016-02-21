package app.sunshine.android.example.com.androidjokedisplaylib;

import android.content.Context;
import java.util.Random;

/**
 * Created by Asus1 on 12/22/2015.
 */
public class Utils {

    public static String returnRandomString(Context context){
        String[] stringArray = context.getResources().getStringArray(R.array.gif_source_array);
        Random rand = new Random();
        int randInt = rand.nextInt(10);
        return stringArray[randInt];
    }
}
