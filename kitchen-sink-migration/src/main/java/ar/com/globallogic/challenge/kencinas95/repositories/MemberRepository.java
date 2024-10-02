package ar.com.globallogic.challenge.kencinas95.repositories;

import ar.com.globallogic.challenge.kencinas95.models.Member;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberRepository extends MongoRepository<Member, String> {
    List<Member> findAllByOrderByNameAsc();

}
