package com.mobile.azrinurvani.crudusingmvppatterns.activity.editor;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;
import com.mobile.azrinurvani.crudusingmvppatterns.R;
import com.thebluealliance.spectrum.SpectrumPalette;

public class EditorActivity extends AppCompatActivity implements EditorView {
    EditText et_title,et_note;
    ProgressDialog progressDialog;

    SpectrumPalette spectrumPalette;

    EditorPresenter presenter;
    int color,id;
    String title,note;

    Menu actionMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please Wait...");


        et_title=findViewById(R.id.title);
        et_note=findViewById(R.id.note);

        spectrumPalette = findViewById(R.id.spectrumPalette);
        spectrumPalette.setOnColorSelectedListener(
                clr -> color = clr
        );

//        EditorPresenter
        presenter = new EditorPresenter(this);

        Intent intent = getIntent();
        id = intent.getIntExtra("id",0);
        title = intent.getStringExtra("title");
        note = intent.getStringExtra("note");
        color = intent.getIntExtra("color",0);

        setDataFromIntentExtra();
    }

    private void setDataFromIntentExtra() {

        if (id != 0 ){
            et_title.setText(title);
            et_note.setText(note);
            spectrumPalette.setSelectedColor(color);

            getSupportActionBar().setTitle("Update Note !");
            readMode();
        }else{
            spectrumPalette.setSelectedColor(getResources().getColor(R.color.white));
            color = getResources().getColor(R.color.white);
            editMode();
        }
    }

    private void readMode() {
        et_title.setFocusableInTouchMode(false);
        et_note.setFocusableInTouchMode(false);
        et_title.setFocusable(false);
        et_note.setFocusable(false);
        spectrumPalette.setEnabled(false);
    }
    private void editMode() {
        et_title.setFocusableInTouchMode(true);
        et_note.setFocusableInTouchMode(true);
        spectrumPalette.setEnabled(true);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_editor,menu);
        actionMenu = menu;

        if (id != 0) {
            actionMenu.findItem(R.id.edit).setVisible(true);
            actionMenu.findItem(R.id.save).setVisible(false);
            actionMenu.findItem(R.id.delete).setVisible(true);
            actionMenu.findItem(R.id.update).setVisible(false);
        }
        else {
            actionMenu.findItem(R.id.edit).setVisible(false);
            actionMenu.findItem(R.id.save).setVisible(true);
            actionMenu.findItem(R.id.delete).setVisible(false);
            actionMenu.findItem(R.id.update).setVisible(false);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String title = et_title.getText().toString().trim();
        String note = et_note.getText().toString().trim();
        int color = this.color;

        switch (item.getItemId()){
            case R.id.save :

                if (title.isEmpty()){
                    et_title.setError("Please enter a title...");
                }else if(note.isEmpty()){
                    et_note.setError("Please enter your note...");
                }else {
                    presenter.saveNote(title, note, color);
                }

                return true;
            case R.id.edit :
                editMode();
                actionMenu.findItem(R.id.edit).setVisible(false);
                actionMenu.findItem(R.id.save).setVisible(false);
                actionMenu.findItem(R.id.delete).setVisible(false);
                actionMenu.findItem(R.id.update).setVisible(true);

                return true;
            case R.id.update :

                if (title.isEmpty()){
                    et_title.setError("Please enter a title...");
                }else if(note.isEmpty()){
                    et_note.setError("Please enter your note...");
                }else {
                    presenter.updateNote(id,title,note,color);
                }

                return true;
            case R.id.delete :

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
                alertDialog.setTitle("Confirm !");
                alertDialog.setMessage("Are you sure ?");
                alertDialog.setCancelable(false);
                alertDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        presenter.deleteNote(id);
                    }
                });

                alertDialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                alertDialog.show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

//method override dari interface IEditorPresenter
    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.hide();
    }

    @Override
    public void onRequestSuccess(String message) {
        Toast.makeText(EditorActivity.this,
                message,
                Toast.LENGTH_SHORT).show();
        finish();//back to MainActivity
        setResult(RESULT_OK);
    }

    @Override
    public void onRequestError(String message) {
        Toast.makeText(EditorActivity.this,
                message,
                Toast.LENGTH_SHORT).show();
    }
}
