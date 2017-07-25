package com.aungmyohtet.pm.web.update;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Card;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.service.update.BoardService;
import com.aungmyohtet.pm.service.update.OrganizationService;

@Controller
public class UpdatedBoardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private OrganizationService organizationService;

    @GetMapping(value = "/u/{organizationName}/boards/{boardName}")
    private String showBoardMenu(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("boardName") String boardName) {
        return "redirect:/u/" + organizationName + "/boards/" + boardName + "/cards";
    }

    @GetMapping(value = "/u/{organizationName}/boards/{boardName}/cards")
    public String showCards(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("boardName") String boardName) {
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("boardName", boardName);
        Organization organization = organizationService.findByName(organizationName);
        Board board = boardService.findByNameAndOrganization(boardName, organization);
        List<Card> cards = boardService.getCards(board);
        model.addAttribute("cards", cards);
        return "card/cards";
    }

    @GetMapping(value = "/u/{organizationName}/boards/new")
    public String showBoardForm(Model model, @PathVariable("organizationName") String organizationName) {
        model.addAttribute("board", new Board());
        model.addAttribute("organizationName", organizationName);
        return "board/form";
    }

    @PostMapping(value = "/u/{organizationName}/boards/new")
    public String addBoard(@Validated @ModelAttribute Board board, BindingResult result, Model model, @PathVariable("organizationName") String organizationName) {
        if (result.hasErrors()) {
            return "board/form";
        }
        Organization organization = organizationService.findByName(organizationName);
        organizationService.addBoard(organization, board);
        return "redirect:/u/" + organizationName + "/boards";
    }

}
