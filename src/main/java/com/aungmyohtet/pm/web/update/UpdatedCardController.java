package com.aungmyohtet.pm.web.update;

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
public class UpdatedCardController {

    @Autowired
    private BoardService boardService;

    @Autowired
    private OrganizationService organizationService;

    @GetMapping(value = "/u/{organizationName}/boards/{boardName}/cards/new")
    public String showCardForm(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("boardName") String boardName) {
        model.addAttribute("card", new Card());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("boardName", boardName);
        return "card/form";
    }

    @PostMapping(value = "/u/{organizationName}/boards/{boardName}/cards/new")
    public String addBoard(@Validated @ModelAttribute Card card, BindingResult result, Model model, @PathVariable("organizationName") String organizationName,
            @PathVariable("boardName") String boardName) {
        if (result.hasErrors()) {
            return "card/form";
        }
        Organization organization = organizationService.findByName(organizationName);
        Board board = boardService.findByNameAndOrganization(boardName, organization);
        boardService.addCard(board, card);
        return "redirect:/u/" + organizationName + "/boards/" + boardName + "/cards";
    }
}
