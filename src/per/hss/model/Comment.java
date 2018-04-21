package per.hss.model;

import java.util.Date;

public class Comment
{
    private int commentId;
    private int newsId;
    private String content;
    private String userIp;
    private Date commentDate;

    public Comment()
    {
        super();
    }
    public Comment(int newsId)
    {
        this.newsId=newsId;
    }
    public Comment(int newsId,String content,String userIp)
    {
        this.newsId=newsId;
        this.content=content;
        this.userIp=userIp;
    }
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public int getNewsId() {
        return newsId;
    }

    public void setNewsId(int newsId) {
        this.newsId = newsId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserIp() {
        return userIp;
    }

    public void setUserIp(String userIp) {
        this.userIp = userIp;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
