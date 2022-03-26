package com.plo.ploboardproject.controller;

import com.plo.ploboardproject.domain.BoardWrite;
import com.plo.ploboardproject.service.BoardRepository;
import com.plo.ploboardproject.dto.BoardWriteRequestDto;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Getter
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardRepository boardRepository;
    BoardWriteRequestDto requestDto;


    @GetMapping("/main")
    public String boards(Model model) {
        List<BoardWrite> boardList = boardRepository.findAllByOrderByModifiedAtDesc();
        model.addAttribute("list", boardList);
        return "board";
    }

    @GetMapping("/postPage")
    public String boardWrite(){
        return "board_write";
    }

    @PostMapping("/post")
    public String boardWrite(BoardWriteRequestDto requestDto){
        BoardWrite board = new BoardWrite(requestDto);
        boardRepository.save(board);
        return "redirect:/board/main";
    }

    @GetMapping("/boardView")
    public String boardView(@RequestParam(value = "idx" , defaultValue = "0") long idx ,Model model) {
        BoardWrite test = boardRepository.getById(idx);
        System.out.println(test);
        model.addAttribute("li", test);
        return "board_view";
    }
}
