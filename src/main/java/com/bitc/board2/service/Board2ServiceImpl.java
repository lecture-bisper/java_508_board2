package com.bitc.board2.service;

import com.bitc.board2.dto.Board2Dto;
import com.bitc.board2.mapper.Board2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Board2ServiceImpl implements Board2Service {

  @Autowired
  private Board2Mapper board2Mapper;

  @Override
  public List<Board2Dto> selectBoard2List() throws Exception {
    return board2Mapper.selectBoard2List();
  }

  @Override
  public void board2Insert(Board2Dto board) throws Exception {
    board2Mapper.board2Insert(board);
  }

  @Override
  public Board2Dto selectBoard2Detail(int boardIdx) throws Exception {
    board2Mapper.board2UpdateHitCnt(boardIdx);
    Board2Dto board = board2Mapper.selectBoard2Detail(boardIdx);
    return board;
  }

  @Override
  public void board2Update(Board2Dto board) throws Exception {
    board2Mapper.board2Update(board);
  }

  @Override
  public void board2Delete(int boardIdx) throws Exception {
    board2Mapper.board2Delete(boardIdx);
  }
}
