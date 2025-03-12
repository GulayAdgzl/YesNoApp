package com.glyadgzl.random.viewmodels
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.glyadgzl.random.model.YesNoResponse
import com.glyadgzl.random.repository.YesNoRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class YesNoViewModel @Inject constructor(private val yesNoRepository: YesNoRepository) :ViewModel() {

    val randomData: StateFlow<YesNoResponse?> get() = yesNoRepository.getYesNoResponse

    suspend fun getRandomData(){
        viewModelScope.launch {
            yesNoRepository.getRandomLiveData()
        }
    }

    val randomLiveData: LiveData<YesNoResponse?> get() =yesNoRepository.getRandomLiveData

    suspend fun getRandomLiveData(){
        viewModelScope.launch {
            yesNoRepository.getRandomLiveData()
        }
    }
}