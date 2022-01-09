package com.javaex.ex03;

import java.util.List;

public class AuthorApp {
	public static void main(String []args) {
		
		List<AuthorVo> list;
		AuthorDao authorDao = new AuthorDao();
		AuthorVo vo01 = new AuthorVo("이문열", "경북 영양");
		authorDao.authorInsert(vo01);
		
		AuthorVo vo02 = new AuthorVo("박경리", "경남 통영");
		authorDao.authorInsert(vo02);
		
		AuthorVo vo03 = new AuthorVo("강동원", "검은 사제들");
		authorDao.authorInsert(vo03);
		
		// 출력
		list = authorDao.authorSelect();
		for (int i = 0; i < list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId() + ", " + vo.getAuthorName() + ", " + vo.getAuthorDesc());
		}
		
		//update
		AuthorVo authorVo = new AuthorVo(2, "박경리(edit)", "경상남도 통영(edit)");
		authorDao.authorUpdate(authorVo);
		list = authorDao.authorSelect();
		for(int i=0;i<list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId()+","+vo.getAuthorName()+","+vo.getAuthorDesc());
		}
		
		//delete
		authorDao.authorDelete(1);
		list = authorDao.authorSelect();
			for(int i=0; i<list.size(); i++) {
				AuthorVo vo=list.get(i);
				System.out.println(vo.getAuthorId()+","+vo.getAuthorName()+","+vo.getAuthorDesc());
		}
	}
}
