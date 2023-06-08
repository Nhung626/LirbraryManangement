package com.huce.thuvien.repository;

import com.huce.thuvien.entity.Account;
import com.huce.thuvien.entity.DocGia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocGiaRepository extends JpaRepository<DocGia, Long> {
    DocGia findByAccount(Account acc);
    DocGia findByHoTenDG(String name);

}
