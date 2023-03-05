package com.example.assesment.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.assesment.repository.SymbolsRepo;

import java.util.Map;

public class SymbolViewModel extends AndroidViewModel {

    private SymbolsRepo symbolsRepo;
    public SymbolViewModel(@NonNull Application application) {
        super(application);
        symbolsRepo = new SymbolsRepo(application);

    }

    public LiveData<Map<String, String>> getsymbols(){
        return symbolsRepo.getsymbols();
    }
    public LiveData<Map<String, String>> getRate(String symbol,String base){
        return symbolsRepo.getRate(symbol,base);
    }
}
