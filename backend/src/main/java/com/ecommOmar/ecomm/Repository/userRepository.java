package com.ecommOmar.ecomm.Repository;


import com.ecommOmar.ecomm.Entity.User;
import com.ecommOmar.ecomm.Enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface userRepository extends JpaRepository<User, Long> {
	Optional<User> findFirstByEmail(String username);


	User findByRole(UserRole userRole);
}
