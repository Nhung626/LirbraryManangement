package com.huce.thuvien.service.Imp;

import com.huce.thuvien.entity.ThuThu;
import com.huce.thuvien.repository.ThuThuRepository;
import com.huce.thuvien.service.ThuThuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThuThuServiceImp implements ThuThuService {
    @Autowired private ThuThuRepository thuThuRepository;
    @Override
    public ThuThu findByName(String name) {
        return thuThuRepository.findByHoTenTT(name);
    }
}
