package com.cha1se.cleanacrhitecturesample.domain.repository

import com.cha1se.cleanacrhitecturesample.domain.models.SaveUserNameParam
import com.cha1se.cleanacrhitecturesample.domain.models.UserName

interface UserRepository {

    fun saveName(saveparam: SaveUserNameParam): Boolean

    fun getName(): UserName

}