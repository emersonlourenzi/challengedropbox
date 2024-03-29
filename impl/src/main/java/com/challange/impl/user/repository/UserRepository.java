package com.challange.impl.user.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserEntity, String> {
    Optional<UserEntity> findById(String id);

    @Override
    <S extends UserEntity> S save(S entity);

    @Override
    void deleteById(String s);

}
