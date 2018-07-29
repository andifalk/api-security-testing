package com.example.serverapp.user.api

import com.epages.restdocs.raml.RamlResourceDocumentation.ramlResource
import com.example.serverapp.user.boundary.User
import com.example.serverapp.user.boundary.UserService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers
import org.mockito.ArgumentMatchers.argThat
import org.mockito.BDDMockito.eq
import org.mockito.BDDMockito.given
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.boot.test.mock.mockito.SpyBean
import org.springframework.http.HttpHeaders.CONTENT_TYPE
import org.springframework.http.MediaType.*
import org.springframework.restdocs.RestDocumentationContextProvider
import org.springframework.restdocs.RestDocumentationExtension
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document
import org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.documentationConfiguration
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get
import org.springframework.restdocs.operation.preprocess.Preprocessors.prettyPrint
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.setup.DefaultMockMvcBuilder
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext
import java.util.*

@ExtendWith(SpringExtension::class, RestDocumentationExtension::class)
@WebMvcTest(UserRestController::class)
internal class UserRestControllerApiIntegrationTest {

    private lateinit var mockMvc: MockMvc

    @MockBean lateinit var userService: UserService
    @SpyBean lateinit var resourceAssembler: UserResourceAssembler

    @BeforeEach
    fun setUp(
            webApplicationContext: WebApplicationContext,
            restDocumentation: RestDocumentationContextProvider) {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
                .apply<DefaultMockMvcBuilder>(
                        documentationConfiguration(restDocumentation)
                                .operationPreprocessors()
                                .withRequestDefaults(prettyPrint())
                                .withResponseDefaults(prettyPrint()))
                .build()
    }

    @Test
    fun getAllUsers() {

        given(userService.findAllUsers()).willReturn(listOf(
            User(
                    UUID.randomUUID(),
                    "hans.muster@example.com",
                    "Hans",
                    "Muster",
                    "secret",
                    listOf("ROLE_USER"))
        ))

        mockMvc.perform(get("/users")
                .accept(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                //.andExpect(content().json(expectedResponse, true))
                .andDo(document("users-get", ramlResource()))
    }

    @Test
    fun getUser() {

        val userIdentifier = UUID.randomUUID()
        given(userService.findUserByIdentifier(userIdentifier)).willReturn(
                User(
                        userIdentifier,
                        "hans.muster@example.com",
                        "Hans",
                        "Muster",
                        "secret",
                        listOf("ROLE_USER"))
        )

        mockMvc.perform(get("/users/{userId}", userIdentifier)
                .accept(APPLICATION_JSON_VALUE))
                .andExpect(status().isOk)
                .andExpect(content().contentType(APPLICATION_JSON_UTF8))
                //.andExpect(content().json(expectedResponse, true))
                .andDo(document("user-get", ramlResource()))
    }

    @Test
    fun createUser() {
    }
}