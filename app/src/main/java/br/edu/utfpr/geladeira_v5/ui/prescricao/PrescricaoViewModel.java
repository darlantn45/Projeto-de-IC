package br.edu.utfpr.geladeira_v5.ui.prescricao;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class PrescricaoViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public PrescricaoViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Prescrição Dietética");
    }

    public LiveData<String> getText() {
        return mText;
    }
}