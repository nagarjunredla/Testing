package com.example.sys1.testing;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Created by rajat on 2/8/2015.
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private String dataSource;
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    JSONArray articles;
    JSONObject json = null;
    public RecyclerAdapter(String dataArgs) throws JSONException {
        dataSource = dataArgs;
        json = new JSONObject(dataSource);
        articles = json.getJSONArray("articles");

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        String title = null;
        String author = null;
        String body = null;
        String date = null;
        try {
            title = articles.getJSONObject(position).getString("title");
            author = articles.getJSONObject(position).getString("author");
            body = articles.getJSONObject(position).getString("body");
            date = dateFormat.format(articles.getJSONObject(position).getDouble("published")).toString();
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        holder.titleView.setText(title);
        holder.authorView.setText("Published by " + author + " on " + date );
        holder.bodyView.setText(body);
    }

    @Override
    public int getItemCount() {
        return articles.length();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        public TextView titleView;
        public TextView authorView;
        public TextView bodyView;
        public ViewHolder(View itemView) {
            super(itemView);
            titleView = (TextView) itemView.findViewById(R.id.title_item);
            authorView = (TextView) itemView.findViewById(R.id.author_item);
            bodyView = (TextView) itemView.findViewById(R.id.body_item);
        }
    }
}