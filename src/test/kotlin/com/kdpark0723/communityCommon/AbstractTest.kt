package com.kdpark0723.communityCommon

import com.fasterxml.jackson.core.JsonParseException
import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.JsonMappingException
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.io.IOException

@RunWith(SpringJUnit4ClassRunner::class)
@SpringBootTest(classes = [CommunityCommonApplication::class])
@WebAppConfiguration
abstract class AbstractTest {

    protected lateinit var mvc: MockMvc

    @Autowired
    internal var webApplicationContext: WebApplicationContext? = null

    @Throws(JsonProcessingException::class)
    protected fun mapToJson(obj: Any): String {
        val objectMapper = ObjectMapper()
        return objectMapper.writeValueAsString(obj)
    }

    @Throws(JsonParseException::class, JsonMappingException::class, IOException::class)
    protected fun <T> mapFromJson(json: String, clazz: Class<T>): T {
        val objectMapper = ObjectMapper()
        return objectMapper.readValue(json, clazz)
    }

    protected fun setUp() {
        mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext!!).build()
    }
}