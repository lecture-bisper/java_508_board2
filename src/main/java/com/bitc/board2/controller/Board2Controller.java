package com.bitc.board2.controller;

import com.bitc.board2.dto.Board2Dto;
import com.bitc.board2.service.Board2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class Board2Controller {

  @Autowired
  private Board2Service board2Service;

  @RequestMapping("/")
  public String index() throws Exception {
    return "index";
  }

  @RequestMapping(value = "/board", method = RequestMethod.GET)
  public ModelAndView openBoard2List() throws Exception {
    ModelAndView mv = new ModelAndView("/rest/restBoard2List");

    List<Board2Dto> dataList = board2Service.selectBoard2List();
    mv.addObject("dataList", dataList);

    return mv;
  }

//  RestFul 방식은 데이터를 전송하는 방법을 지정함
//  리소스를 어떻게 사용할 것인지에 따라 GET(조회), POST(입력), PUT(수정), DELETE(삭제)로 나누어서 사용
//  @RequestMapping 어노테이션 사용 시 value 속성에 주소를 입력하고, method 속성에 RequestMethod 값을 입력함
//  만약 method 및 value 속성을 사용하지 않을 경우, @GetMapping, @PostMapping, @PutMapping, @DeleteMapping 어노테이션을 대신 사용해야 함
  @RequestMapping(value = "/board/write", method = RequestMethod.GET)
  public String board2Write() throws Exception {
    return "/rest/restBoard2Write";
  }

//  RequestMapping의 value속성이 동일하더라도, method 속성 값이 다르기 때문에 다른 메서드로 맵핑되어 다른 기능을 수햄함
  @RequestMapping(value = "/board/write", method = RequestMethod.POST)
  public String board2Insert(Board2Dto board) throws Exception {
    board2Service.board2Insert(board);
    return "redirect:/board";
  }

//  @Pathvariable : 메서드의 파라미터가 URI의 변수로 사용되는 의미
  @RequestMapping(value = "/board/{boardIdx}", method = RequestMethod.GET)
  public ModelAndView openBoard2Detail(@PathVariable("boardIdx") int boardIdx) throws Exception {
    ModelAndView mv = new ModelAndView("/rest/restBoard2Detail");

    Board2Dto board = board2Service.selectBoard2Detail(boardIdx);
    mv.addObject("board", board);

    return mv;
  }

  @RequestMapping(value = "/board/{boardIdx}", method = RequestMethod.PUT)
  public String board2Update(Board2Dto board) throws Exception {
    board2Service.board2Update(board);

    return "redirect:/board";
  }

//  @DeleteMapping("/board/{boardIdx}")
  @RequestMapping(value = "/board/{boardIdx}", method = RequestMethod.DELETE)
  public String board2Delete(@PathVariable("boardIdx") int boardIdx) throws Exception {
    board2Service.board2Delete(boardIdx);

    return "redirect:/board";
  }

}
