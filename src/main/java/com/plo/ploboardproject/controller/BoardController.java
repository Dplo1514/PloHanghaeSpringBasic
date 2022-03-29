package com.plo.ploboardproject.controller;

import com.plo.ploboardproject.domain.Board;
import com.plo.ploboardproject.domain.Comment;
import com.plo.ploboardproject.dto.CommentRequestDto;
import com.plo.ploboardproject.service.BoardRepository;
import com.plo.ploboardproject.dto.BoardRequestDto;
import com.plo.ploboardproject.service.BoardService;
import com.plo.ploboardproject.service.CommentRepository;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.security.Principal;
import java.util.*;

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
    public String boardWrite(Principal principal){
        try {
            principal.getName();
        }catch (NullPointerException e){
            return "redirect:/";
        }
        return "board_write";
    }


    @PostMapping("/post")
    public String boardWrite(@RequestParam(name = "title") String title,
                             @RequestParam(name = "contents") String contents ,
                             Principal principal,Model model){
        String name = principal.getName();
        String titles = title;
        String content = contents;
        try {
            boardService.BoardSave( name ,titles , content);
        } catch (IllegalArgumentException e){
            String warning = "게시글 내용 및 제목을 입력해주세요";
            model.addAttribute("warning", warning);
            return "board_write";
        }
        return "redirect:/board/main";
    }

    @GetMapping("/boardView")
    public String boardView(@RequestParam(value = "idx" , defaultValue = "0") long id ,Model model, Principal principal) {
        Board getBoard = boardRepository.getById(id);
        List<Comment> getComment = commentRepository.findByBoard_idOrderByModifiedAtDesc(id);
        try {
            String user = principal.getName();
            model.addAttribute("user" , user);
            model.addAttribute("li", getBoard);
            model.addAttribute("comment", getComment);
        }catch (NullPointerException e){
            model.addAttribute("li", getBoard);
            model.addAttribute("comment", getComment);
        }
        return "board_view";
    }


    @PostMapping("/boardView")
    public String commentPost(@RequestParam(value = "idx" , defaultValue = "0") long idx , @RequestParam(name = "comment") String comment , Principal principal, RedirectAttributes redirectAttributes) {
        String comments = comment;
        try {
            String name = principal.getName();
            boardService.CommentSave(name, comments, idx);
        }   catch (NullPointerException e) {
            String warning = "로그인 후 이용 가능한 기능입니다";
            redirectAttributes.addFlashAttribute("warning", warning);
            return "redirect:/board/boardView?idx=" + idx;
        } catch (IllegalArgumentException e){
            String warning = "댓글 내용을 입력해주세요";
            redirectAttributes.addFlashAttribute("warning", warning);
            return "redirect:/board/boardView?idx=" + idx;
        }
        return "redirect:/board/boardView?idx=" + idx;
    }

    @ResponseBody
    @RequestMapping("/userCheck")
    public Map<String , String> userCheck (Principal principal){
        Map<String , String> result = new HashMap<String , String>();
        try {
            String userCheck = principal.getName();
            result.put("user" , userCheck);
        }catch (NullPointerException e){
            result.put("user" , null);
            return result;
        }
        System.out.println(result);
        return result;
    }

    @PostMapping("/commentUpdate/{idx}")
    public String commentUpdate(@PathVariable long idx ,
                                @RequestBody HashMap<String, Object> map) {
        String comment = (String) map.get("commentUpdate");
        String commentId = (String) map.get("commentId");
        long id = Long.parseLong(commentId);
        boardService.update(id , comment);

        return "redirect:/board/boardView?idx=" + idx;
    }

}


















