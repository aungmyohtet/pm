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
import com.aungmyohtet.pm.entity.Card;
import com.aungmyohtet.pm.entity.Status;
import com.aungmyohtet.pm.entity.TaskNote;
import com.aungmyohtet.pm.entity.TechnologyTag;
import com.aungmyohtet.pm.entity.User;
import com.aungmyohtet.pm.service.BoardService;
import com.aungmyohtet.pm.service.CardService;

@Controller
public class CardController {

    @Autowired
    private CardService cardService;

    @Autowired
    private BoardService boardService;

    @RequestMapping(value = "/{organizationName}/boards/{boardName}/cards", method = RequestMethod.GET)
    public String showCards(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("boardName") String boardName) {
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("boardName", boardName);
        model.addAttribute("cards", cardService.find(organizationName, boardName));
        // model.addAttribute("boardName", boardService.findBoardNamebyBoardNoAndOrganizationName(boardNo, organizationName));
        return "cardList";
    }

    @RequestMapping(value = "/{organizationName}/boards/{boardName}/cards/new", method = RequestMethod.GET)
    public String showCardForm(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("boardName") String boardName) {
        model.addAttribute("card", new Card());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("boardName", boardName);
        return "cardForm";
    }

    @RequestMapping(value = "{organizationName}/boards/{boardName}/cards/new", method = RequestMethod.POST)
    public String addBoard(@Validated @ModelAttribute Card card, BindingResult result, Model model, @PathVariable("organizationName") String organizationName,
            @PathVariable("boardName") String boardName) {

        if (result.hasErrors()) {
            return "cardForm";
        }

        // to fix here
        // cardService.findBoardAndAddCard(card, boardName, organizationName);
        return "redirect:/" + organizationName + "/boards/" + boardName + "/cards";
    }

    @RequestMapping(value = "/{organizationName}/boards/{boardName}/cards/{cardTitle}", method = RequestMethod.GET)
    public String showCardDetails(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("boardName") String boardName,
            @PathVariable("cardTitle") String cardTitle) {
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("boardName", boardName);
        model.addAttribute("card", cardService.findOne(organizationName, boardName, cardTitle));
        return "cardDetail";
    }

}
