package com.kdpark0723.communityCommon.test.services.auth

import com.kdpark0723.communityCommon.SetUpRole
import com.kdpark0723.communityCommon.exception.CantFindUserException
import com.kdpark0723.communityCommon.exception.IncorrectUserInformationException
import com.kdpark0723.communityCommon.model.role.MockRoleDataAccessor
import com.kdpark0723.communityCommon.model.role.dataAccess.RoleDataAccessor
import com.kdpark0723.communityCommon.model.user.MockUserDataAccessor
import com.kdpark0723.communityCommon.model.user.User
import com.kdpark0723.communityCommon.model.user.UserFactory
import com.kdpark0723.communityCommon.model.user.dataAccess.UserDataAccessor
import com.kdpark0723.communityCommon.security.JwtTokenProvider
import com.kdpark0723.communityCommon.service.auth.SignInService
import com.kdpark0723.communityCommon.service.auth.SignUpService
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@SpringBootTest
class SignInServiceTests {

    private val userDataAccess: UserDataAccessor = MockUserDataAccessor()

    private val roleDataAccess: RoleDataAccessor = MockRoleDataAccessor()

    private val tokenProvider: JwtTokenProvider = JwtTokenProvider(
        "18125-40-4-11786-5414-14-9-120730141231834-4988-1-65-32-72-447-481278-89-75-79-124-7-83-32-55-6946-126-4811263730-29-44-803573-591852-108-38-19-96105-50-9735-109292-78",
        604800000)

    private val signUpService: SignUpService = SignUpService(userDataAccess, roleDataAccess)

    private val signInService: SignInService = SignInService(userDataAccess, tokenProvider)

    private val factory: UserFactory = UserFactory()

    private val signedUpUser: User = factory.createDummyUser()

    private val setUpRole: SetUpRole = SetUpRole(this.roleDataAccess)

    @Before
    fun setUp() {
        setUpRole.setRole()

        signUpService.signUp(signedUpUser)
    }

    @Test
    fun signInSuccess() {
        val response = signInService.signIn(signedUpUser)

        assertTrue(response.accessToken != null)
    }

    @Test(expected = CantFindUserException::class)
    fun signInFailBecauseUserNotSignedUpBefore() {
        val otherUser = factory.createDummyUser()

        signInService.signIn(otherUser)
    }

    @Test(expected = IncorrectUserInformationException::class)
    fun signInFailBecauseUserPasswordIsIncorrect() {
        val otherUser = signedUpUser.copy(password = "otherPassword")

        signInService.signIn(otherUser)
    }
}