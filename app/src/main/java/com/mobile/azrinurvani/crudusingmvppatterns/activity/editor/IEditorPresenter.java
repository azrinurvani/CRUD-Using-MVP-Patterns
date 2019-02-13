package com.mobile.azrinurvani.crudusingmvppatterns.activity.editor;

public interface IEditorPresenter {

    void saveNote(String title, String note,int id);
    void updateNote(int id,String title,String note,int color);
    void deleteNote(int id);
}
