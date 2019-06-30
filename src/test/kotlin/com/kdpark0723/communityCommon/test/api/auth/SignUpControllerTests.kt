package com.kdpark0723.communityCommon.test.api.auth

import com.kdpark0723.communityCommon.AbstractTest
import com.kdpark0723.communityCommon.model.user.UserFactory
import com.kdpark0723.communityCommon.model.user.dataTransferObject.SignUpRequest
import com.kdpark0723.communityCommon.model.user.dataTransferObject.SignUpResponse
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

    private val factory = UserFactory()

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
    fun signUpFailBecauseAlreadySigned() {
        val signUpUser = factory.createDummySignUpUser()

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

        val inputJson = mapToJson(request)

        return mvc.perform(MockMvcRequestBuilders.post(uri)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .content(inputJson)).andReturn()
    }

}