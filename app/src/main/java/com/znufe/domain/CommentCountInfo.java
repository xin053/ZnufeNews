package com.znufe.domain;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by zzx on 2016/3/20.
 */
public class CommentCountInfo
{
    private int qreply;
    private int total;
    private int show;
    private int commentStatus;
    private int praise;
    private int dispraise;

    public CommentCountInfo()
    {
    }

    public CommentCountInfo(JSONObject comment_count_info)
    {
        try
        {
            this.qreply = comment_count_info.getInt("qreply");
            this.total = comment_count_info.getInt("total");
            this.show = comment_count_info.getInt("show");
            this.commentStatus = comment_count_info.getInt("comment_status");
            this.praise = comment_count_info.getInt("praise");
            this.dispraise = comment_count_info.getInt("dispraise");

        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public CommentCountInfo(int qreply, int total, int show, int commentStatus, int praise, int dispraise)
    {
        this.qreply = qreply;
        this.total = total;
        this.show = show;
        this.commentStatus = commentStatus;
        this.praise = praise;
        this.dispraise = dispraise;
    }

    public int getQreply()
    {
        return qreply;
    }

    public void setQreply(int qreply)
    {
        this.qreply = qreply;
    }

    public int getTotal()
    {
        return total;
    }

    public void setTotal(int total)
    {
        this.total = total;
    }

    public int getShow()
    {
        return show;
    }

    public void setShow(int show)
    {
        this.show = show;
    }

    public int getCommentStatus()
    {
        return commentStatus;
    }

    public void setCommentStatus(int commentStatus)
    {
        this.commentStatus = commentStatus;
    }

    public int getPraise()
    {
        return praise;
    }

    public void setPraise(int praise)
    {
        this.praise = praise;
    }

    public int getDispraise()
    {
        return dispraise;
    }

    public void setDispraise(int dispraise)
    {
        this.dispraise = dispraise;
    }

    @Override
    public String toString()
    {
        return "CommentCountInfo{" +
                "qreply=" + qreply +
                ", total=" + total +
                ", show=" + show +
                ", commentStatus=" + commentStatus +
                ", praise=" + praise +
                ", dispraise=" + dispraise +
                '}';
    }
}
