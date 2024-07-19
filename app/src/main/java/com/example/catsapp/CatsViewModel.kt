package com.example.catsapp

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.catsapp.API.CatsModel
import com.example.catsapp.API.NetworkResponse
import com.example.catsapp.API.RetrofitInstance
import kotlinx.coroutines.launch

class CatsViewModel : ViewModel() {

    private val catsApi = RetrofitInstance.catsApi
    private val _catsResult = MutableLiveData<NetworkResponse<CatsModel>>()
    val catsResult: LiveData<NetworkResponse<CatsModel>> = _catsResult

    fun getData(cats: String) {
        _catsResult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = catsApi.getCats(cats)
                if (response.isSuccessful) {
                    val catsModel = response.body()
                    if(catsModel != null) {
                        _catsResult.value = NetworkResponse.Success(catsModel)
                        Log.i("Response :", response.body().toString())
                    } else{
                        _catsResult.value = NetworkResponse.Error("catsModel is null")
                        Log.i("Response", "catsModel is null")
                    }
                }
                else
                    _catsResult.value = NetworkResponse.Error(response.message())
                    Log.i("Error :", response.message())

            } catch (e : Exception) {
                _catsResult.value = NetworkResponse.Error(e.toString())
                Log.e("Exception", e.toString())
            }
        }
    }
}