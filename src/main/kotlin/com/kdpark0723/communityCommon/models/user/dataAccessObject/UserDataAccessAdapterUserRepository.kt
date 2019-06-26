package com.kdpark0723.communityCommon.models.user.dataAccessObject

import com.kdpark0723.communityCommon.models.dataAccessObject.DataAccessAdapterCrudRepository
import com.kdpark0723.communityCommon.models.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class UserDataAccessAdapterUserRepository : UserDataAccess, DataAccessAdapterCrudRepository<User, String, UserRepository>() {
    @Autowired
    final override val repository: UserRepository? = null
}