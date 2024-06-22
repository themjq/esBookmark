package com.example.enums;

public enum KeyRedis {
    LIKE(1),  //用户点赞
    DISLIKE(0), //用户点不喜欢
    RANK_KEY("bm_rank"),  //排行榜的key
    LIKE_KEY("like_list_"),  //用户点赞表
    DISLIKE_KEY("disLike_list_"),  //用户不喜欢表
    UserScore(1.0)  //用户分数 (可设多个，比如给vip用户增加权重)
    ;
    private Integer islike;
    private String key;
    private Double score;

    KeyRedis(int islike) {
        this.islike=islike;
    }
    KeyRedis(String key){
        this.key=key;
    }
    KeyRedis(Double score){
        this.score=score;
    }
    public Integer getIslike(){
        return this.islike;
    }
    public String getKey(){
        return this.key;
    }
    public Double getScore(){
        return this.score;
    }
}
