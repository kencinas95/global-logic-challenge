package ar.com.globallogic.challenge.kencinas95.services;

import ar.com.globallogic.challenge.kencinas95.models.Member;
import ar.com.globallogic.challenge.kencinas95.repositories.MemberRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberRegistrationService {
    private final MemberRepository repository;

    @Getter
    private List<Member> members;

    @PostConstruct
    public void init() {
        members = repository.findAllByOrderByNameAsc();
    }

    @EventListener
    public void onMemberListChanged(final Member member) {
        init();
    }
}
