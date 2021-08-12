package zeroj.zerojspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zeroj.zerojspring.domain.Member;

import java.util.Optional;

//인터스페이스가 인터스페이스를 받을 때 extends로 받음
//SpringDataJpa가 JpaRository 받고 있을때 이 인터페스의 구현체를 자동으로 만들어준다(스프링빈에 자동으로 등록)
public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository {

    @Override
    Optional<Member> findByName(String name);
}
