package com.huce.thuvien.controller;

import com.huce.thuvien.entity.Sach;
import com.huce.thuvien.entity.ThuThu;
import com.huce.thuvien.service.BookService;

import java.util.List;

import com.huce.thuvien.service.LoginService;
import com.huce.thuvien.service.ThuThuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class BookController {
    private final BookService bookService;
    public final ThuThuService thuThuService;
    @Autowired
    public BookController(BookService bookService, ThuThuService thuThuService) {
        this.bookService = bookService;
        this.thuThuService = thuThuService;
    }

    @RequestMapping(value = "/thuthu/add-book", method = RequestMethod.GET)
    public String addBook(@RequestParam("name")String name, Model model) {
        ThuThu thuThu = thuThuService.findByName(name);
        model.addAttribute("thuThu", thuThu);
        return "thuthu/form-book";
    }

    @RequestMapping(value = "/thuthu/add-book", method = RequestMethod.POST)
    public String submitBook(@RequestParam("hoTenTT") String name,
                             @RequestParam("tensach")String tensach,
                             @RequestParam("tacgia")String tacgia,
                             @RequestParam("theloai")String theloai,
                             @RequestParam("soluong")int soluong,
                             RedirectAttributes model) {
        Sach sach=new Sach(tensach,tacgia,theloai,soluong);
        bookService.createBook(sach);
        model.addAttribute("name", name);
        return "redirect:/thuthu/list-book";
    }

    @RequestMapping(value = "/thuthu/list-book", method = RequestMethod.GET)
    public String listBookTT(Model model, @RequestParam("name") String name) {
        final List<Sach> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("name", name);
        return "thuthu/list-book";
    }
    @RequestMapping(value = "/docgia/list-book", method = RequestMethod.GET)
    public String listBookDG(@RequestParam("name") String name, Model model) {
        final List<Sach> books = bookService.findAllBooks();
        model.addAttribute("books", books);
        model.addAttribute("name", name);
        return "docgia/list-book";
    }

    @RequestMapping("thuthu/searchBook")
    public String searchBook(@RequestParam("keyword") String keyword, Model model) {
        final List<Sach> books = bookService.searchBooks(keyword);
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        return "thuthu/list-book";
    }
    @RequestMapping(value = "docgia/searchBook")
    public String searchBookDG(@RequestParam("keyword") String keyword,
                               @RequestParam("name") String name, Model model) {
        final List<Sach> books = bookService.searchBooks(keyword);
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        model.addAttribute("name",name);
        return "docgia/list-book";
    }
}
