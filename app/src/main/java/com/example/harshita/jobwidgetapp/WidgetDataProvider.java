package com.example.harshita.jobwidgetapp;

import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class WidgetDataProvider implements RemoteViewsService.RemoteViewsFactory {

    List<Job> jobList;
    Context mContext = null;

    public WidgetDataProvider(Context context, Intent intent) {
        mContext = context;
    }

    @Override
    public void onCreate() {
        init();
    }


    @Override
    public void onDataSetChanged() {
        init();
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return jobList.size();
    }


    @Override
    public RemoteViews getViewAt(int i) {
        RemoteViews view = new RemoteViews(mContext.getPackageName(),
                android.R.layout.simple_list_item_1);
        Intent fillInIntent = new Intent();
        fillInIntent.putExtra("row", i);
        view.setOnClickFillInIntent(android.R.layout.simple_list_item_1, fillInIntent);
        Job job = jobList.get(i);
        view.setTextViewText(android.R.id.text1, job.positionTitle);
        return view;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }


    void init() {
        //Some url endpoint that you may have
        String myUrl = "https://jobs.search.gov/jobs/search.json?query=nursing+jobs";
        //String to place our result in
        String result;
        //Instantiate new instance of our class
        HttpGetRequest getRequest = new HttpGetRequest();
        //Perform the doInBackground method, passing in our url
        try {

            result = getRequest.execute(myUrl).get();

            Gson gson = new Gson();
            Type type = new TypeToken<List<Job>>() {
            }.getType();
            jobList = gson.fromJson(result, type);

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
