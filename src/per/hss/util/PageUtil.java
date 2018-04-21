package per.hss.util;

/**
 * 分页相关
 */
public class PageUtil {
    public static String  getUpAndDownPagation(int totalNum,int currentPage,int pageSize,String typeId)
    {
        //获取总页数
        int totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
        StringBuffer pageCode=new StringBuffer();
        pageCode.append("<ul class='pager'>");
        if(currentPage==1){
            pageCode.append("<li class='disabled'><a href='#'>上一页</a></li>");
        }else{
            pageCode.append("<li><a href='news?action=list&typeId="+typeId+"&page="+(currentPage-1)+"'>上一页</a></li>");
        }
        pageCode.append("&nbsp;&nbsp;");
        if(currentPage==totalPage){
            pageCode.append("<li class='disabled'><a href='#'>下一页</a></li>");
        }else{
            pageCode.append("<li><a href='news?action=list&typeId="+typeId+"&page="+(currentPage+1)+"'>下一页</a></li>");
        }
        pageCode.append("</ul>");
        return pageCode.toString();
    }
}
