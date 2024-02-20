package com.example.springboot3jpa.infrastructure.repositorty;

import com.example.springboot3jpa.domain.member.MemberAggregate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MemberRepository extends JpaRepository<MemberAggregate, Long> { // MemberAggregate: mapping class , Integer : primary key

    public Boolean existsByUserId(String userId);

    public Optional<MemberAggregate> findById(long id);
    public Optional<MemberAggregate> findByUserId(String userId);

    public List<MemberAggregate> findByUserName(String userName);

    public List<MemberAggregate> findByUserNameAndUserId(String userName, String userId);

}
