package com.lambdaschool.notetaker;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.ArrayList;

public class NoteListAdapter extends RecyclerView.Adapter<NoteListAdapter.ViewHolder> {
    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView noteTitle, noteContent;
        ViewGroup parentView;
        int lastPosition;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            lastPosition = -1;
            noteTitle = itemView.findViewById(R.id.note_element_title);
            noteContent = itemView.findViewById(R.id.note_element_content);
            parentView = itemView.findViewById(R.id.note_element_parent_layout);
        }
    }

    private ArrayList<Note> dataList;
    private Context         context;
    private Activity        activity;

    NoteListAdapter(ArrayList<Note> dataList, Activity activity) {
        this.dataList = dataList;
        this.activity = activity;
    }

    public void replaceList(ArrayList<Note> newData) {
        this.dataList.clear();
        this.dataList.addAll(newData);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        context = viewGroup.getContext();
        View view = LayoutInflater.from(
                viewGroup.getContext())
                                  .inflate(
                                          R.layout.note_element_layout,
                                          viewGroup,
                                          false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        final Note data = dataList.get(i);

        viewHolder.noteTitle.setText(data.getTitle());
        // I removed the content length limit to better take advantage of the staggered grid
        /*String content;
        if (data.getContent().length() > 30) {
            content = data.getContent().substring(0, 30) + "...";
        } else {
            content = data.getContent();
        }*/
        /*if(i == 1) {
            viewHolder.parentView.setBackgroundColor(context.getResources().getColor(R.color.colorPrimary));
        } else {
            viewHolder.parentView.setBackgroundColor(context.getResources().getColor(R.color.colorAccent));
        }*/

        viewHolder.noteContent.setText(data.getContent());
        viewHolder.parentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, EditActivity.class);
                intent.putExtra(EditActivity.EDIT_NOTE_KEY, data);

                Bundle options = ActivityOptions.makeSceneTransitionAnimation((Activity)context).toBundle();
//                context.startActivity(intent, options);

                activity.startActivityForResult(intent, MainActivity.EDIT_REQUEST_CODE, options);
            }
        });
//        setEnterAnimation(viewHolder.parentView, viewHolder, i);
    }

    private void setEnterAnimation(View viewToAnimate, ViewHolder holder, int position) {
//        if(position > holder.lastPosition) {
            Animation animation = AnimationUtils.loadAnimation(
                    viewToAnimate.getContext(), android.R.anim.slide_in_left);
            viewToAnimate.startAnimation(animation);
            holder.lastPosition = position;
//        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }
}
