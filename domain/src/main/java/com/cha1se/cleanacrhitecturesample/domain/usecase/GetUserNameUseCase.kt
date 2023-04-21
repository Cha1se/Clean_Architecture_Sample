package com.cha1se.cleanacrhitecturesample.domain.usecase

import com.cha1se.cleanacrhitecturesample.domain.models.UserName
import com.cha1se.cleanacrhitecturesample.domain.repository.UserRepository

class GetUserNameUseCase(private  val userRespository: UserRepository) {

    fun execute(): UserName {
        return userRespository.getName()
    }
}