package com.javaex.ex02;

import java.util.List;

public class AuthorApp {
	public static void main(String []args) {
		
		AuthorDao authorDao = new AuthorDao();
		List<AuthorVo> list;
		
		//작가 등록
		authorDao.authorInsert("이문열", "경상북도 영양");
		authorDao.authorInsert("박경리", "경남 통영");
		
		//작가 삭제
		authorDao.authorDelete(2);
	
		//select(출력)
		List<AuthorVo> authorList = authorDao.authorSelect();
		//list = authorDao.AuthorSelect
		for(int i=0; i<authorList.size(); i++) {
			AuthorVo vo = authorList.get(i);
			//list.size(), list.get(i)
			System.out.println(vo.getAuthorId()+","+vo.getAuthorName()+","+vo.getAuthorDesc());
		}
		
		//작가 수정
		authorDao.authorUpdate(1, "김문열", "경북 영양");
		
		//select
		list = authorDao.authorSelect();
		for(int i=0; i<list.size(); i++) {
			AuthorVo vo = list.get(i);
			System.out.println(vo.getAuthorId()+","+vo.getAuthorName()+","+vo.getAuthorDesc());
		}
	}
}
