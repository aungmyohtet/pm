package com.aungmyohtet.pm.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.service.BoardService;

@Controller
public class BoardController {

    @Autowired
    private BoardService boardService;

    public void setBoardService(BoardService boardService) {
        this.boardService = boardService;
    }

    @ModelAttribute("module")
    String module() {
        return "boards";
    }

    @RequestMapping(value = "/{organizationName}/boards/new", method = RequestMethod.GET)
    public String showBoardForm(Model model, @PathVariable("organizationName") String organizationName) {
        model.addAttribute("board", new Board());
        model.addAttribute("organizationName", organizationName);
        return "boardForm";
    }

    @RequestMapping(value = "{organizationName}/boards/new", method = RequestMethod.POST)
    public String addBoard(@Validated @ModelAttribute Board board, BindingResult result, Model model, @PathVariable("organizationName") String organizationName) {

        if (result.hasErrors()) {
            return "boardForm";
        }

        boardService.addBoardToOrganization(board, organizationName);
        return "redirect:/" + organizationName + "/boards";
    }

    @RequestMapping(value = "/{organizationName}/boards/{boardNo}", method = RequestMethod.GET)
    private String showProjectHome(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("boardNo") String boardNo) {
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("boardNo", boardNo);
        return "redirect:/" + organizationName + "/boards/" + boardNo + "/cards";
    }
}
