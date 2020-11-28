package com.sj.spring.beans;

public class PageBean2 {

	private int min;
	private int max;
	
	private int prevPage;
	private int nextPage;
	
	private int pageCnt;
	private int currentPage;
	
	public PageBean2(int contentCnt, int page, int page_contentCnt, int page_buttonCnt) {
		
		this.currentPage = page;
		
		pageCnt = contentCnt / page_contentCnt;
		if(contentCnt % page_contentCnt > 0) {
			pageCnt += 1;
		}
		if(pageCnt == 0) {
			pageCnt = 1;
		}
		
		min = (currentPage - 1) / page_buttonCnt * page_buttonCnt + 1;
		max = min + page_buttonCnt - 1;
		
		if(max > pageCnt) {
			max = pageCnt;
		}
		
		prevPage = min - 1;
		nextPage = max + 1;
		
		if(nextPage > pageCnt) {
			nextPage = pageCnt;
		}
		
		if(prevPage < 1) {
			prevPage = 1;
		}
		
	}
	
	public int getMin() {
		return min;
	}
	public int getMax() {
		return max;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	
}