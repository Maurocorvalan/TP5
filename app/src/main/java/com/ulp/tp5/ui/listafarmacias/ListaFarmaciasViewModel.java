package com.ulp.tp5.ui.listafarmacias;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListaFarmaciasViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public ListaFarmaciasViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}