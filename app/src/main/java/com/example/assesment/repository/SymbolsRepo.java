package com.example.assesment.repository;


import android.content.Context;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.assesment.api.ApiInterface;
import com.example.assesment.api.RetrofitInstance;
import com.example.assesment.model.RatesModel;
import com.example.assesment.model.SymModel;


import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SymbolsRepo {
    private Context mContext;
    private MutableLiveData mutableLiveData, mutableLiveDataRates;

    private SymModel symbolsModel;
    private RatesModel ratesModel;
    private Map<String, String> ratesMap;

    public SymbolsRepo(Context context) {
        this.mContext = context;
    }

    private Map<String, String> stringStringMap;
    private List<String> symbolsList;

    public MutableLiveData<Map<String, String>> getsymbols() {

        try {
            mutableLiveData = new MutableLiveData();

            ApiInterface apiServices = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);

            apiServices.getSymbolList().enqueue(new Callback<SymModel>() {
                @Override
                public void onResponse(Call<SymModel> call, Response<SymModel> response) {
                    if (response.isSuccessful()) {
                        symbolsModel = response.body();
                        stringStringMap = symbolsModel.getSymbols();
                        symbolsList = new ArrayList<String>(stringStringMap.keySet());
                        mutableLiveData.postValue(stringStringMap);
                    } else {
                        Log.e("failed", "onFailure: error");
                    }
                }

                @Override
                public void onFailure(Call<SymModel> call, Throwable t) {
                    Log.e("failed", "onFailure: " + t.getLocalizedMessage());
                }
            });

        } catch (Exception e) {
            Log.e("", "onFailure: " + e.getLocalizedMessage());
        }
        return mutableLiveData;
    }


    public MutableLiveData<Map<String, String>> getRate(String symbol, String base) {
        try {
            mutableLiveDataRates = new MutableLiveData();

            ApiInterface apiServices = RetrofitInstance.getRetrofitInstance().create(ApiInterface.class);
            apiServices.geSymModelCall(symbol, base).enqueue(new Callback<RatesModel>() {
                @Override
                public void onResponse(Call<RatesModel> call, Response<RatesModel> response) {
                    if (response.isSuccessful()) {
                        ratesModel = response.body();
                        ratesMap = ratesModel.getRates();
                        mutableLiveDataRates.postValue(ratesMap);
                    } else {
                        Log.e("failed", "onFailure: error");
                    }

                }

                @Override
                public void onFailure(Call<RatesModel> call, Throwable t) {

                }
            });
        } catch (Exception e) {
            Log.e("", "onFailure: " + e.getLocalizedMessage());
        }
        return mutableLiveDataRates;
    }
}
