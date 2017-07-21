package com.aungmyohtet.pm.web.update;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.aungmyohtet.pm.dto.BoardDto;
import com.aungmyohtet.pm.entity.Board;
import com.aungmyohtet.pm.entity.Organization;
import com.aungmyohtet.pm.service.BoardService2;
import com.aungmyohtet.pm.service.OrganizationService;

@Controller
public class BoardServiceTestController {

    @Autowired
    private BoardService2 boardService;

    @Autowired
    private OrganizationService organizationService;

    @GetMapping(value = "/json/update/boards")
    @ResponseBody
    private List<BoardDto> getAllBoards(Model model) {
        List<Board> boards =  boardService.findAll();
        return boards.stream().map(board -> boardService.convertToDto(board)).collect(Collectors.toList());
    }

    @GetMapping(value = "json/update/boards/search-by-organization")
    @ResponseBody
    private List<BoardDto> findBoardsByOrganization(@RequestParam("organizationName") String organizationName) {
        Organization organization = organizationService.findByName(organizationName);
        List<Board> boards = boardService.findByOrganization(organization);
        return boards.stream().map(board -> boardService.convertToDto(board)).collect(Collectors.toList()); 
    }
}
