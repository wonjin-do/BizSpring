package org.zerock.mapper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.mapper.BoardMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardMapperTests {
	@Setter(onMethod_ =  @Autowired)
	private BoardMapper mapper;
	
//	@Test
//	public void testGetList() {
//		mapper.getList().forEach(board->log.info(board));
//	}
//	
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("new Post");
		board.setContent("new Content");
		board.setWriter("newbie");
		mapper.insert(board);
		log.info(board);
		
	}
//	
//	@Test
//	public void testRead() {
//		BoardVO board = mapper.read(7L);
//		log.info(board);
//	}
	
	@Test
	public void testDelete() {
		log.info("DELETE COUNT: " + mapper.delete(2L));
	}
	
//	@Test
//	public void testUpdate() {
//		BoardVO board = new BoardVO();
//		board.setBno(7L);
//		board.setTitle("editted Title");
//		board.setContent("editted Content");
//		board.setWriter("user00");
//		int count = mapper.update(board);
//		log.info("UPDATE COUNT: " + count);
//	}
	
	
}
