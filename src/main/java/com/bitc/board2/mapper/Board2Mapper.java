package com.bitc.board2.mapper;

import com.bitc.board2.dto.Board2Dto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Board2Mapper {
  List<Board2Dto> selectBoard2List() throws Exception;

  void board2Insert(Board2Dto board) throws Exception;

  void board2UpdateHitCnt(int boardIdx) throws Exception;

  Board2Dto selectBoard2Detail(int boardIdx) throws Exception;

  void board2Update(Board2Dto board) throws Exception;

  void board2Delete(int boardIdx) throws Exception;
}
