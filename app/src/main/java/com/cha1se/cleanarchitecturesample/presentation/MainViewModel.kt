package com.cha1se.cleanarchitecturesample.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cha1se.cleanacrhitecturesample.domain.models.SaveUserNameParam
import com.cha1se.cleanacrhitecturesample.domain.models.UserName
import com.cha1se.cleanacrhitecturesample.domain.usecase.GetUserNameUseCase
import com.cha1se.cleanacrhitecturesample.domain.usecase.SaveUserNameUseCase

class MainViewModel(
    private val getUserNameUseCase: GetUserNameUseCase,
    private val saveUserNameUseCase: SaveUserNameUseCase
) : ViewModel() {

    private var resultLiveMutable = MutableLiveData<String>()
    val resultLive: LiveData<String> = resultLiveMutable

    init {
        Log.e("TAG", "VM created")
    }

    fun save(text: String) {
        val params = SaveUserNameParam(name = text)
        val result: Boolean = saveUserNameUseCase.execute(param = params)
        resultLiveMutable.value = "Save res = $result"
    }

    fun load(){
        val userName: UserName = getUserNameUseCase.execute()
        resultLiveMutable.value = "${userName.firstName}  ${userName.lastName}"
    }

}