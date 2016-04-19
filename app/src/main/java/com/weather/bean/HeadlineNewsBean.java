package com.weather.bean;

import com.znufe.domain.CommentCountInfo;

import org.json.JSONArray;

import java.text.SimpleDateFormat;

/**
 * Created by Cui on 16-4-17.
 */
public class HeadlineNewsBean {
    private String id;
    private String title;        //���ű���
    private String long_title;        //���ű���
    private String source;       //��Դ
    private String link;         //��������
    private String picLink;      //ͼƬ����
    private String kpic;
    private String introduce;       //���
    private String pubDate;         //����
    private int articlePubDate;
    private String  comments;
    private JSONArray picsArray;    //ͼƬ��
    private String feedShowStyle;
    private String category; //���
    private Boolean is_focus=false;
    private int comment;  //���۵�����
    private CommentCountInfo comment_count_info;

    public HeadlineNewsBean(){
        comment=0;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLong_title() {
        return long_title;
    }

    public void setLong_title(String long_title) {
        this.long_title = long_title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPicLink() {
        return picLink;
    }

    public void setPicLink(String picLink) {
        this.picLink = picLink;
    }

    public String getKpic() {
        return kpic;
    }

    public void setKpic(String kpic) {
        this.kpic = kpic;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        long parsingResult = Long.parseLong(pubDate);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String result = sdf.format(parsingResult*1000);//ʱ���13λ
        this.pubDate = result;
    }

    public int getArticlePubDate() {
        return articlePubDate;
    }

    public void setArticlePubDate(int articlePubDate) {
        this.articlePubDate = articlePubDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public JSONArray getPicsArray() {
        return picsArray;
    }

    public void setPicsArray(JSONArray picsArray) {
        this.picsArray = picsArray;
    }

    public String getFeedShowStyle() {
        return feedShowStyle;
    }

    public void setFeedShowStyle(String feedShowStyle) {
        this.feedShowStyle = feedShowStyle;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Boolean getIs_focus() {
        return is_focus;
    }

    public void setIs_focus(Boolean is_focus) {
        this.is_focus = is_focus;
    }

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public CommentCountInfo getComment_count_info() {
        return comment_count_info;
    }

    public void setComment_count_info(CommentCountInfo comment_count_info) {
        this.comment_count_info = comment_count_info;
    }

    @Override
    public String toString() {
        return "datedhot="+ getIs_focus();
    }
}
