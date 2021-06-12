package com.interview.eclectics.Adapters;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.interview.eclectics.Models.ArticleModel;
import com.interview.eclectics.R;
import com.mukesh.tinydb.TinyDB;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArticleAdapter extends BaseAdapter {
    private Activity activity;
    private LayoutInflater layoutInflater;
    private List<ArticleModel> dashboardItems;
    private TextView name, node, full, fork, watcher, date;


    public ArticleAdapter(Activity activity, List<ArticleModel> dashboardItems) {
        super();
        this.activity = activity;
        this.dashboardItems = dashboardItems;
    }

    @Override
    public int getCount() {
        return dashboardItems.size();
    }

    @Override
    public Object getItem(int location) {
        return dashboardItems.get(location);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (layoutInflater == null)
            layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.list_articles, null);


        name = (TextView) convertView.findViewById(R.id.name);
        node = (TextView) convertView.findViewById(R.id.node);
        full = (TextView) convertView.findViewById(R.id.full);
        fork = (TextView) convertView.findViewById(R.id.fork);
        watcher = (TextView) convertView.findViewById(R.id.watcher);
        date = (TextView) convertView.findViewById(R.id.date);

        ArticleModel m = dashboardItems.get(position);
        String nam = m.getName();
        String nod = m.getNodeId();
        String ful = m.getFullName();
        String fo = m.getForks();
        String wat = m.getWatchers();
        String dat = m.getDateCreated();

        name.setText(nam);
        node.setText(nod);
        full.setText(ful);
        fork.setText(fo);
        watcher.setText(wat);
        date.setText(dat);


        return convertView;
    }


}
