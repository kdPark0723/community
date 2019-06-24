package com.kdpark0723.communityCommon.models.user.dao

import com.kdpark0723.communityCommon.models.dao.SpringDAOAdapter
import com.kdpark0723.communityCommon.models.user.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component

@Component
class SpringUserDAOAdapter : UserDAO, SpringDAOAdapter<User, String, UserRepository>() {
    @Autowired
    final val repository: UserRepository? = null

    init {
        this.registeredRepository = repository
    }
}