package com.nonvoid.andromeda.io;

import android.content.Context;

import com.nonvoid.andromeda.data.Hint;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by Matt on 6/1/2016.
 *
 * This will handle saving data to the internal storage as a file
 */
public class InternalStorage {

    public static String HINTLISTKEY = "HINTSLIST";

    public static void writeObject(Context context, String key, Object object) {
        FileOutputStream fos = null;
        try {
            fos = context.openFileOutput(key, Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(object);
            oos.close();
            fos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static Object readObject(Context context, String key) {
        try {
            FileInputStream fis = context.openFileInput(key);
            ObjectInputStream ois = new ObjectInputStream(fis);
            return ois.readObject();
        } catch (IOException | ClassNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

    public static void writeHintsList(Context context, ArrayList<Hint> arrayList){
        InternalStorage.writeObject(context, HINTLISTKEY, arrayList);
    }
    public static ArrayList<Hint> readHintList(Context context){
        return (ArrayList<Hint>) InternalStorage.readObject(context, HINTLISTKEY);
    }
}
