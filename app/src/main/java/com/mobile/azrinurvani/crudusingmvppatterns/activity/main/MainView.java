package com.mobile.azrinurvani.crudusingmvppatterns.activity.main;

import com.mobile.azrinurvani.crudusingmvppatterns.model.NoteModel;

import java.util.List;

public interface MainView {
//Cari method yang ada return
    void showLoading();
    void hideLoading();
    void onGetResult(List<NoteModel> notes);
    void onErrorLoading(String message);

}
