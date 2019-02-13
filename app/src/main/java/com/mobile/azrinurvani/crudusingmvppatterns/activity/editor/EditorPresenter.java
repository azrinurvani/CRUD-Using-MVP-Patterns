package com.mobile.azrinurvani.crudusingmvppatterns.activity.editor;

import com.mobile.azrinurvani.crudusingmvppatterns.model.NoteModel;
import com.mobile.azrinurvani.crudusingmvppatterns.network.ApiClient;
import com.mobile.azrinurvani.crudusingmvppatterns.network.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditorPresenter implements IEditorPresenter {

    private EditorView view;

    public EditorPresenter(EditorView view) {
        this.view = view;
    }

    @Override
    public void saveNote(final String title, final String note,final int color) {

            view.showProgress();

            ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
            Call<NoteModel> call = apiInterface.saveNote(title,note,color);
            call.enqueue(new Callback<NoteModel>() {
                @Override
                public void onResponse(Call<NoteModel> call, Response<NoteModel> response) {
                    view.hideProgress();

                    if(response.isSuccessful()&&response.body()!=null){

                        Boolean success = response.body().getSuccess();
                        if (success){
                            view.onRequestSuccess(response.body().getMessage());
                        }else{
                            view.onRequestError(response.body().getMessage());
                        }
                    }
                }

                @Override
                public void onFailure(Call<NoteModel> call, Throwable t) {
                    view.hideProgress();
                    view.onRequestError(t.getLocalizedMessage());
                }
            });

    }

    @Override
    public void updateNote(int id, String title, String note, int color) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<NoteModel>call = apiInterface.updateNote(id,title,note,color);
        call.enqueue(new Callback<NoteModel>() {
            @Override
            public void onResponse(Call<NoteModel> call, Response<NoteModel> response) {
                view.hideProgress();
                if (response.isSuccessful()){
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    }
                    else{
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }
            @Override
            public void onFailure(Call<NoteModel> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });
    }

    @Override
    public void deleteNote(int id) {
        view.showProgress();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<NoteModel> call = apiInterface.deleteNote(id);
        call.enqueue(new Callback<NoteModel>() {
            @Override
            public void onResponse(Call<NoteModel> call, Response<NoteModel> response) {
                view.hideProgress();
                if (response.isSuccessful() && response.body() != null){
                    Boolean success = response.body().getSuccess();
                    if (success) {
                        view.onRequestSuccess(response.body().getMessage());
                    }
                    else{
                        view.onRequestError(response.body().getMessage());
                    }
                }
            }

            @Override
            public void onFailure(Call<NoteModel> call, Throwable t) {
                view.hideProgress();
                view.onRequestError(t.getLocalizedMessage());
            }
        });

    }
}
