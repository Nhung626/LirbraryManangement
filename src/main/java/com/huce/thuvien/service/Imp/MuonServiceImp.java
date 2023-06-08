package com.huce.thuvien.service.Imp;

import com.huce.thuvien.entity.*;
import com.huce.thuvien.repository.*;
import com.huce.thuvien.service.MuonService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Service
public class MuonServiceImp implements MuonService {
    @Autowired
    private PhieuMuonRepository phieuMuonRepository;
    @Autowired
    private PhieuPHRepository phieuPHRepository;
    @Autowired
    private DocGiaRepository docGiaRepository;
    @Autowired
    private GioSachRepository gioSachRepository;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ThuThuRepository thuThuRepository;

    @Override
    public List<PhieuMuon> findAllPMS() {
        return phieuMuonRepository.findAll();
    }

    @Override
    public List<PhieuPH> findAllPHS() {
        return phieuPHRepository.findAll();
    }

    @Override
    public void createPH(Long maPM, String hoTenTT) {
        PhieuMuon pm = phieuMuonRepository.findByMaPM(maPM);
        ThuThu thuthu = thuThuRepository.findByHoTenTT(hoTenTT);
        PhieuPH ph = new PhieuPH(thuthu, pm);
        phieuPHRepository.save(ph);
    }


    @Override
    public void updatePhieuMuon(Long id, String trangThai) {
        PhieuPH ph = phieuPHRepository.findByMaPT(id);
        if (ph.getTrangThai().equals("Đã mượn") && trangThai.equals("Đã trả")) {
            ph.setTrangThai(trangThai);
            ph.getPhieuMuon().setTrangThai("Đã trả");
            ph.setNgayTra(LocalDateTime.now());
        }
        if(ph.getTrangThai().equals("Đang đợi") && trangThai.equals("Đã mượn")){
            ph.setTrangThai(trangThai);
            ph.getPhieuMuon().setTrangThai("Đang mượn");
        }
        phieuPHRepository.save(ph);
    }

    @Override
    public void addBookToGio(String hoTenDG, Long id) {
        DocGia docGia = docGiaRepository.findByHoTenDG(hoTenDG);
        Sach sach = bookRepository.findByMaSach(id);
        GioSach gioSach = gioSachRepository.findByDocGia(docGia);

        gioSach.getBooks().add(sach);
        sach.getGiosachDG().add(gioSach);
        gioSachRepository.save(gioSach);

    }

    @Override
    public void delBookGio(DocGia docGia, Set<Sach> books) {
        GioSach gioSach = gioSachRepository.findByDocGia(docGia);
        for (Sach sach : books) {
            gioSach.getBooks().remove(sach);
            sach.getGiosachDG().remove(gioSach);
        }
        gioSachRepository.save(gioSach);
    }

    @Override
    public void addPhieuMuon(String hoTenDG, Set<Sach> books) {
        DocGia docGia = docGiaRepository.findByHoTenDG(hoTenDG);
        PhieuMuon pm = new PhieuMuon(books, docGia);
        for (Sach sach : books) {
            sach.getPhieuMuons().add(pm);
        }
        phieuMuonRepository.save(pm);
        delBookGio(docGia, books);
    }
    public void delPhieuMuon(Long maPM) {
        PhieuMuon pm = phieuMuonRepository.findByMaPM(maPM);
        for (Sach sach : pm.getBooks()) {
            sach.getPhieuMuons().remove(pm);
        }
        phieuMuonRepository.delete(pm);
    }


    @Override
    public GioSach findByDocGia(DocGia docGia) {
        return gioSachRepository.findByDocGia(docGia);
    }

    @Override
    public void updatePhieuPH(Long id, String trangThai) {
        PhieuPH phieuPH = phieuPHRepository.findByMaPT(id);
        phieuPH.setTrangThai(trangThai);
    }
}
