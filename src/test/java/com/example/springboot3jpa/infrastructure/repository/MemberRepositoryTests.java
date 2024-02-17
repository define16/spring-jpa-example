package com.example.springboot3jpa.infrastructure.repository;

import com.example.springboot3jpa.domain.member.MemberAggregate;
import com.example.springboot3jpa.infrastructure.repositorty.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;

    @Test
    void testJpa() {
        String useId1 = UUID.randomUUID().toString();
        System.out.println(useId1);
        MemberAggregate memberAggregate = new MemberAggregate(useId1,"test", "1234");
        this.memberRepository.save(memberAggregate);

        String useId12 = UUID.randomUUID().toString();
        MemberAggregate memberAggregate2 = new MemberAggregate(useId12,"test", "5678");
        this.memberRepository.save(memberAggregate2);

        Optional<MemberAggregate> saved = this.memberRepository.findByUserId(useId12);
        assert (memberAggregate2.equals(saved.get()));
    }
}
