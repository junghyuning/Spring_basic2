package com.fastcampus.ch4.domain;

public class PageHandler {
    private int totalCnt; // 총 게시물 갯수
    private int pageSize; //한 페이지 크기
    private int naviSize = 10; //페이지 네비게이션의 크기
    private int totalPage; //전체 페이지 갯수
    private int page; // 현재 페이지
    private int beginPage; //네비게이션의 첫번째 페이지
    private int endPage; // 네비게이션의 마지막 페이지
    private boolean showPrev; //이전페이지로 이동하는 링크를 보여줄 것인지
    private boolean showNext; //다음페이지로 이동하는 링크를 보여줄 것인지

    private int pageStart; //페이지의 시작글의 rownum
    private int pageEnd; // 페이지의 끝글 번호 rownum

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getNaviSize() {
        return naviSize;
    }

    public void setNaviSize(int naviSize) {
        this.naviSize = naviSize;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getBeginPage() {
        return beginPage;
    }

    public void setBeginPage(int beginPage) {
        this.beginPage = beginPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isShowPrev() {
        return showPrev;
    }

    public void setShowPrev(boolean showPrev) {
        this.showPrev = showPrev;
    }

    public boolean isShowNext() {
        return showNext;
    }

    public void setShowNext(boolean showNext) {
        this.showNext = showNext;
    }

    public PageHandler() {
    }

    public PageHandler(int totalCnt, int page) {
        this(totalCnt,page,10);

    }

    public int getPageStart() {
        return pageStart;
    }

    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageEnd() {
        return pageEnd;
    }

    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }

    //    페이지 번호 뜨는 것
    public PageHandler(int totalCnt, int page, int pageSize) {
        this.totalCnt = totalCnt;
        this.pageSize = pageSize;
        this.page = page;

        totalPage=(int)Math.ceil(totalCnt/(double)pageSize);

//        beginPage = page-(page%pageSize)+1; => 안됨?

        beginPage = page/ naviSize * naviSize +1;
        endPage = Math.min(beginPage+naviSize-1,totalPage);
        showPrev = beginPage!=1;
        showNext = endPage != totalPage;
    }

    void print(){
        System.out.println("page = " + page);
        System.out.println(showPrev ? "[PREV]": "");
        for(int i = beginPage; i<=endPage;i++){
            System.out.println(i+" ");
        }
        System.out.println(showNext ? "[NEXT]": "");
    }

    public void setPageRange(){
        pageStart = (page-1)*pageSize + 1;
        pageEnd = page*pageSize;
    }

    @Override
    public String toString() {
        return "PageHandler{" +
                "totalCnt=" + totalCnt +
                ", pageSize=" + pageSize +
                ", naviSize=" + naviSize +
                ", totalPage=" + totalPage +
                ", page=" + page +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                ", pageStart=" + pageStart +
                ", pageEnd=" + pageEnd +
                '}';
    }
}
