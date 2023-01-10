package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static Long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  //null이 반환될 가능성이 있다면 Optional로 감싸서 반환
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()
                .filter(member -> member.getName().equals(name))    //람다식
                .findAny();                                         //하나라도 찾으면 반환
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values()); //member를 반환
    }

    public void clearStore() {
        store.clear();
    }
}
