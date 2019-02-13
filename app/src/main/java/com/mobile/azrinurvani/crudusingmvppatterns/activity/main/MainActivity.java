package com.mobile.azrinurvani.crudusingmvppatterns.activity.main;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mobile.azrinurvani.crudusingmvppatterns.R;
import com.mobile.azrinurvani.crudusingmvppatterns.activity.editor.EditorActivity;
import com.mobile.azrinurvani.crudusingmvppatterns.model.NoteModel;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView{

    private static final int INTENT_ADD = 100;
    private static final int INTENT_EDIT = 200;
    FloatingActionButton fab;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;

    MainPresenter presenter;
    MainAdapter adapter;
    MainAdapter.ItemClickListener itemClickListener;
    List<NoteModel>listNote;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab = findViewById(R.id.add);
        fab.setOnClickListener(view ->
            startActivityForResult(new Intent(this,EditorActivity.class),INTENT_ADD)
        );

        swipeRefreshLayout = findViewById(R.id.swipe_refresh);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //        recyclerView.setAdapter(adapter);

        presenter = new MainPresenter(this);
        presenter.getData();

        swipeRefreshLayout.setOnRefreshListener(
                () -> presenter.getData()
        );

        itemClickListener = ((view, position) ->{
            int id = listNote.get(position).getId();
            String title = listNote.get(position).getTitle();
            String note = listNote.get(position).getNote();
            int color = listNote.get(position).getColor();

            Intent intent = new Intent(this,EditorActivity.class);
            intent.putExtra("id",id);
            intent.putExtra("title",title);
            intent.putExtra("note",note);
            intent.putExtra("color",color);
            startActivityForResult(intent,INTENT_EDIT);

        } );
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode ==INTENT_ADD && requestCode==RESULT_OK){
            presenter.getData();//reload data
        }
        else if(requestCode ==INTENT_EDIT && requestCode==RESULT_OK){
            presenter.getData();//reload data
        }
    }

    @Override
    public void showLoading() {
        swipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void hideLoading() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onGetResult(List<NoteModel> notes) {
        adapter = new MainAdapter(this,notes,itemClickListener);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

        listNote = notes;

    }

    @Override
    public void onErrorLoading(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
