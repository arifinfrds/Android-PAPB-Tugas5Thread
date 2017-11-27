package com.example.arifinfrds.tugas5thread;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.TextView;

import java.lang.ref.WeakReference;

/**
 * Created by arifinfrds on 11/22/17.
 */



//AsyncTask is supposed to do a single/one time task.
//        That is not a good use of AsyncTask. AsyncTasks should do work and be done, not try hanging around.
//        You will find dirty solutions on the web with Thread.sleep() for example. Those are really bad solutions.
//        If you need two different process you should create two different tasks
//        For your issue you can cancel() your background thread or asynsTask, and when needed
//        create a new one with updated parameters maybe

public class CounterTask extends AsyncTask<Void, Integer, Void> {

    private WeakReference<Activity> mWeakActivity;
    private TextView mTvNumber;


    public CounterTask(WeakReference<Activity> mWeakActivity) {
        this.mWeakActivity = mWeakActivity;

        Activity activity = mWeakActivity.get();

        if (activity != null) {
            mTvNumber = activity.findViewById(R.id.uiTextViewNumber);
        }
    }

    // -- run intensive processes here
    // -- notice that the datatype of the first param in the class definition matches the param passed to this
    // method
    // -- and that the datatype of the last param in the class definition matches the return type of this method
    @Override
    protected Void doInBackground(Void... voids) {

        int i = 0;
        while (i <= 10000) {
            try {
                Thread.sleep(50);
                publishProgress(i);
                i++;
            } catch (Exception e) {
            }
        }
        return null;
    }

    // -- gets called just before thread begins
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    // -- called from the publish progress
    // -- notice that the datatype of the second param gets passed to this method
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        String counter = String.valueOf(values[0]);
        mTvNumber.setText(counter);
    }


}
