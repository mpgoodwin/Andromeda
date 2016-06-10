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
public class HintList extends ArrayList<Hint> {
    Context context;
    public HintList(Context context) {
        this.context = context;
        this.addAll(InternalStorage.readHintList(context));
    }

    public void save(){
        if(this.context != null)
            InternalStorage.writeHintsList(this.context, this);
        else
            Log.d(MainActivity.DEBUGSTR, "HintList save failed");
    }

    public void update(Context context){
        if(context != null)
            addUnique(new HintList(context));
    }
    
    public void addUnique(Hint h){
        for (Hint hint : this){
            if(h.equals(hint))
                return;
        }
        this.add(h);
    }
    public void addUnique(ArrayList<Hint> hintList){
        for(Hint hint : hintList){
            addUnique(hint);
        }
    }
}
