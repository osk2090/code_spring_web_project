package com.osk2090.controller;

import com.osk2090.domain.BoardVO;
import com.osk2090.domain.Criteria;
import com.osk2090.domain.PageDTO;
import com.osk2090.service.BoardService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@Log4j
@RequestMapping("/board/")
@AllArgsConstructor
public class BoardController {
    private BoardService service;

//    @GetMapping("/list")
//    public void list(Model model) {
//        log.info("list");
//        model.addAttribute("list", service.getList());
//    }

    @GetMapping("/list")
    public void list(Criteria cri, Model model) {
        log.info("list: " + cri);
        model.addAttribute("list", service.getList(cri));
//        model.addAttribute("pageMaker", new PageDTO(cri, 123));
        int total = service.getTotalCount(cri);
        log.info("total: " + total);
        model.addAttribute("pageMaker", new PageDTO(cri, total));
    }

    @PostMapping("/register")
    public String register(BoardVO board, RedirectAttributes rttr) {
        log.info("register: " + board);
        service.register(board);
        rttr.addFlashAttribute("result", board.getBno());
        return "redirect:/board/list";
    }

    @GetMapping({"/get", "/modify"})
    public void get(@RequestParam("bno") Long bno,
                    @ModelAttribute("cri") Criteria cri, Model model) {
        log.info("/get or modify");
        model.addAttribute("board", service.get(bno));
    }

    @PostMapping("/modify")
    public String modify(BoardVO board, @ModelAttribute("cri") Criteria cri,
                         RedirectAttributes rttr) {
        log.info("modify:" + board);

        if (service.modify(board)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list" + cri.getListLink();
    }

    @PostMapping("/remove")
    public String remove(@RequestParam("bno") Long bno, @ModelAttribute("cri") Criteria cri,
                         RedirectAttributes rttr) {
        log.info("remove..." + bno);
        if (service.remove(bno)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/board/list" + cri.getListLink();
    }

    @GetMapping("/register")
    public void register() {

    }
}
