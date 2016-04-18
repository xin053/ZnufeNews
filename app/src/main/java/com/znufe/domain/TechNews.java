package com.znufe.domain;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 科技新闻类
 */

public class TechNews
{
	private String id;
	private String title;
    private String longTitle;
    private String source;
    private String link;
    private String pic;
    private String kpic;
    private String bpic;
    private String intro;
    private String pubDate;
    private Date articlePubDate;
    private String comments;
    private String feedShowStyle;
    private String category;
    private boolean isFocus;
    private String comment;
    private CommentCountInfo commentCountInfo;

    public TechNews()
    {
    }

    public TechNews(String id, String title, String longTitle, String source, String link, String pic, String kpic, String bpic, String intro, String pubDate, Date articlePubDate, String comments, String feedShowStyle, String category, boolean isFocus, String comment, CommentCountInfo commentCountInfo)
    {
        this.id = id;
        this.title = title;
        this.longTitle = longTitle;
        this.source = source;
        this.link = link;
        this.pic = pic;
        this.kpic = kpic;
        this.bpic = bpic;
        this.intro = intro;
        this.pubDate = pubDate;
        this.articlePubDate = articlePubDate;
        this.comments = comments;
        this.feedShowStyle = feedShowStyle;
        this.category = category;
        this.isFocus = isFocus;
        this.comment = comment;
        this.commentCountInfo = commentCountInfo;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getLongTitle()
    {
        return longTitle;
    }

    public void setLongTitle(String longTitle)
    {
        this.longTitle = longTitle;
    }

    public String getSource()
    {
        return source;
    }

    public void setSource(String source)
    {
        this.source = source;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink(String link)
    {
        this.link = link;
    }

    public String getPic()
    {
        return pic;
    }

    public void setPic(String pic)
    {
        this.pic = pic;
    }

    public String getKpic()
    {
        return kpic;
    }

    public void setKpic(String kpic)
    {
        this.kpic = kpic;
    }

    public String getBpic()
    {
        return bpic;
    }

    public void setBpic(String bpic)
    {
        this.bpic = bpic;
    }

    public String getIntro()
    {
        return intro;
    }

    public void setIntro(String intro)
    {
        this.intro = intro;
    }

    public void setPubDate(String pubDate) {
        long parsingResult = Long.parseLong(pubDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String result = sdf.format(parsingResult*1000);//时间戳13位
        this.pubDate = result;
    }

    public String getPubDate()
    {
        return pubDate;
    }

    public Date getArticlePubDate()
    {
        return articlePubDate;
    }

    public void setArticlePubDate(Date articlePubDate)
    {
        this.articlePubDate = articlePubDate;
    }

    public String getComments()
    {
        return comments;
    }

    public void setComments(String comments)
    {
        this.comments = comments;
    }

    public String getFeedShowStyle()
    {
        return feedShowStyle;
    }

    public void setFeedShowStyle(String feedShowStyle)
    {
        this.feedShowStyle = feedShowStyle;
    }

    public String getCategory()
    {
        return category;
    }

    public void setCategory(String category)
    {
        this.category = category;
    }

    public boolean isFocus()
    {
        return isFocus;
    }

    public void setFocus(boolean focus)
    {
        isFocus = focus;
    }

    public String getComment()
    {
        return comment;
    }

    public void setComment(String comment)
    {
        this.comment = comment;
    }

    public CommentCountInfo getCommentCountInfo()
    {
        return commentCountInfo;
    }

    public void setCommentCountInfo(CommentCountInfo commentCountInfo)
    {
        this.commentCountInfo = commentCountInfo;
    }

    @Override
    public String toString()
    {
        return "TechNews{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", longTitle='" + longTitle + '\'' +
                ", source='" + source + '\'' +
                ", link='" + link + '\'' +
                ", pic='" + pic + '\'' +
                ", kpic='" + kpic + '\'' +
                ", bpic='" + bpic + '\'' +
                ", intro='" + intro + '\'' +
                ", pubDate=" + pubDate +
                ", articlePubDate=" + articlePubDate +
                ", comments='" + comments + '\'' +
                ", feedShowStyle='" + feedShowStyle + '\'' +
                ", category='" + category + '\'' +
                ", isFocus=" + isFocus +
                ", comment='" + comment + '\'' +
                ", commentCountInfo=" + commentCountInfo +
                '}';
    }
}
