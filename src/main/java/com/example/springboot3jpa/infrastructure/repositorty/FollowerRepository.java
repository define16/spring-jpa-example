package com.example.springboot3jpa.infrastructure.repositorty;

import com.example.springboot3jpa.domain.member.Follower;
import com.example.springboot3jpa.domain.member.MemberAggregate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FollowerRepository extends JpaRepository<Follower, Long> { // MemberAggregate: mapping class , Integer : primary key

    public Optional<Follower> findById(long id);
    public List<Follower> findByUserId(String userId);

    public List<Follower> findByUserName(String userName);

    public List<Follower> findByUserNameAndUserId(String userName, String userId);

}
