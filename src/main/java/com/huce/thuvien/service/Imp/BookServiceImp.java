package com.huce.thuvien.service.Imp;

import com.huce.thuvien.entity.Sach;
import com.huce.thuvien.exception.NotFoundException;
import com.huce.thuvien.repository.BookRepository;
import com.huce.thuvien.service.BookService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
@Service
public class BookServiceImp implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImp(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<Sach> findAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public List<Sach> searchBooks(String keyword) {
        if (keyword != null) {
            return bookRepository.search(keyword);
        }
        return bookRepository.findAll();
    }

    @Override
    public Sach findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));
    }

    @Override
    public void createBook(Sach book) {
        bookRepository.save(book);
    }

    @Override
    public void updateBook(Sach book) {
        bookRepository.save(book);
    }

    @Override
    public void deleteBook(Long id) {
        final Sach book = bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));

        bookRepository.deleteById(book.getMaSach());
    }
}
