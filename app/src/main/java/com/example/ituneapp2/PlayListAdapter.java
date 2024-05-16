package com.example.ituneapp2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PlayListAdapter extends RecyclerView.Adapter<PlayListAdapter.MyViewHolder> {

    private List<ListItem> listItemList;
    Context context;
    private PlaylistController playlistController;

    public void setTaskList(List<ListItem> listItemList) {
        this.listItemList = listItemList;
    }

    public PlayListAdapter(List<ListItem> listItems, Context context) {
        this.listItemList = listItems;
        this.context = context;
        playlistController = new PlaylistController(this.context);
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemTask = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_list_item, viewGroup, false);
        return new MyViewHolder(itemTask);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        ListItem listItem = listItemList.get(i);

        String title = listItem.getUrl();
        myViewHolder.title.setText(title);

    }


    @Override
    public int getItemCount() {
        return listItemList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;

        MyViewHolder(View itemView) {
            super(itemView);
            this.title = itemView.findViewById(R.id.tvTitle);
        }
    }
}