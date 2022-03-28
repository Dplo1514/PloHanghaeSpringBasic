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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        List<Comment> getComment = commentRepository.findByBoard_idOrderByModifiedAtDesc(id);
        model.addAttribute("li", getBoard);
        model.addAttribute("comment", getComment);
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

    @ResponseBody
    @RequestMapping("/userCommentCheck")
    public Map<String , String> userCommentCheck(@RequestParam(value = "idx" , defaultValue = "0") long id , Principal principal , Model model){
        //comment도 리턴해주고 , user도 리턴해줘야한다.
        List<Comment> getComment = commentRepository.findByBoard_idOrderByModifiedAtDesc(id);
        Map<String , String> result = new HashMap<String , String>();

        System.out.println(result);
        return result;
    }
}


















