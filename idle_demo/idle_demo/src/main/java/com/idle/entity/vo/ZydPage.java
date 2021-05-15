package com.idle.entity.vo;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

public class ZydPage<T> implements Serializable {

    private int current = 1;
    private long total;
    private long pages;
    private List<T> records = Collections.emptyList();

    public ZydPage() {
        super();
    }
    
    public ZydPage(int current, long total, List<T> records) {
        super();
        this.current = current;
        this.total = total;
        this.records = records;
    }
    
    
    public ZydPage(int current, long total, int pageSize, List<T> records) {
        super();
        this.current = current;
        this.total = total;
        this.records = records;
        if(pageSize !=0 ){
        	if( total % pageSize == 0){
        		pages = total / pageSize;
        	}else{
        		pages = (total / pageSize) + 1;
        	}
        }
    }
    
    public ZydPage(int current, long total, long pages , List<T> records) {
        super();
        this.current = current;
        this.total = total;
        this.pages = pages;
        this.records = records;
    }

    public long getPages() {
		return pages;
	}

	public void setPages(long pages) {
		this.pages = pages;
	}

	public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }
}
