package com.exam.model;

import java.util.ArrayList;

public class BoardListTO {
	
	private int cpage;
	private int recordPerPage;
	private int blockPerPage;
	private int totalPage;
	private int totalRecord;
	private int startBlock;
	private int endBlock;
	private String SearchKey;
	private String SearchWord;
	public BoardListTO() {
		// TODO Auto-generated constructor stub
		this.cpage=1;
		this.recordPerPage=10;
		this.blockPerPage=5;
		this.totalPage=1;
		this.totalRecord=0;
	}
	private ArrayList<latterBoardTO> latterList;
	private ArrayList<FreeBoardTO> freeList;
	private ArrayList<ColumnBoardTO> columnList;
	private ArrayList<InfoBoardTO> infoList;
	private ArrayList<CusBoardTO> customerList;
	
	
	public String getSearchKey() {
		return SearchKey;
	}

	public void setSearchKey(String searchKey) {
		SearchKey = searchKey;
	}

	public String getSearchWord() {
		return SearchWord;
	}

	public void setSearchWord(String searchWord) {
		SearchWord = searchWord;
	}

	public ArrayList<CusBoardTO> getCustomerList() {
		return customerList;
	}

	public void setCustomerList(ArrayList<CusBoardTO> customerList) {
		this.customerList = customerList;
	}
	public ArrayList<InfoBoardTO> getInfoList() {
		return infoList;
	}

	public void setInfoList(ArrayList<InfoBoardTO> infoList) {
		this.infoList = infoList;
	}

	public ArrayList<ColumnBoardTO> getColumnList() {
		return columnList;
	}

	public void setColumnList(ArrayList<ColumnBoardTO> columnList) {
		this.columnList = columnList;
	}

	public ArrayList<FreeBoardTO> getFreeList() {
		return freeList;
	}

	public void setFreeList(ArrayList<FreeBoardTO> freeList) {
		this.freeList = freeList;
	}

	public ArrayList<latterBoardTO> getLatterList() {
		return latterList;
	}

	public void setLatterList(ArrayList<latterBoardTO> latterList) {
		this.latterList = latterList;
	}

	public int getCpage() {
		return cpage;
	}

	public void setCpage(int cpage) {
		this.cpage = cpage;
	}

	public int getRecordPerPage() {
		return recordPerPage;
	}

	public void setRecordPerPage(int recordPerPage) {
		this.recordPerPage = recordPerPage;
	}

	public int getBlockPerPage() {
		return blockPerPage;
	}

	public void setBlockPerPage(int blockPerPage) {
		this.blockPerPage = blockPerPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getStartBlock() {
		return startBlock;
	}

	public void setStartBlock(int startBlock) {
		this.startBlock = startBlock;
	}

	public int getEndBlock() {
		return endBlock;
	}

	public void setEndBlock(int endBlock) {
		this.endBlock = endBlock;
	}

	
}
