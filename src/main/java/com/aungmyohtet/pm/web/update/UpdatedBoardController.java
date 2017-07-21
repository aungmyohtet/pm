package com.aungmyohtet.pm.web.update;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Card;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.service.BoardService2;
import com.aungmyohtet.pm.service.OrganizationService2;

@Controller
public class UpdatedBoardController {

    @Autowired
    private BoardService2 boardService;

    @Autowired
    private OrganizationService2 organizationService;

    @GetMapping(value = "/u/{organizationName}/boards/{boardName}")
    private String showBoardMenu(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("boardName") String boardName) {
        return "redirect:/" + organizationName + "/boards/" + boardName + "/cards";
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

}
