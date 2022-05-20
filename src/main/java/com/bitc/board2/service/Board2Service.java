package com.bitc.board2.service;

import com.bitc.board2.dto.Board2Dto;

import java.util.List;

public interface Board2Service {
  List<Board2Dto> selectBoard2List() throws Exception;

  void board2Insert(Board2Dto board) throws Exception;

  Board2Dto selectBoard2Detail(int boardIdx) throws Exception;

  void board2Update(Board2Dto board) throws Exception;

  void board2Delete(int boardIdx) throws Exception;
}
