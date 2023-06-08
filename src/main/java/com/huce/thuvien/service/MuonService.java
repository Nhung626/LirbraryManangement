package com.huce.thuvien.service;

import com.huce.thuvien.entity.*;

import java.util.List;
import java.util.Set;

public interface MuonService {
    List<PhieuMuon> findAllPMS();
    List<PhieuPH> findAllPHS();
    void createPH(Long maPM, String hoTenTT);
    void updatePhieuMuon(Long id,String trangThai);
    void updatePhieuPH(Long id,String trangThai);
    void addBookToGio(String hoTenDG, Long id);
    void delBookGio(DocGia docGia, Set<Sach> books);

    void addPhieuMuon(String docGia, Set<Sach> books);
    GioSach findByDocGia(DocGia docGia);
    void delPhieuMuon(Long maPM);
}
