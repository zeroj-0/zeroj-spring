package zeroj.zerojspring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import zeroj.zerojspring.repository.JdbcTeplateMemberRepository;
import zeroj.zerojspring.repository.JpaMemberRepository;
import zeroj.zerojspring.repository.MemberRepository;
import zeroj.zerojspring.repository.MemoryMemberRepository;
import zeroj.zerojspring.service.MemberService;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

//자바 코드로 직접 스프링 빈 등록하는 방법
// 그 전에 어노테이션으로 선언해주었던 @Service @Autowired @Repository 지워주어야 함
@Configuration
public class SpringConfig {

    // 스프링이 @Configuration을 읽고 밑을보면서 스프링빈에 등록하라는 뜻으로 인식함
    // MemberService로직 호출해서 스프링빈에 등록해줌
//    private DataSource dataSource;
//
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }

//    @Bean
//    public MemberService memberService(){
//        //생성자로 memberRepository 넣어주어야함
//        // MemberService는 스프링빈에 있는 MemberRepository 엮어주어야 함
//        return new MemberService(memberRepository());
//    }

    @Bean
    public MemberRepository memberRepository() {
        //memberRepository의 경우 인터페이스라 구현체인 MemoryMemberRepository 해주어야 함
//        return new MemoryMemberRepository();
//        return new JdbcTeplateMemberRepository(dataSource);
        return  new JpaMemberRepository(em);
    }
}
