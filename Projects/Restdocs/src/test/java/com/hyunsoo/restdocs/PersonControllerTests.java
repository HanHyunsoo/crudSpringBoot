package com.hyunsoo.restdocs;

import com.hyunsoo.restdocs.controller.PersonController;
import com.hyunsoo.restdocs.dto.PersonRequest;
import com.hyunsoo.restdocs.dto.PersonResponse;
import com.hyunsoo.restdocs.model.Person;
import com.hyunsoo.restdocs.service.PersonService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.restdocs.headers.HeaderDocumentation.headerWithName;
import static org.springframework.restdocs.headers.HeaderDocumentation.responseHeaders;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.operation.preprocess.Preprocessors.*;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public class PersonControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    PersonService personService;

    @Test
    void createPerson() throws Exception {
        PersonRequest personRequest = new PersonRequest();
        personRequest.setName("한현수");
        personRequest.setAge((short) 24);

        when(personService.save(any())).thenReturn(1L);

        this.mockMvc.perform(
                post("/api/people")
                        .content("{\"name\": \"한현수\", \"age\": 26}")
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isCreated())
                .andDo(document("person-create",
                        preprocessRequest(prettyPrint()),
                        requestFields(
                                fieldWithPath("name").description("이름"),
                                fieldWithPath("age").description("나이")
                        ),
                        responseHeaders(
                            headerWithName(HttpHeaders.LOCATION).description("요청을 완수하면 해당 Person의 정보 url을 반환")
                        )
                ));

    }

    @Test
    void getPerson() throws Exception {
        PersonResponse personResponse = PersonResponse.builder()
                .id(1L)
                .name("한현수")
                .age((short) 24)
                .created(LocalDateTime.now())
                .build();

        when(personService.findById(anyLong())).thenReturn(personResponse);

        this.mockMvc.perform(
                RestDocumentationRequestBuilders.get(
                        "/api/people/{id}", 1L
                ).accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("person-get",
                        preprocessResponse(prettyPrint()),
                        pathParameters(
                                parameterWithName("id").description("Person id")
                        ),
                        responseFields(
                                fieldWithPath("id").description("PK"),
                                fieldWithPath("name").description("이름"),
                                fieldWithPath("age").description("나이"),
                                fieldWithPath("created").description("생성시간")
                        )
                ));
    }

    @Test
    void getPersonAll() throws Exception {
        List<PersonResponse> list = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            PersonResponse personResponse = PersonResponse.builder()
                    .id((long) i)
                    .name("한현수" + i)
                    .age((short) i)
                    .created(LocalDateTime.now())
                    .build();

            list.add(personResponse);
        }

        when(personService.findAll()).thenReturn(list);

        this.mockMvc.perform(
                        get("/api/people")
                                .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(document("person-get-all",
                        preprocessResponse(prettyPrint()),
                        responseFields(
                                fieldWithPath("[].id").description("PK"),
                                fieldWithPath("[].name").description("이름"),
                                fieldWithPath("[].age").description("나이"),
                                fieldWithPath("[].created").description("생성시간")
                        )
                ));
    }

    @Test
    void updatePerson() throws Exception {
        this.mockMvc.perform(
                        patch("/api/people/{id}", 1L)
                                .content("{\"name\": \"한현수 수정\", \"age\": 30}")
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andDo(document("person-update",
                        requestFields(
                                fieldWithPath("name").description("이름"),
                                fieldWithPath("age").description("나이")
                        )
                ));
    }

    @Test
    void deletePerson() throws Exception {
        this.mockMvc.perform(
                        delete("/api/people/{id}", 1L)
                )
                .andExpect(status().isOk())
                .andDo(document("person-delete",
                        preprocessRequest(prettyPrint())
                ));
    }
}
