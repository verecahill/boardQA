package app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import app.dto.Account;

public interface UserRepository extends JpaRepository<Account, Long>{
	Account findByUserId(String userId);
}
