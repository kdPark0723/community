package com.kdpark0723.communityCommon.test.api.auth

import com.kdpark0723.communityCommon.APITest
import com.kdpark0723.communityCommon.SetUpRole
import com.kdpark0723.communityCommon.model.role.dataAccess.RoleDataAccessor
import com.kdpark0723.communityCommon.model.user.User
import com.kdpark0723.communityCommon.model.user.UserFactory
import com.kdpark0723.communityCommon.model.user.dataAccess.UserDataAccessor
import com.kdpark0723.communityCommon.model.user.dataTransfer.SignUpRequest
import com.kdpark0723.communityCommon.model.user.dataTransfer.SignUpResponse
import org.junit.After
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
class SignUpControllerTests : APITest() {

    private val factory = UserFactory()

    private val users: MutableList<User> = emptyList<User>().toMutableList()

    @Autowired
    private val userDataAccess: UserDataAccessor? = null

    @Autowired
    private val roleDataAccess: RoleDataAccessor? = null

    private var setUpRole: SetUpRole? = null

    @Before
    public override fun setUp() {
        super.setUp()

        setUpRole = SetUpRole(roleDataAccess!!)

        setUpRole!!.setRole()
    }

    @After
    fun close() {
        users.forEach {
            userDataAccess?.deleteByUsername(it.username)
        }
    }

    @Test
    fun signUpSuccess() {
        val signUpUser = factory.createDummySignUpRequest()

        signUpAndCheckSuccess(signUpUser)
    }

    @Test
    fun signUpFailBecauseAlreadySigned() {
        val signUpUser = factory.createDummySignUpRequest()

        signUpAndCheckSuccess(signUpUser)
        signUpAndCheckFailBecauseEmailIsOverlap(signUpUser)
    }

    private fun signUpAndCheckSuccess(request: SignUpRequest) {
        val result = signUpAndGetResult(request)

        val status = result.response.status
        assertEquals(201, status)

        val correctForm = SignUpResponse(request.toUser())

        val content = result.response.contentAsString
        val response = mapFromJson(content, SignUpResponse::class.java)

        assertEquals(correctForm, response)
    }

    private fun signUpAndCheckFailBecauseEmailIsOverlap(request: SignUpRequest) {
        val result = signUpAndGetResult(request)

        val status = result.response.status
        assertEquals(403, status)
    }

    private fun signUpAndGetResult(request: SignUpRequest): MvcResult {
        val uri = "/api/auth/sign_up"

        users.add(request.toUser())

        val inputJson = mapToJson(request)

        return mvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson)).andReturn()
    }

}