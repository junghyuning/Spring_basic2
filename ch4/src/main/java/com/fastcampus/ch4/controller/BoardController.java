package com.fastcampus.ch4.controller;

import com.fastcampus.ch4.domain.*;
import com.fastcampus.ch4.service.*;
import oracle.jdbc.proxy.annotation.Post;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;
import org.springframework.ui.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.*;

import javax.servlet.http.*;
import java.time.*;
import java.util.*;

@Controller
@RequestMapping("/board")
public class BoardController {
    @Autowired
    BoardService boardService;

    @GetMapping("/write")
    public String write(Model m) {
        m.addAttribute("mode", "new");
        return "board"; //읽기 & 쓰기 => 쓰기 == new
    }

    @PostMapping("/write")
    public String write(BoardDto boardDto, Model m, HttpSession session, RedirectAttributes rattr) {
        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer);
        try {
            int rowCnt = boardService.write(boardDto);
            if (rowCnt != 1) {
                throw new Exception("Write failed");
            }
            /*세션을 이용한 1회성 저장*/
            rattr.addFlashAttribute("msg", "WRT_OK");
            /*가장 최신 페이지로 return*/
            return "redirect:/board/list";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("boardDto", boardDto);
            rattr.addFlashAttribute("msg", "WRT_ERR");
            return "board";
        }
    }

    @PostMapping("/modify")
    public String modify(BoardDto boardDto, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr) {
        String writer = (String) session.getAttribute("id");
        boardDto.setWriter(writer);
        try {
            int rowCnt = boardService.modify(boardDto);
            if (rowCnt != 1) {
                throw new Exception("Modify failed");
            }
            /*세션을 이용한 1회성 저장*/
            rattr.addFlashAttribute("msg", "MOD_OK");
            /*기존 페이지로 return*/
            rattr.addFlashAttribute("page", page);
            rattr.addFlashAttribute("pageSize", pageSize);
        return "redirect:/board/list";
        } catch (Exception e) {
            e.printStackTrace();
            m.addAttribute("boardDto", boardDto);
            rattr.addFlashAttribute("msg", "MOD_ERR");
            return "board";
        }
    }

    @PostMapping("/remove")
    public String remove(Integer bno, Integer page, Integer pageSize, Model m, HttpSession session, RedirectAttributes rattr) {
        String writer = (String) session.getAttribute("id");
        try {
            /*m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);*/
            rattr.addFlashAttribute("page", page);
            rattr.addFlashAttribute("pageSize", pageSize);
            int rowCnt = boardService.remove(bno, writer);
            if (rowCnt != 1)
                throw new Exception("board remove error");
            /*redirectAttributes 사용시, param을 통해전달된 값이 리로드시 사라짐 => 즉, 한번만 적용 가능*/
            rattr.addFlashAttribute("msg", "DEL_OK");
            return "redirect:/board/list";

        } catch (Exception e) {
            e.printStackTrace();
            rattr.addFlashAttribute("msg", "DEL_ERROR");
            return "boardList";
        }
    }

    @GetMapping("/read")
    public String read(Integer bno, Integer page, Integer pageSize, Model m) {
        try {
            BoardDto boardDto = boardService.read(bno);
//            m.addAttribute("boardDto",boardDto); // 이름 생략시, 타입의 첫글자를 소문자로 변환한것과 동일하게 이름 부여함
            m.addAttribute(boardDto);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "board";
    }

    @GetMapping("/list")
    //*int 대신 Integer 객체를 타입으로 할 경우, 쿼리스트링에 매개변수를 주지 않아도 기본 초기화를 진행하므로 에러가 발생하지 않음*//*
    public String list(Integer page, Integer pageSize, Model m, HttpServletRequest request) {
        if (!loginCheck(request))
            return "redirect:/login/login?toURL=" + request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동
        if (page == null) page = 1;
        if (pageSize == null) pageSize = 20;
        try {

            int totalCnt = boardService.getCount();
            PageHandler pageHandler = new PageHandler(totalCnt, page, pageSize);
            Map map = new HashMap();
            map.put("pageStart", (page - 1) * pageSize + 1);
            map.put("pageEnd", page * pageSize);

            List<BoardDto> list = boardService.getPage(map);
            m.addAttribute("list", list);
//          JSP에 페이지 헨들러 정보 전송
            m.addAttribute("ph", pageHandler);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
    }
    @GetMapping("/list")
    //*int 대신 Integer 객체를 타입으로 할 경우, 쿼리스트링에 매개변수를 주지 않아도 기본 초기화를 진행하므로 에러가 발생하지 않음*//*
    public String list(SearchCondition sc, Model m, HttpServletRequest request) {
        if (!loginCheck(request))
            return "redirect:/login/login?toURL=" + request.getRequestURL();  // 로그인을 안했으면 로그인 화면으로 이동
        try {

            int totalCnt = boardService.getSearchResultCnt(sc);
            PageHandler pageHandler = new PageHandler(totalCnt, sc);
            List<BoardDto> list = boardService.getSearchResultPage(sc);
            m.addAttribute("list", list);
//          JSP에 페이지 헨들러 정보 전송
            m.addAttribute("ph", pageHandler);
            m.addAttribute("page", page);
            m.addAttribute("pageSize", pageSize);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "boardList"; // 로그인을 한 상태이면, 게시판 화면으로 이동
    }

    private boolean loginCheck(HttpServletRequest request) {
        // 1. 세션을 얻어서
        HttpSession session = request.getSession();
        // 2. 세션에 id가 있는지 확인, 있으면 true를 반환
        return session.getAttribute("id") != null;
    }
}