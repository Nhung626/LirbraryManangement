package com.huce.thuvien.service.Imp;

import com.huce.thuvien.entity.Account;
import com.huce.thuvien.entity.DocGia;
import com.huce.thuvien.entity.GioSach;
import com.huce.thuvien.repository.AccountRepository;
import com.huce.thuvien.repository.DocGiaRepository;
import com.huce.thuvien.repository.GioSachRepository;
import com.huce.thuvien.service.DocgiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocGiaServiceImp implements DocgiaService {
    private AccountRepository accountRepository;
    private DocGiaRepository docGiaRepository;
    private GioSachRepository gioSachRepository;

    @Autowired
    public DocGiaServiceImp(AccountRepository accountRepository, DocGiaRepository docGiaRepository, GioSachRepository gioSachRepository) {
        this.accountRepository = accountRepository;
        this.docGiaRepository = docGiaRepository;
        this.gioSachRepository = gioSachRepository;
    }

    @Override
    public void createDocGia(Account acc, DocGia docGia, GioSach gioSach) {
        accountRepository.save(acc);
        docGiaRepository.save(docGia);
        gioSachRepository.save(gioSach);
    }

    public DocGia findByName(String name) {
        return docGiaRepository.findByHoTenDG(name);
    }

}
