package com.kdpark0723.communityCommon.models.user

import com.kdpark0723.communityCommon.models.dataAccessObject.MockDataAccess
import com.kdpark0723.communityCommon.models.user.dataAccessObject.UserDataAccess

class MockUserDataAccess : UserDataAccess, MockDataAccess<User, String>() {
    override fun getEntityKey(entity: User): String {
        return entity.identifier
    }
}