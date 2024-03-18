package kr.board.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data //- Lombok API
@AllArgsConstructor
public class Board {
  private int idx; //
  private String title; // 
  private String content; // 
  private String writer; // 
  private String indate; // 
  private int count; 
  // setter , getter
}
