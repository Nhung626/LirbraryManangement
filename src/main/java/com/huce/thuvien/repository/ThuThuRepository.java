package com.huce.thuvien.repository;

import com.huce.thuvien.entity.Account;
import com.huce.thuvien.entity.ThuThu;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThuThuRepository extends JpaRepository<ThuThu,Long> {
    ThuThu findByAccount(Account acc);
    ThuThu findByHoTenTT(String hoTenTT);
}
