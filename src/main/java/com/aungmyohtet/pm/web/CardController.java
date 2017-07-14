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

    @RequestMapping(value = "/{organizationName}/boards/{boardNo}/cards", method = RequestMethod.GET)
    public String showCards(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("boardNo") int boardNo) {
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("boardNo", boardNo);
        model.addAttribute("cards", cardService.findByOrganizationNameAndBoardNo(organizationName, boardNo));
        // model.addAttribute("boardName", boardService.findBoardNamebyBoardNoAndOrganizationName(boardNo, organizationName));
        return "cardList";
    }

    @RequestMapping(value = "/{organizationName}/boards/{boardNo}/cards/new", method = RequestMethod.GET)
    public String showCardForm(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("boardNo") int boardNo) {
        model.addAttribute("card", new Card());
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("boardNo", boardNo);
        return "cardForm";
    }

    @RequestMapping(value = "{organizationName}/boards/{boardNo}/cards/new", method = RequestMethod.POST)
    public String addBoard(@Validated @ModelAttribute Card card, BindingResult result, Model model, @PathVariable("organizationName") String organizationName,
            @PathVariable("boardNo") int boardNo) {

        if (result.hasErrors()) {
            return "cardForm";
        }

        cardService.findBoardAndAddCard(card, boardNo, organizationName);
        return "redirect:/" + organizationName + "/boards/" + boardNo + "/cards";
    }

    @RequestMapping(value = "/{organizationName}/boards/{boardNo}/cards/{cardTitle}", method = RequestMethod.GET)
    public String showCardDetails(Model model, @PathVariable("organizationName") String organizationName, @PathVariable("boardNo") int boardNo,
            @PathVariable("cardTitle") String cardTitle) {
        model.addAttribute("organizationName", organizationName);
        model.addAttribute("boardNo", boardNo);
        model.addAttribute("card", cardService.find(organizationName, boardNo, cardTitle));
        return "cardDetail";
    }

}
