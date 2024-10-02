package ar.com.globallogic.challenge.kencinas95.services;

import ar.com.globallogic.challenge.kencinas95.models.Member;
import ar.com.globallogic.challenge.kencinas95.repositories.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Slf4j
@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository repository;

    private final ApplicationEventPublisher eventPublisher;

    public List<Member> getAllMembers() {
        return repository.findAllByOrderByNameAsc();
    }

    public Member getMemberById(final String identifier) throws NoSuchElementException {
        log.debug("Getting member by id: {}", identifier);
        return repository.findById(identifier).orElseThrow();
    }

    @SneakyThrows
    public void register(final Member member) {
        log.info("Registering new member: {}", member.getName());
        repository.save(member);
        eventPublisher.publishEvent(member);
    }

}
