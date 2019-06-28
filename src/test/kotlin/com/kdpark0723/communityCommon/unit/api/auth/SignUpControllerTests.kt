package com.kdpark0723.communityCommon.unit.api.auth

import com.kdpark0723.communityCommon.AbstractTest
import com.kdpark0723.communityCommon.model.user.UserFactory
import com.kdpark0723.communityCommon.model.user.dataTransferObject.SignUpResponseForm
import com.kdpark0723.communityCommon.model.user.dataTransferObject.SignUpUser
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MvcResult
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@RunWith(SpringRunner::class)
@SpringBootTest
class SignUpControllerTests : AbstractTest() {
    @Before
    public override fun setUp() {
        super.setUp()
    }

    @Test
    fun signUpSuccess() {
        val signUpUser = factory.createDummySignUpUser()

        signUpAndCheckSuccess(signUpUser)
    }

    @Test
    fun signUpFailBecauseEmailIsOverlap() {
        val signUpUser = factory.createDummySignUpUser()

        signUpAndCheckSuccess(signUpUser)
        signUpAndCheckFailBecauseEmailIsOverlap(signUpUser)
    }

    fun signUpAndCheckSuccess(user: SignUpUser) {
        val result = signUpAndGetResult(user)

        val status = result.response.status
        assertEquals(201, status)

        val correctForm = SignUpResponseForm(user.toUser())

        val content = result.response.contentAsString
        val response = mapFromJson(content, SignUpResponseForm::class.java)

        assertEquals(correctForm, response)
    }

    fun signUpAndCheckFailBecauseEmailIsOverlap(user: SignUpUser) {
        val result = signUpAndGetResult(user)

        val status = result.response.status
        assertEquals(403, status)
    }

    fun signUpAndGetResult(user: SignUpUser): MvcResult {
        val uri = "/api/auth/sign_up"

        val inputJson = mapToJson(user)

        return mvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson)).andReturn()
    }

    private val factory = UserFactory()

}