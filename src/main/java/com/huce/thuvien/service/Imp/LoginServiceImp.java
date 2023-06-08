package com.huce.thuvien.service.Imp;

import com.huce.thuvien.entity.Account;
import com.huce.thuvien.entity.DocGia;
import com.huce.thuvien.entity.ThuThu;
import com.huce.thuvien.repository.AccountRepository;
import com.huce.thuvien.repository.DocGiaRepository;
import com.huce.thuvien.repository.ThuThuRepository;
import com.huce.thuvien.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImp implements LoginService {
    private AccountRepository accountRepository;
    private DocGiaRepository docGiaRepository;
    private ThuThuRepository thuThuRepository;

    @Autowired
    public LoginServiceImp(AccountRepository accountRepository, DocGiaRepository docGiaRepository, ThuThuRepository thuThuRepository) {
        this.accountRepository = accountRepository;
        this.docGiaRepository = docGiaRepository;
        this.thuThuRepository = thuThuRepository;
    }

    @Override
    public boolean login(String email, String pass) {
        Account account = accountRepository.findByEmail(email);
        if (account != null && account.getPassword().equals(pass)) {
            return true;
        }
        return false;
    }
    @Override
    public DocGia loginDocGia(String email, String pass) {
        Account acc = accountRepository.findByEmail(email);
        return docGiaRepository.findByAccount(acc);
    }
    @Override
    public ThuThu loginThuThu(String email, String pass) {
        Account acc = accountRepository.findByEmail(email);
        return thuThuRepository.findByAccount(acc);
    }
}
