package com.kdpark0723.communityCommon.test

import com.kdpark0723.communityCommon.AbstractTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders

@RunWith(SpringRunner::class)
@SpringBootTest
class SwaggerTests : AbstractTest() {
    @Before
    public override fun setUp() {
        super.setUp()
    }

    @Test
    fun swaggerUIIsExist() {
        val uri = "/swagger-ui.html"
        val mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
            .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn()

        val status = mvcResult.response.status
        assertEquals(200, status)
    }

}