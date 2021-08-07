package zeroj.zerojspring.repository;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import zeroj.zerojspring.domain.Member;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //test가 끝날때마다 repository를 깔끔하게 지워주는 코드 작성
    //@AfterEach를 달아주면 메소드가 하나 실행되고 끝날때마다 실행되는 것
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){
        Member member = new Member();
        member.setName("서영정");

        repository.save(member);

        //반환타입이 Optional이라 Optional에서 값을 꺼낼때 get()메소드 사. 원래 바로 꺼내는 것은 좋은 방법은 아님
        Member result = repository.findById(member.getId()).get();
        //member와 result가 같은 값인지 확인할 수 있음. 다르면 에러뜸
//        Assertions.assertEquals(member, result);

        assertThat(member).isEqualTo(result);

    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("안태용");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("서영정");
        repository.save(member2);

        Member result = repository.findByName("안태용").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("안태용");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("서영정");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
