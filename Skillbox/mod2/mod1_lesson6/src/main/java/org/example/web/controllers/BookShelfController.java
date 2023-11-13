package org.example.web.controllers;

import org.apache.log4j.Logger;
import org.example.app.services.BookService;
import org.example.web.dto.Book;
import org.example.web.dto.BookIdToRemove;
import org.example.web.dto.QueryRegexToRemove;
import org.example.web.dto.UploadingFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Validated
@Controller
@RequestMapping(value = "/books")
public class BookShelfController {

    private Logger logger = Logger.getLogger(BookShelfController.class);
    private BookService bookService;

    @Autowired
    public BookShelfController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/shelf")
    public String books(Model model) {
        logger.info("got book shelf");
        model.addAttribute("book", new Book());
        model.addAttribute("bookIdToRemove", new BookIdToRemove());
        model.addAttribute("uploadingFile", new UploadingFile());
        model.addAttribute("queryRegexToRemove", new QueryRegexToRemove());
        model.addAttribute("bookList", bookService.getAllBooks());
        return "book_shelf";
    }

    @PostMapping("/save")
    public String saveBook(@Valid Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", book);
            model.addAttribute("bookIdToRemove", new BookIdToRemove());
            model.addAttribute("uploadingFile", new UploadingFile());
            model.addAttribute("queryRegexToRemove", new QueryRegexToRemove());
            model.addAttribute("bookList", bookService.getAllBooks());
            return "book_shelf";
        } else {
            bookService.saveBook(book);
            logger.info("current repository size: " + bookService.getAllBooks().size());
            return "redirect:/books/shelf";
        }
    }
    @PostMapping("/removeByRegex")
    public String removeByRegex(@Valid QueryRegexToRemove queryRegexToRemove, BindingResult bindingResult, Model model) {
        System.out.println(bindingResult.hasErrors());
        if (bindingResult.hasErrors()){
            model.addAttribute("book", new Book());
            model.addAttribute("bookIdToRemove", new BookIdToRemove());
            model.addAttribute("uploadingFile", new UploadingFile());

//            model.addAttribute("queryRegexToRemove", new QueryRegexToRemove());
            model.addAttribute("bookList", bookService.getAllBooks());
            return "book_shelf";
        }
        else {
            bookService.removeBookByRegex(queryRegexToRemove.getQuery());
            logger.info("current repository size: " + bookService.getAllBooks().size());
            return "redirect:/books/shelf";
        }
    }

    @PostMapping("/remove")
    public String removeBook(@Valid BookIdToRemove bookIdToRemove, BindingResult bindingResult, Model model) {
        System.out.println(bindingResult.hasErrors());

        if (bindingResult.hasErrors()){
            model.addAttribute("book", new Book());
/*
            model.addAttribute("bookIdToRemove", new BookIdToRemove());
*/
            model.addAttribute("uploadingFile", new UploadingFile());
            model.addAttribute("queryRegexToRemove", new QueryRegexToRemove());
            model.addAttribute("bookList", bookService.getAllBooks());
            return "book_shelf";
        }
        else {
            bookService.removeBookById(bookIdToRemove.getId());
            return "redirect:/books/shelf";
        }
    }
    @PostMapping("/uploadFile")
    public String uploadFile(@Valid UploadingFile uploadingFile, BindingResult bindingResult, Model model) throws Exception{
        if(!bindingResult.hasErrors()){
            MultipartFile file = uploadingFile.getFile();
            String name = file.getOriginalFilename();
            System.out.println(file);
            byte[] bytes = file.getBytes();
            String rootPath = System.getProperty("catalina.home");
            File dir = new File(rootPath+File.separator+ "external_uploads");
            if (dir.exists()){
                dir.mkdirs();
            }
            File serverFile = new File(dir.getAbsolutePath()+File.separator+name);
            BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream((serverFile)));
            stream.write(bytes);
            stream.close();
            logger.info("new file saved aat:" +serverFile.getAbsolutePath());
            return "redirect:/books/shelf";
        }
        else {
            model.addAttribute("book", new Book());

            model.addAttribute("bookIdToRemove", new BookIdToRemove());

//            model.addAttribute("uploadingFile", new UploadingFile());
            model.addAttribute("queryRegexToRemove", new QueryRegexToRemove());
            model.addAttribute("bookList", bookService.getAllBooks());
            return "book_shelf";
        }
    }
//    @PostMapping("/uploadFile")
//    public String uploadFile(@RequestParam("file") @NotNull MultipartFile file) throws Exception{
//        String name = file.getOriginalFilename();
//        System.out.println(file);
//        byte[] bytes = file.getBytes();
//        String rootPath = System.getProperty("catalina.home");
//        File dir = new File(rootPath+File.separator+ "external_uploads");
//        if (dir.exists()){
//            dir.mkdirs();
//        }
//        File serverFile = new File(dir.getAbsolutePath()+File.separator+name);
//        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream((serverFile)));
//        stream.write(bytes);
//        stream.close();
//        logger.info("new file saved aat:" +serverFile.getAbsolutePath());
//        return "redirect:/books/shelf";
//    }
}
