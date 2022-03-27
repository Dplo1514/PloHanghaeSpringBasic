package com.plo.ploboardproject.controller;

import com.plo.ploboardproject.domain.Board;
import com.plo.ploboardproject.domain.Comment;
import com.plo.ploboardproject.dto.CommentRequestDto;
import com.plo.ploboardproject.service.BoardRepository;
import com.plo.ploboardproject.dto.BoardRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardRepository boardRepository;
    BoardRequestDto requestDto;
    CommentRequestDto commentRequestDto;

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
    public String boardView(@RequestParam(value = "idx" , defaultValue = "0") long idx ,Model model) {
        Board getBoard = boardRepository.getById(idx);
        model.addAttribute("li", getBoard);
        return "board_view";
    }

}

















