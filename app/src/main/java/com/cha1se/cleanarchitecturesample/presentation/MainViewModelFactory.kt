package com.cha1se.cleanarchitecturesample.presentation

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.cha1se.cleanacrhitecturesample.data.repository.UserRepositoryImpl
import com.cha1se.cleanacrhitecturesample.data.storage.sharedprefs.SharedPrefUserStorage
import com.cha1se.cleanacrhitecturesample.domain.usecase.GetUserNameUseCase
import com.cha1se.cleanacrhitecturesample.domain.usecase.SaveUserNameUseCase

class MainViewModelFactory(context: Context) : ViewModelProvider.Factory {

    private val userRepository by lazy(LazyThreadSafetyMode.NONE) {
        UserRepositoryImpl(
            userStorage = SharedPrefUserStorage(context = context)
        )
    }
    private val getUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        GetUserNameUseCase(
            userRespository = userRepository
        )
    }
    private val saveUserNameUseCase by lazy(LazyThreadSafetyMode.NONE) {
        SaveUserNameUseCase(
            userRepository = userRepository
        )
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            getUserNameUseCase = getUserNameUseCase,
            saveUserNameUseCase = saveUserNameUseCase
        ) as T
    }

}