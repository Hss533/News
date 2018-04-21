package per.hss.util;

public class NavUtil {
    //面包屑
    public static String genNewsListNavigation(String typeName,String typeId){

        StringBuffer navCode=new StringBuffer();
        navCode.append("当前位置:&nbsp;&nbsp;");
        navCode.append("<a href='goIndex'>主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;");
        navCode.append("<a href='news?action=list&typeId="+typeId+"'>"+typeName+"</a>");
        return navCode.toString();
    }
    public static String genNewsNavigation(String typeName,String typeId,String newsTitle)
    {
        StringBuffer navCode=new StringBuffer();
        navCode.append("当前位置:&nbsp;&nbsp;");
        navCode.append("<a href='goIndex'>主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;");
        navCode.append("<a href='news?action=list&typeId="+typeId+"'>"+typeName+"</a>&nbsp;&nbsp;>"+newsTitle);
        return navCode.toString();
    }
}
