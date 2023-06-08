package com.huce.thuvien.controller;

import com.huce.thuvien.entity.DocGia;
import com.huce.thuvien.entity.GioSach;
import com.huce.thuvien.entity.Sach;
import com.huce.thuvien.repository.GioSachRepository;
import com.huce.thuvien.service.BookService;
import com.huce.thuvien.service.DocgiaService;
import com.huce.thuvien.service.MuonService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;

@Controller
public class PhieuMuonController {
    private final MuonService muonService;
    private final DocgiaService docgiaService;
    private final BookService bookService;


    @Autowired
    public PhieuMuonController(MuonService muonService, DocgiaService docgiaService, BookService bookService) {
        this.muonService = muonService;
        this.docgiaService = docgiaService;
        this.bookService = bookService;
    }

    @RequestMapping(value = "/docgia/list-pm", method = RequestMethod.GET)
    public String listPMuonDG(@RequestParam("name") String name, Model model) {
        model.addAttribute("phieumuon", muonService.findAllPMS());
        model.addAttribute("name", name);
        return "docgia/list-pm";
    }

    @RequestMapping(value = "/thuthu/list-pm", method = RequestMethod.GET)
    public String listPMuonTT(Model model, @RequestParam("name") String name) {
        model.addAttribute("phieumuon", muonService.findAllPMS());
        model.addAttribute("name", name);
        return "thuthu/list-pm";
    }

    @RequestMapping(value = "/thuthu/list-pt", method = RequestMethod.GET)
    public String listPH(Model model, @RequestParam("name") String name) {
        model.addAttribute("name", name);
        model.addAttribute("phieuph", muonService.findAllPHS());
        return "thuthu/list-pt";
    }

    @RequestMapping(value = "/thuthu/phanhoi", method = RequestMethod.GET)
    public String phanHoi(RedirectAttributes model, @RequestParam("tenTT") String name, @RequestParam("maPM") Long ma) {
        muonService.createPH(ma, name);
        model.addAttribute("name", name);
        return "redirect:/thuthu/list-pt";
    }

    @RequestMapping(value = "/docgia/muon", method = RequestMethod.GET)
    public String viewPMuon(@RequestParam("name") String name, Model model) {
        DocGia docGia = docgiaService.findByName(name);
        GioSach gioSach = muonService.findByDocGia(docGia);
        model.addAttribute("docgia", docGia);
        model.addAttribute("books", gioSach.getBooks());
        return "docgia/form-pm";
    }

    @RequestMapping(value = "/docgia/muon", method = RequestMethod.POST)
    public ResponseEntity addPMuon(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        String hoTenDG = request.getParameter("hoTenDG");
        // Lấy danh sách đối tượng từ formdata
        Set<Sach> books = new HashSet<>();
        Long sachID;
        int i = 0;
        while (request.getParameter("sach_" + i) != null) {
            sachID = Long.parseLong(request.getParameter("sach_" + i));
            books.add(bookService.findBookById(sachID));
            i++;
        }
        muonService.addPhieuMuon(hoTenDG,books);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "/docgia/add-gio", method = RequestMethod.GET)
    public String addBookToPM(@RequestParam("name") String name, @RequestParam Long id, RedirectAttributes redirectAttributes) {
        muonService.addBookToGio(name, id);
        redirectAttributes.addAttribute("name", name);
        return "redirect:/docgia/list-book";
    }

    @RequestMapping(value = "/thuthu/muon", method = RequestMethod.GET)
    public String updateMuon(@RequestParam("maPH") Long maPH,@RequestParam("tenTT") String name, RedirectAttributes model){
        muonService.updatePhieuMuon(maPH,"Đã mượn");
        model.addAttribute("name",name);
        return "redirect:/thuthu/list-pt";
    }
    @RequestMapping(value = "/thuthu/tra", method = RequestMethod.GET)
    public String updateTra(@RequestParam("maPH") Long maPH,@RequestParam("tenTT") String name, RedirectAttributes model){
        muonService.updatePhieuMuon(maPH,"Đã trả");
        model.addAttribute("name",name);
        return "redirect:/thuthu/list-pt";
    }
    @RequestMapping(value = "/docgia/delPM")
    public String deletePM(@RequestParam("maPM") Long maPM, @RequestParam("name") String name,RedirectAttributes model){
        muonService.delPhieuMuon(maPM);
        model.addAttribute("name", name);
        return "redirect:/docgia/list-pm";
    }
}
