package com.example.jsonparsing;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyAdapter extends FragmentStatePagerAdapter {

    private Context myContext;
    ArrayList<String> albumId;
    ArrayList<String> url;
    ArrayList<String> photId;
    ArrayList<String> photoTitle;
    int totalTabs;


    public MyAdapter(Context context, FragmentManager fm, int totalTabs, ArrayList albumId,
                     ArrayList photId,ArrayList photoTitle, ArrayList url) {
        super(fm);
        myContext = context;
        this.totalTabs = totalTabs;
        this.albumId = albumId;
        this.url = url;
        this.photId = photId;
        this.photoTitle = photoTitle;
    }


    // this is for fragment tabs
    @Override
    public @NotNull Fragment getItem(int position) {
        String tabid = albumId.get(position);
        ArrayList<String> contentUrls = new ArrayList<>();
        ArrayList<String> contentTitles = new ArrayList<>();

        for(int i=0; i<photId.size();i++) {
            if(tabid.equals(photId.get(i))) {
                contentUrls.add(url.get(i)+".png");
                contentTitles.add(photoTitle.get(i));
            }
        }
        return new MyFragment(myContext, contentTitles, contentUrls);
    }
    // this counts total number of tabs
    @Override
    public int getCount() {
        return totalTabs;
    }
}
