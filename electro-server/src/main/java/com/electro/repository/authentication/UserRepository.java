package com.electro.repository.authentication;

import com.electro.entity.authentication.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsUserByUsername(String username);

    boolean existsUserByEmail(String email);

    Optional<User> findByEmailAndResetPasswordToken(String email, String resetPasswordToken);

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.customer c LEFT JOIN FETCH c.customerGroup cg WHERE u.id = :id")
    Optional<User> findByIdWithDetails(@Param("id") Long id);
}
