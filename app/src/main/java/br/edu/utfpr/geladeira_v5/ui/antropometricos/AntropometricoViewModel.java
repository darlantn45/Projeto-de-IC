package br.edu.utfpr.geladeira_v5.ui.antropometricos;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class AntropometricoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public AntropometricoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Antropométrico");
    }

    public LiveData<String> getText() {
        return mText;
    }
}