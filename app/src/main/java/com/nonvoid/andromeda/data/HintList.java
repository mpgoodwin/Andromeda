package com.nonvoid.andromeda.data;

import android.content.Context;
import android.util.Log;

import com.nonvoid.andromeda.MainActivity;
import com.nonvoid.andromeda.io.InternalStorage;

import java.util.ArrayList;

/**
 * Created by Matt on 6/9/2016.
 * 
 * Example use:
 *      
 *      HintList hintList = new HintList(this);
 *      hintList.save(this);
 *      hintList.
 */
public class HintList {
    Context context;
    ArrayList<Hint> hints;
    public HintList(Context context) {
        this.context = context;

        hints = InternalStorage.readHintList(context);
        if (hints == null){
            hints = new ArrayList<>();
        }
    }

    public ArrayList<Hint> getHints() {
        return hints;
    }

    public void save(){
        if(this.context != null)
            InternalStorage.writeHintsList(this.context, hints);
        else
            Log.d(MainActivity.DEBUGSTR, "HintList save failed");
    }

    public void update(Context context){
        if(context != null)
            addUnique(InternalStorage.readHintList(context));
    }
    
    public void addUnique(Hint h){
        for (Hint hint : hints){
            if(h.equals(hint))
                return;
        }
        hints.add(h);
    }
    public void addUnique(ArrayList<Hint> hintList){
        for(Hint hint : hintList){
            addUnique(hint);
        }
    }
}
