package com.glyadgzl.random.repository
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.glyadgzl.random.api.YesNoAPI
import com.glyadgzl.random.model.YesNoResponse

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class YesNoRepository @Inject constructor(private val randomYesNoAPI: YesNoAPI) {

    private val _getYesNoResponse = MutableStateFlow<YesNoResponse?>(null)
    val getYesNoResponse: StateFlow<YesNoResponse?> get() = _getYesNoResponse

    suspend fun getYesNoResponse() {
        val data = randomYesNoAPI.getRandomYesNo()
        if (data.isSuccessful && data.body() != null) {
            _getYesNoResponse.emit(data.body())
        }
    }

    private val _getRandomLiveData = MutableLiveData<YesNoResponse?>()
    val getRandomLiveData: LiveData<YesNoResponse?> get() = _getRandomLiveData

    suspend fun getRandomUserLiveData() {
        val data = randomYesNoAPI.getRandomYesNo()
        if (data.isSuccessful && data.body() != null) {
            _getRandomLiveData.postValue(data.body())
        }
    }
}
