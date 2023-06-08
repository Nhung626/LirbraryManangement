package com.huce.thuvien.repository;

import com.huce.thuvien.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByEmail(String email);
}
