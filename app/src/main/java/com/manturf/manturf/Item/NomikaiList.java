package com.manturf.manturf.Item;


public class NomikaiList {
    private int id;
    private String title;
    private String content;
    private String occupation;
    private String place;
    private String date;
    private String time;

    public NomikaiList(int id,String title,String content,String occupation,
                       String place,String date,String time){
        this.id=id;
        this.title=title;
        this.content=content;
        this.occupation=occupation;
        this.place=place;
        this.date=date;
        this.time=time;
    }

    public int getId(){
        return id;
    }

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public String getOccupation(){
        return occupation;
    }

    public String getPlace(){
        return place;
    }

    public String getDate(){
        return date;
    }

    public String getTime(){
        return time;
    }
}