package per.hss.dao;
import per.hss.model.Comment;
import per.hss.util.DateUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.*;
import java.sql.*;

public class CommentDao {
/*emmmmmmmm**/
    public List<Comment> commentList(Connection con, Comment s_comment) throws Exception {
        List<Comment> commentList = new ArrayList<Comment>();
        StringBuffer sb = new StringBuffer("select * from t_comment");
        if (s_comment.getNewsId() != -1) {
            sb.append(" and newId=" + s_comment.getNewsId());
        }
        sb.append(" order by commentDate desc ");
	 System.out.println("asd");
        try {
            System.out.println(sb.toString().replaceFirst("and", "where"));
            PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Comment comment = new Comment();
                comment.setCommentId(rs.getInt("commitId"));
                comment.setNewsId(rs.getInt("newId"));
                comment.setContent(rs.getString("content"));
                comment.setUserIp(rs.getString("userIP"));
                comment.setCommentDate(DateUtil.stringToDate(rs.getString("commentDate"), "yyyy-MM-dd HH:mm:ss"));
                commentList.add(comment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return commentList;
    }

    /**
     * 添加一条评论
     */
    public int commentAdd(Connection con, Comment comment)
    {
        String sql="insert into t_comment values (null,?,?,?,now())";
        int result=0;
        try {

            PreparedStatement ptmt=con.prepareStatement(sql);
            ptmt.setInt(1,comment.getNewsId());
            ptmt.setString(2,comment.getContent());
            ptmt.setString(3,comment.getUserIp());
result=ptmt.executeUpdate();

            } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
