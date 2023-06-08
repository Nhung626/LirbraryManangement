package com.huce.thuvien.controller;

import com.huce.thuvien.entity.*;
import com.huce.thuvien.service.DocgiaService;
import com.huce.thuvien.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;

@Controller
public class LoginController {
    private final DocgiaService docgiaService;
    private final LoginService loginService;

    @Autowired
    public LoginController(DocgiaService docgiaService, LoginService loginService) {
        this.docgiaService = docgiaService;
        this.loginService = loginService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String showHome() {
        return "redirect:/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login1";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(@RequestParam("email") String email,
                            @RequestParam("password") String pass,
                            RedirectAttributes redirectAttributes) {
        if (loginService.login(email, pass)) {
            if(loginService.loginDocGia(email, pass)!=null) {
                DocGia docGia= loginService.loginDocGia(email, pass);
                redirectAttributes.addAttribute("name",docGia.getHoTenDG());
                return "redirect:/docgia/list-book";
            }else {
                ThuThu thuThu = loginService.loginThuThu(email, pass);
                redirectAttributes.addAttribute("name",thuThu.getHoTenTT());
                return "redirect:/thuthu/list-pm";
            }
         }
        return "login1";
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        return "login1";
    }
    @RequestMapping(value = "/sign-in", method = RequestMethod.GET)
    public String signIn() {
        return "sign-in";
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.POST)
    public String addAccount(
            @RequestParam("hoten") String hoten,
            @RequestParam("email") String email,
            @RequestParam("password") String pass,
            @RequestParam("ngaysinh") LocalDate ngaysinh,
            @RequestParam("cccd") String cccd,
            @RequestParam("sdt") String sdt
    ) {
        Account account = new Account(email, pass);
        DocGia docGia = new DocGia(hoten, sdt, ngaysinh, cccd, account);
        GioSach gioSach = new GioSach(docGia);
        docgiaService.createDocGia(account, docGia, gioSach);
        return "redirect:/login";
    }
}
