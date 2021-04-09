package br.edu.utfpr.geladeira_v5.ui.receitas;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ReceitasViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ReceitasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Receitas");
    }

    public LiveData<String> getText() {
        return mText;
    }
}