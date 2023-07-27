package com.example.aisleclone.screens.ui.notes;

import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.aisleclone.models.notes.NotesResponseModel;
import com.example.aisleclone.network.NetworkAPI;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotesViewModel extends ViewModel {

    private final MutableLiveData<NotesResponseModel> notesResponseModel = new MutableLiveData<>();

    public LiveData<NotesResponseModel> getnotesResponse() {
        return notesResponseModel;
    }

    public void fetchNotes() {
        NetworkAPI.getNotesResponse(new Callback<NotesResponseModel>() {
            @Override
            public void onResponse(@NonNull Call<NotesResponseModel> call, retrofit2.Response<NotesResponseModel> response) {
                if (response.isSuccessful()) {
                    notesResponseModel.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NotesResponseModel> call, Throwable t) {
                String n = "u";
            }
        });
    }
}