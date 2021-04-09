package br.edu.utfpr.geladeira_v5.ui.laudo;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class LaudoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public LaudoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Laudo Biométrico");

    }

    public LiveData<String> getText() {
        return mText;
    }
}