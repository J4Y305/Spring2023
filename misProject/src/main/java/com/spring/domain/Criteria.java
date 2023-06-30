package com.spring.domain;

public class Criteria {

	private int page; // 시작할 페이지
	private int perPageNum; // 화면에 보여줄 게시글 수

	private int startPage; // MyBatis에서 사용할 시작 페이지 정보

	// 초기 데이터 설정을 위한 생성자
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		// 페이징에 대한 예외 처리
		if (page <= 0) {
			this.page = 1;
			return;
		}

		this.page = page;
	}

	public int getPerPageNum() {
		return perPageNum;
	}

	public void setPerPageNum(int perPageNum) {
		this.perPageNum = perPageNum;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	
	// MyBatis에서 사용할 method
	public int getPageEnd() {
		return this.startPage + 9;
	}

	public int getPageStart() {
		// 페이징 시작 row 계산: 1 page => 1 row, 2 page=> 11 row, 3 page => 21 row, 4 page => 31 row
		this.startPage = (this.page * this.perPageNum) - 9;
		
		return this.startPage;
	}

	
	@Override
	public String toString() {
		return "Criteria [page=" + page + ", perPageNum=" + perPageNum + ", startPage=" + startPage + ", getPageEnd()="
				+ getPageEnd() + ", getPageStart()=" + getPageStart() + "]";
	}

}
