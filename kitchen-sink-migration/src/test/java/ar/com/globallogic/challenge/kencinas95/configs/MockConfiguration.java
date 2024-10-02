package ar.com.globallogic.challenge.kencinas95.configs;

import ar.com.globallogic.challenge.kencinas95.repositories.MemberRepository;
import ar.com.globallogic.challenge.kencinas95.services.MemberRegistrationService;
import ar.com.globallogic.challenge.kencinas95.services.MemberService;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

@DataMongoTest
@TestConfiguration
public class MockConfiguration {

    @Bean
    @Primary
    public MemberRepository memberRepository() {
        return Mockito.mock(MemberRepository.class);
    }

    @Bean
    public MemberService memberService() {
        return Mockito.mock(MemberService.class);
    }

    @Bean
    public MemberRegistrationService memberRegistrationService() {
        return Mockito.mock(MemberRegistrationService.class);
    }

}
