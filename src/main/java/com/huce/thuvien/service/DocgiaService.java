package com.huce.thuvien.service;
import com.huce.thuvien.entity.Account;
import com.huce.thuvien.entity.DocGia;
import com.huce.thuvien.entity.GioSach;

import java.util.List;
public interface DocgiaService {
    void createDocGia(Account acc, DocGia docGia, GioSach gioSach);
    DocGia  findByName(String name);
}
