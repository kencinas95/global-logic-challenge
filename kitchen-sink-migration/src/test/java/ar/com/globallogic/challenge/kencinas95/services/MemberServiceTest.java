package ar.com.globallogic.challenge.kencinas95.services;

import ar.com.globallogic.challenge.kencinas95.models.Member;
import ar.com.globallogic.challenge.kencinas95.repositories.MemberRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @Mock
    private MemberRepository repository;

    @Mock
    private ApplicationEventPublisher publisher;

    private MemberService service;

    @BeforeEach
    public void setup() {
        service = new MemberService(repository, publisher);
    }

    @Test
    void testRegisterMemberShouldSuccess() {
        final String name = "Jane Doe";
        final String email = "jane@mail.com";
        final String phone = "219522190011";
        final String identifier = "100";

        final Member member = Member.builder()
                .name(name)
                .email(email)
                .phoneNumber(phone)
                .build();

        final Member expected = Member.builder()
                .id(identifier)
                .name(name)
                .email(email)
                .phoneNumber(phone)
                .build();

        Mockito.when(repository.save(member))
                .then(invocationOnMock -> {
                    member.setId(identifier);
                    return null;
                })
                .thenReturn(expected);

        Mockito.doNothing()
                .when(publisher)
                .publishEvent(expected);

        service.register(member);

        Mockito.verify(repository, Mockito.atLeastOnce())
                .save(member);

        Mockito.verify(publisher, Mockito.atLeastOnce())
                .publishEvent(expected);

        Assertions.assertNotNull(member.getId());

        Assertions.assertEquals(member.getId(), identifier);

    }

}
