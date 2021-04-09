package br.edu.utfpr.geladeira_v5.ui.nutricional;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class NutricionalViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public NutricionalViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Orientação Nutricional");
    }

    public LiveData<String> getText() {
        return mText;
    }
}