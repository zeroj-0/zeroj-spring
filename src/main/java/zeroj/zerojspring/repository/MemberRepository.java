package zeroj.zerojspring.repository;

import zeroj.zerojspring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    //회원이 저장소에 저장
    Member save(Member member);

    //저장소에서 findById와 findByName으로 찾아올 수 있음
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);

    //지금까지 저장된 모든 회원리스트를 반환
    List<Member> findAll();
}
