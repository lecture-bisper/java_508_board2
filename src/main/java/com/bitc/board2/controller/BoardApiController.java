package com.bitc.board2.controller;

import com.bitc.board2.dto.Board2Dto;
import com.bitc.board2.service.Board2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ConditionalOnEnabledResourceChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Rest Api 방식을 사용하면 template과 연동하는 것이 아니라 데이터를 직접 전달함
// @RestController : @Controller와 @ResponseBody가 하나로 합쳐진 어노테이션
// @ResponseBody : 데이터를 클라언트에게 전달 시 템플릿과 함께 전달하는 것이 아닌 데이터 자체를 전달하도록 하는 어노테이션, Ajax 통신 시 필수로 사용해야 함
@RestController
public class BoardApiController {

  @Autowired
  private Board2Service board2Service;

  @RequestMapping(value = "/test", method = RequestMethod.GET)
  public String restApiTest() throws Exception {
    return "Restful Api 방식 Test 페이지";
  }

  @RequestMapping(value = "/api/board", method = RequestMethod.GET)
  public List<Board2Dto> openBoard2List() throws Exception {
    return board2Service.selectBoard2List();
  }

//  @RequestBody : @RequestParam과 비슷한 기능을 하는 어노테이션으로 메서드의 파라미터가 반드시 HTTP 패킷의 바디에 담겨 있어야 한다는 것을 나타내는 어노테이션, 주로 POST, PUT을 사용하는 메서드에는 @RequestBody 를 사용하고, GET 을 사용하는 메서드에는 @RequestParam을 주로 사용함
  @RequestMapping(value = "/api/board/write", method = RequestMethod.POST)
  public void board2Write(@RequestBody Board2Dto board) throws Exception {
    board2Service.board2Insert(board);
  }

  @RequestMapping(value = "/api/board/{boardIdx}", method = RequestMethod.GET)
  public Board2Dto openBoard2Detail(@PathVariable("boardIdx") int boardIdx) throws Exception {
    return board2Service.selectBoard2Detail(boardIdx);
  }

//  현재 update와 delete는 명령 수행 후 rest방식의 board 페이지로 화면이 넘어가도록 설정되어 있음
//  리다이렉트가 필요없으면 반환값을 void로 사용해도 됨
  @RequestMapping(value = "/api/board/{boardIdx}", method = RequestMethod.PUT)
  public String board2Update(@PathVariable("boardIdx") int boardIdx, @RequestBody Board2Dto board) throws Exception {
    board.setBoardIdx(boardIdx);
    board2Service.board2Update(board);
    return "redirect:/board";
  }

  @RequestMapping(value = "/api/board/{boardIdx}", method = RequestMethod.DELETE)
  public String board2Delete(@PathVariable("boardIdx") int boardIdx) throws Exception {
    board2Service.board2Delete(boardIdx);
    return "redirect:/board";
  }

}
