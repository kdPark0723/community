package com.kdpark0723.communityCommon.test.api.auth

import com.kdpark0723.communityCommon.APITest
import com.kdpark0723.communityCommon.SetUpRole
import com.kdpark0723.communityCommon.model.role.dataAccess.RoleDataAccessor
import com.kdpark0723.communityCommon.model.user.User
import com.kdpark0723.communityCommon.model.user.UserFactory
import com.kdpark0723.communityCommon.model.user.dataAccess.UserDataAccessor
import com.kdpark0723.communityCommon.model.user.dataTransfer.SignInRequest
import com.kdpark0723.communityCommon.model.user.dataTransfer.SignInResponse
import com.kdpark0723.communityCommon.model.user.dataTransfer.SignUpRequest
import org.junit.After
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@RunWith(SpringRunner::class)
@SpringBootTest
class SignInControllerTests : APITest() {
    private val factory = UserFactory()

    @Autowired
    private val userDataAccess: UserDataAccessor? = null

    @Autowired
    private val roleDataAccess: RoleDataAccessor? = null

    private var setUpRole: SetUpRole? = null

    private var signedUpUser: User? = null

    @Before
    public override fun setUp() {
        super.setUp()

        setUpRole = SetUpRole(roleDataAccess!!)

        setUpRole!!.setRole()

        val signUpRequest = factory.createDummySignUpRequest()
        signedUpUser = signUpRequest.toUser()

        signUpAndGetResult(signUpRequest)
    }

    @After
    fun close() {
        signedUpUser?.username?.let { userDataAccess?.deleteByUsername(it) }
    }

    @Test
    fun signInSuccess() {
        val signInRequestForCheckUsername = SignInRequest()
        signInRequestForCheckUsername.usernameOrEmail = signedUpUser?.username ?: ""
        signInRequestForCheckUsername.password = signedUpUser?.password ?: ""

        signInAndCheckSuccess(signInRequestForCheckUsername)

        val signInRequestForCheckEmail = SignInRequest()
        signInRequestForCheckEmail.usernameOrEmail = signedUpUser?.email ?: ""
        signInRequestForCheckEmail.password = signedUpUser?.password ?: ""

        signInAndCheckSuccess(signInRequestForCheckEmail)
    }

    @Test
    fun signInFailBecauseNotSignedUp() {
        val other = factory.createDummySignInRequest()

        signInAndCheckFailBecauseNotSignedUp(other)
    }

    @Test
    fun signInFailBecauseNotCorrectPassword() {
        val signInRequest = SignInRequest()
        signInRequest.usernameOrEmail = signedUpUser?.username ?: ""
        signInRequest.password = "notCorrectPassword"

        signInAndCheckFailBecauseNotCorrectPassword(signInRequest)
    }


    private fun signInAndCheckSuccess(request: SignInRequest) {
        val result = signInAndGetResult(request)

        val status = result.response.status
        assertEquals(200, status)

        val content = result.response.contentAsString
        val response = mapFromJson(content, SignInResponse::class.java)

        Assert.assertTrue(response.accessToken != null)
    }

    private fun signInAndCheckFailBecauseNotSignedUp(request: SignInRequest) {
        val result = signInAndGetResult(request)

        val status = result.response.status
        assertEquals(404, status)
    }

    private fun signInAndCheckFailBecauseNotCorrectPassword(request: SignInRequest) {
        val result = signInAndGetResult(request)

        val status = result.response.status
        assertEquals(403, status)
    }

    private fun signUpAndGetResult(request: SignUpRequest): MvcResult {
        val uri = "/api/auth/sign_up"

        val inputJson = mapToJson(request)

        return mvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson)).andReturn()
    }

    private fun signInAndGetResult(request: SignInRequest): MvcResult {
        val uri = "/api/auth/sign_in"

        val inputJson = mapToJson(request)

        return mvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson)).andReturn()
    }
}