package ar.com.globallogic.challenge.kencinas95.controller;

import ar.com.globallogic.challenge.kencinas95.configs.MockConfiguration;
import ar.com.globallogic.challenge.kencinas95.models.Member;
import ar.com.globallogic.challenge.kencinas95.services.MemberService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@Import(MockConfiguration.class)
public class MemberControllerTest {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MemberService service;


    @Test
    public void testShouldRegisterMemberSuccess() throws Exception {
        var member = Member.builder()
                .name("Joe Doe")
                .email("joedoe@mail.com")
                .phoneNumber("1100223344")
                .build();

        var request = post("/members")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)
                .content(OBJECT_MAPPER.writeValueAsBytes(member));

        Mockito.doNothing().when(service).register(member);

        mockMvc.perform(request)
                .andDo(print())
                .andExpect(status().isCreated());

        Mockito.verify(service, Mockito.atLeastOnce())
                .register(member);

    }

}
