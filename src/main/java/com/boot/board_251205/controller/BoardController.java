package com.boot.board_251205.controller;

import com.boot.board_251205.model.Board;
import com.boot.board_251205.repository.BoardRepository;
import com.boot.board_251205.validator.BoardValidator;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
//    public String list(Model model){
//    public String list(Model model, Pageable pageable){
//    public String list(Model model, @PageableDefault(size = 2) Pageable pageable){
//    public String list(Model model, @PageableDefault(size = 2) Pageable pageable, @RequestParam() String searchText){
    public String list(Model model, @PageableDefault(size = 2) Pageable pageable, @RequestParam(required = false, defaultValue = "") String searchText){
//        List<Board> boards = boardRepository.findAll();
//        Page<Board> boards = boardRepository.findAll(PageRequest.of(1, 20));
//        Page<Board> boards = boardRepository.findAll(PageRequest.of(0, 20));
//        Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);

//        int startPage = boards.getPageable().getPageNumber()-4;
//        int endPage = boards.getPageable().getPageNumber()+4;
//        int startPage = Math.max(0,boards.getPageable().getPageNumber()-4);
        int startPage = Math.max(1,boards.getPageable().getPageNumber()-4);
        int endPage = Math.min(boards.getTotalPages(),boards.getPageable().getPageNumber()+4);

        model.addAttribute("boards",boards);
        model.addAttribute("startPage",startPage);
        model.addAttribute("endPage",endPage);

        log.info("@# boards=>"+boards);
        log.info("@# startPage=>"+startPage);
        log.info("@# endPage=>"+endPage);
        log.info("@# searchText=>"+searchText);

        return "board/list";
    }
    @GetMapping("/form")
//    public String form(Model model){
//    public String form(Model model, @RequestParam Long id){
    public String form(Model model, @RequestParam(required = false) Long id){
//        model.addAttribute("board", new Board());
        log.info("@# GetMapping form()");
        log.info("@# id=>"+id);

        if (id == null){
            model.addAttribute("board", new Board());
        }else {
//            Optional<Board> board = boardRepository.findById(id);
            Board board = boardRepository.findById(id).orElse(null);
            log.info("@# board=>"+board);

            model.addAttribute("board",board);
        }
        return "board/form";
    }
    @PostMapping("/form")
//    public String form(@ModelAttribute Board board, Model model) {
    public String form(@Valid Board board, BindingResult bindingResult) {
        log.info("@# PostMapping form()");

        boardValidator.validate(board, bindingResult);

        if (bindingResult.hasErrors()) {
            return "board/form";
        }

        boardRepository.save(board);

//        return "result";
        return "redirect:/board/list";
    }

}
