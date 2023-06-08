package com.huce.thuvien.service;
import com.huce.thuvien.entity.Sach;

import java.util.List;

 public interface BookService {
     List<Sach> findAllBooks();

     List<Sach> searchBooks(String keyword);

     Sach findBookById(Long id);

     void createBook(Sach book);

     void updateBook(Sach book);

     void deleteBook(Long id);
}
