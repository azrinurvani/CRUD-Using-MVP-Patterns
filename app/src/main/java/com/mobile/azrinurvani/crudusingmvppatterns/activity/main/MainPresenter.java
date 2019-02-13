package com.mobile.azrinurvani.crudusingmvppatterns.activity.main;

import com.mobile.azrinurvani.crudusingmvppatterns.model.NoteModel;
import com.mobile.azrinurvani.crudusingmvppatterns.network.ApiClient;
import com.mobile.azrinurvani.crudusingmvppatterns.network.ApiInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements IMainPresenter {

    private MainView view;

    public MainPresenter(MainView view) {
        this.view = view;
    }
//tampung di interface presenter
    public void getData(){
        view.showLoading();
        //Request Data to Server
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<NoteModel>> call = apiInterface.getNote();
        call.enqueue(new Callback<List<NoteModel>>() {
            @Override
            public void onResponse(Call<List<NoteModel>> call, Response<List<NoteModel>> response) {
                view.hideLoading();
                if (response.isSuccessful()&&response.body()!=null){
                    view.onGetResult(response.body());
                }
            }

            @Override
            public void onFailure(Call<List<NoteModel>> call, Throwable t) {
                view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage());

            }
        });
    }
}
