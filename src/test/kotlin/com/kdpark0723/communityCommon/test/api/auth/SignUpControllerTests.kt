package com.kdpark0723.communityCommon.test.api.auth

import com.kdpark0723.communityCommon.AbstractTest
import com.kdpark0723.communityCommon.model.role.Role
import com.kdpark0723.communityCommon.model.role.dataAccess.RoleDataAccess
import com.kdpark0723.communityCommon.model.user.User
import com.kdpark0723.communityCommon.model.user.UserFactory
import com.kdpark0723.communityCommon.model.user.dataAccess.UserDataAccess
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
class SignUpControllerTests : AbstractTest() {

    private val factory = UserFactory()

    private val users: MutableList<User> = emptyList<User>().toMutableList()

    @Autowired
    private val userDataAccess: UserDataAccess? = null

    @Autowired
    private val roleDataAccess: RoleDataAccess? = null

    @Before
    public override fun setUp() {
        super.setUp()

        saveRole(Role.Name.USER)
        saveRole(Role.Name.ADMIN)
    }

    private fun saveRole(name: Role.Name) {
        if (!roleDataAccess!!.existsByName(name)) {
            val role = Role()
            role.name = name

            roleDataAccess.save(role)
        }
    }

    @After
    fun close() {
        for (user in users) {
            userDataAccess?.deleteByUsername(user.username)
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