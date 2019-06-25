package com.kdpark0723.communityCommon.models.user

import com.kdpark0723.communityCommon.models.dao.MockDAO
import com.kdpark0723.communityCommon.models.user.dao.UserDAO

class MockUserDAO : UserDAO, MockDAO<User, String>() {
    override fun getEntityKey(entity: User): String {
        return entity.identifier
    }
}