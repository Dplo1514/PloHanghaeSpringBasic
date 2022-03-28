package com.plo.ploboardproject.controller;

import com.plo.ploboardproject.domain.Board;
import com.plo.ploboardproject.domain.Comment;
import com.plo.ploboardproject.service.BoardRepository;
import com.plo.ploboardproject.dto.BoardRequestDto;
import com.plo.ploboardproject.service.BoardService;
import com.plo.ploboardproject.service.CommentRepository;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Getter
@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardRepository boardRepository;
    private final BoardService boardService;
    private final CommentRepository commentRepository;

    public BoardController(BoardRepository boardRepository, BoardService boardService, CommentRepository commentRepository) {
        this.boardRepository = boardRepository;
        this.boardService = boardService;
        this.commentRepository = commentRepository;
    }

    @GetMapping("/main")
    public String boards(Model model) {
        List<Board> boardList = boardRepository.findAllByOrderByModifiedAtDesc();
        model.addAttribute("list", boardList);
        return "board";
    }

    @GetMapping("/postPage")
    public String boardWrite(){
        return "board_write";
    }

    @PostMapping("/post")
    public String boardWrite(BoardRequestDto requestDto){
        Board board = new Board(requestDto);
        boardRepository.save(board);
        return "redirect:/board/main";
    }

    @GetMapping("/boardView")
    public String boardView(@RequestParam(value = "idx" , defaultValue = "0") long id ,Model model) {
        Board getBoard = boardRepository.getById(id);
        Comment getComment = commentRepository.getById(id);
        model.addAttribute("li", getBoard);
        return "board_view";
    }


    @PostMapping("/boardView")
    public String commentPost(@RequestParam(value = "idx" , defaultValue = "0") long idx , @RequestParam(name = "comment") String comment ,Principal principal , Model model) {
        String name = principal.getName();
        String comments = comment;
        System.out.println(name);
        System.out.println(comments);
        System.out.println(idx);
        boardService.CommentSave(name , comments , idx);
        return "redirect:/board/main";
    }
}

















