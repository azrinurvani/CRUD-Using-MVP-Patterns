package com.mobile.azrinurvani.crudusingmvppatterns.activity.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mobile.azrinurvani.crudusingmvppatterns.R;
import com.mobile.azrinurvani.crudusingmvppatterns.model.NoteModel;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.RecyclerViewAdapter> {
    private Context context;
    private List<NoteModel>  listNote;
    private ItemClickListener itemClickListener;

    public MainAdapter(Context context, List<NoteModel> listNote, ItemClickListener itemClickListener) {
        this.context = context;
        this.listNote = listNote;
        this.itemClickListener = itemClickListener;

    }

    @NonNull
    @Override
    public RecyclerViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_note,parent,false);

        return new RecyclerViewAdapter(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter holder, int position) {
        NoteModel noteModel = listNote.get(position);
        holder.tv_title.setText(noteModel.getTitle());
        holder.tv_note.setText(noteModel.getNote());
        holder.tv_date.setText(noteModel.getDate());
        holder.card_item.setBackgroundColor(noteModel.getColor());

    }

    @Override
    public int getItemCount() {
        return listNote.size();
    }

    public interface ItemClickListener{
        void onItemClick(View view,int position);
    }

    class RecyclerViewAdapter extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView tv_title,tv_note,tv_date;
        CardView card_item;
        ItemClickListener itemClickListener;
        public RecyclerViewAdapter(View itemView,ItemClickListener itemClickListener) {
            super(itemView);

            tv_title =itemView.findViewById(R.id.title);
            tv_note = itemView.findViewById(R.id.note);
            tv_date = itemView.findViewById(R.id.date);

            card_item = itemView.findViewById(R.id.card_item);
            this.itemClickListener = itemClickListener;
            card_item.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onItemClick(v,getAdapterPosition());
        }
    }
}
