package com.huce.thuvien.service;

import com.huce.thuvien.entity.Account;
import com.huce.thuvien.entity.DocGia;
import com.huce.thuvien.entity.ThuThu;

public interface LoginService {
    boolean login(String email, String pass);
    DocGia loginDocGia(String email, String pass);
    ThuThu loginThuThu(String email, String pass);
}
