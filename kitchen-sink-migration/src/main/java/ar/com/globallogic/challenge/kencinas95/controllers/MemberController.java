package ar.com.globallogic.challenge.kencinas95.controllers;

import ar.com.globallogic.challenge.kencinas95.models.Member;
import ar.com.globallogic.challenge.kencinas95.services.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * MemberController
 * Holds the endpoints of the REST API for "members".
 */
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberController {
    private final MemberService service;

    /**
     * Lists all members from the database.
     * @return List of all members registered in the database
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Member>> listAllMembers() {
        log.info("Listing all members...");
        return ResponseEntity.ok(service.getAllMembers());
    }

    /**
     * Lookup a member by a given identifier.
     *
     * @param id: member identifier
     * @throws java.util.NoSuchElementException: on member not found
     * @return member
     */
    @GetMapping(value = "/{identifier}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> lookupMemberById(@PathVariable("identifier") final String id) {
        return ResponseEntity.ok(service.getMemberById(id));
    }

    /**
     * Create a new member.
     *
     * @param member: json member data
     * @throws IllegalArgumentException: on bad request
     * @throws RuntimeException: on request body validation
     * @return empty plain http response (201 CREATED)
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createMember(
            @Validated @RequestBody final Member member
    ) {
        service.register(member);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
