package zeroj.zerojspring;

import zeroj.zerojspring.domain.Member;
import zeroj.zerojspring.repository.MemberRepository;
import zeroj.zerojspring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;

public class MemberService {

    //memberservicetest에서 사용되는 memberrepsitory와 다른 인스턴스를 가지기 때문에 같은 인스턴스에서 비교를 해야하기 때문에 새롭게 바꿔주어야함
//    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //바꾼 내용
    private final MemberRepository memberRepository;

    //생성자 만들어줌. 외부에서 넣어주도록 만들어주는 것임. 내가 인스턴스해서 만드는 것이 아님
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //회원가입
    public Long join(Member member){
        //같은 이름이 있는 중복회원이 안될 때를 구현
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        //ifPresent -> null이 아닌 값이 있으면 로직이 동작함. Optional이라서 가능한 것.
//        result.ifPresent(m -> {
//            throw new IllegalStateException("이미 존재하는 회원입니다.");
//        });

        //중복회원 검증
        //메소드로 뽑아냄
        validateDuplicateMember(member);

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        //위의 코드 정리함
        //findByName하고 반환값이 Optional member니까 ifPresent함
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    //전체회원 조회기능
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
