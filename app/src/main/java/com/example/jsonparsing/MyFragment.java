package com.example.jsonparsing;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.example.jsonparsing.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyFragment extends Fragment {

    ArrayList<String> titles;
    ArrayList<String> urls = new ArrayList<>();
    Context ctx;
    View v;
    public MyFragment(Context ctx, ArrayList titles, ArrayList urls) {
        this.titles = titles;
        this.urls = urls;
        this.ctx = ctx;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.myfragment, container, false);
        GridLayout gridLayout = v.findViewById(R.id.layout);
        createLayout(gridLayout);
        return v;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    void createLayout(GridLayout gridLayout) {
        int total = urls.size();
        int column = 3;
        int row = total / column;
        gridLayout.setColumnCount(column);
        gridLayout.setRowCount(row + 1);
        for (int i = 0, c = 0, r = 0; i < total; i++, c++) {
            if (c == column) {
                c = 0;
                r++;
            }
            ImageView oImageView = new ImageView(ctx);
            TextView  txtView = new TextView(ctx);
            gridLayout.setAlignmentMode(GridLayout.ALIGN_BOUNDS);

            Picasso.with(ctx)
                    .load(urls.get(i))
                    .into(oImageView);
            txtView.setText(titles.get(i));

            GridLayout.Spec rowSpan = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
            GridLayout.Spec colspan = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
            if (r == 0 && c == 0) {
                Log.e("", "spec");
                colspan = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
                rowSpan = GridLayout.spec(GridLayout.UNDEFINED, 1, 1f);
            }
            GridLayout.LayoutParams gridParam = new GridLayout.LayoutParams(
                    rowSpan, colspan);
            gridParam.height = GridLayout.LayoutParams.WRAP_CONTENT;
            gridParam.width = GridLayout.LayoutParams.WRAP_CONTENT;
            gridParam.rightMargin = 10;
            gridParam.topMargin = 10;
            gridParam.setGravity(Gravity.CENTER);
            gridLayout.addView(oImageView, gridParam);
            //gridLayout.addView(txtView,gridParam);
        }
    }
}