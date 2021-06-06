package com.example.jsonparsing;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.jsonparsing.R;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    TabLayout tabLayout;
    ViewPager viewPager;
    MyAdapter adapter;
    ArrayList<String> almbumTitle = new ArrayList<>();
    ArrayList<String> albumId = new ArrayList<>();
    ArrayList<String> photoTitle = new ArrayList<>();
    ArrayList<String> photoUrl = new ArrayList<>();
    ArrayList<String> photoId = new ArrayList<>();
    ArrayList<String> bitmapArrayList = new ArrayList<>();
    HashMap<Integer, String> hashMap = new HashMap<Integer, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout=(TabLayout)findViewById(R.id.tabLayout);
        viewPager=(ViewPager)findViewById(R.id.viewPager);


        DownloadFilesTask downloadFilesTask = new DownloadFilesTask();
        downloadFilesTask.execute();

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @SuppressLint("StaticFieldLeak")
    class DownloadFilesTask extends AsyncTask<Void, Void, Void> {
        private final ProgressDialog dialog = new ProgressDialog(MainActivity.this);
        String jsonalbumsStr;
        String jsonphotoStr;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            this.dialog.setMessage("Downloading ...");
            this.dialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            ServiceHandler sh = new ServiceHandler();
            jsonalbumsStr = sh.makeServiceCall("https://jsonplaceholder.typicode.com/albums/", ServiceHandler.GET);
            jsonphotoStr = sh.makeServiceCall("https://jsonplaceholder.typicode.com/photos", ServiceHandler.GET);

            if (jsonalbumsStr != null) {
                try {
                    JSONArray almbumarr = new JSONArray(jsonalbumsStr);
                    for (int i = 0; i < almbumarr.length(); i++) {
                        JSONObject c = almbumarr.getJSONObject(i);
                        String title = c.getString("title");
                        String id = c.getString("id");
                        albumId.add(id);
                        almbumTitle.add(title);
                    }
                } catch (Exception e) {
                }
            }

            if (jsonphotoStr !=null ) {
                try {
                    JSONArray photoStr = new JSONArray(jsonphotoStr);
                    for(int i=0; i < photoStr.length(); i++) {
                        JSONObject c = photoStr.getJSONObject(i);
                        String id = c.getString("albumId");
                        String url = c.getString("thumbnailUrl");
                        String title = c.getString("title");
                        photoTitle.add(title);
                        photoId.add(id);
                        photoUrl.add(url);
                    }
                } catch (Exception e) {
                }
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void response) {
            super.onPostExecute(response);
            dialog.dismiss();

            for(int i=0;i<almbumTitle.size();i++) {
                tabLayout.addTab(tabLayout.newTab().setText(almbumTitle.get(i)));
            }

            adapter = new MyAdapter(MainActivity.this,getSupportFragmentManager(),
                    tabLayout.getTabCount(), albumId, photoId, photoTitle, photoUrl);
            viewPager.setAdapter(adapter);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}