package com.example.springboot3jpa.infrastructure.repository;

import com.example.springboot3jpa.domain.member.Follower;
import com.example.springboot3jpa.domain.member.MemberAggregate;
import com.example.springboot3jpa.domain.member.MemberDto;
import com.example.springboot3jpa.infrastructure.repositorty.FollowerRepository;
import com.example.springboot3jpa.infrastructure.repositorty.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class MemberRepositoryTests {
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private FollowerRepository followerRepository;
    @Autowired
    private TransactionManager transactionManager;

    @Test
    void testJpa() {
        String useId1 = UUID.randomUUID().toString();
        System.out.println(useId1);
        MemberAggregate memberAggregate = new MemberAggregate(useId1,"test", "1234", "ROLE_ADMIN");
        this.memberRepository.save(memberAggregate);

        String useId12 = UUID.randomUUID().toString();
        MemberAggregate memberAggregate2 = new MemberAggregate(useId12,"test", "5678", "ROLE_ADMIN");
        this.memberRepository.save(memberAggregate2);

        Optional<MemberAggregate> saved = this.memberRepository.findByUserId(useId12);
        assert (memberAggregate2.equals(saved.get()));

    }


    @Test
    void testUpdateJpa() {
        String useId1 = UUID.randomUUID().toString();
        System.out.println(useId1);
        MemberAggregate memberAggregate = new MemberAggregate(useId1,"test", "1234", "ROLE_ADMIN");
        this.memberRepository.save(memberAggregate);

        Optional<MemberAggregate> saved = memberRepository.findByUserId(useId1);
        MemberAggregate s = saved.get();
        s.setIntroduction("add");
        memberRepository.save(s);

    }


    @Test
    void testAddFollower() {
        String useId1 = UUID.randomUUID().toString();
        System.out.println(useId1);
        MemberAggregate memberAggregate = new MemberAggregate(useId1,"test11", "1234", "ROLE_ADMIN");
        this.memberRepository.save(memberAggregate);
        String followerUseId1 = UUID.randomUUID().toString();
        Follower follower = MemberDto.Follow.builder().userId(followerUseId1).userName("follower1").build().toEntity();
        memberAggregate.addFollower(follower);
        followerRepository.save(follower);

    }

    @Test
    void testSelectAddFollower() {
        String useId1 = UUID.randomUUID().toString();
        System.out.println(useId1);
        MemberAggregate memberAggregate = new MemberAggregate(useId1,"test11", "1234", "ROLE_ADMIN");
        this.memberRepository.save(memberAggregate);
        String followerUseId1 = UUID.randomUUID().toString();
        Follower follower = MemberDto.Follow.builder().userId(followerUseId1).userName("follower1").build().toEntity();
        TransactionTemplate transactionTemplate = new TransactionTemplate((PlatformTransactionManager) transactionManager);
        transactionTemplate.execute(status -> {
            Optional<MemberAggregate> saved = memberRepository.findByUserId(useId1);
            MemberAggregate s = saved.get();
            s.addFollower(follower);
            followerRepository.save(follower);
            return null;
        });

    }
}
