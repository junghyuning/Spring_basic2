package com.fastcampus.ch4.domain;

public class PageHandler {
   private int totalCnt; // 총 게시물 갯수
    /* private int pageSize; //한 페이지 크기
    private int page; // 현재 페이지
    private  String option;
    private String keyword;*/
    private SearchCondition sc;
    private int naviSize = 10; //페이지 네비게이션의 크기
    private int totalPage; //전체 페이지 갯수
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

    public PageHandler(int totalCnt, SearchCondition sc){
        this.totalCnt = totalCnt;
        this.sc = sc;
        doPaging(totalCnt,sc);
    }

    public void doPaging(int totalCnt, SearchCondition sc) {
        this.totalCnt = totalCnt;

        totalPage=(int)Math.ceil(totalCnt/(double)sc.getPageSize());

//        beginPage = page-(page%pageSize)+1; => 안됨?

        beginPage = (sc.getPage()-1)/ naviSize * naviSize +1;
        endPage = Math.min(beginPage+naviSize-1,totalPage);
        showPrev = beginPage!=1;
        showNext = endPage != totalPage;
    }

    void print(){
        System.out.println("page = " + sc.getPage());
        System.out.println(showPrev ? "[PREV]": "");
        for(int i = beginPage; i<=endPage;i++){
            System.out.println(i+" ");
        }
        System.out.println(showNext ? "[NEXT]": "");
    }

    public void setPageRange(int page, int pageSize){
        pageStart = (page-1)*pageSize + 1;
        pageEnd = page*pageSize;
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


    @Override
    public String toString() {
        return "PageHandler{" +
                "totalCnt=" + totalCnt +
                ", sc=" + sc +
                ", naviSize=" + naviSize +
                ", totalPage=" + totalPage +
                ", beginPage=" + beginPage +
                ", endPage=" + endPage +
                ", showPrev=" + showPrev +
                ", showNext=" + showNext +
                ", pageStart=" + pageStart +
                ", pageEnd=" + pageEnd +
                '}';
    }
}
