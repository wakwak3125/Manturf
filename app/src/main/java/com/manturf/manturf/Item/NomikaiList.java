package com.manturf.manturf.Item;

import com.activeandroid.Model;
import com.activeandroid.annotation.Column;
import com.activeandroid.annotation.Table;

@Table(name = "EventList")
public class NomikaiList extends Model{

    @Column(name = "event_id")
    private int event_id;
    @Column(name = "title")
    private String title;
    @Column(name = "content")
    private String content;
    @Column(name = "created_at")
    private String created_at;
    @Column(name = "update_at")
    private String update_at;
    @Column(name = "date")
    private String date;
    @Column(name = "place")
    private String place;
    @Column(name = "time")
    private String time;
    @Column(name = "occupation")
    private String occupation;

    public NomikaiList(int event_id,
                       String title,
                       String content,
                       String created_at,
                       String update_at,
                       String date,
                       String place,
                       String time,
                       String occupation
    ){
        this.event_id=event_id;
        this.title=title;
        this.content=content;
        this.created_at=created_at;
        this.update_at=update_at;
        this.date=date;
        this.place=place;
        this.time=time;
        this.occupation=occupation;
    }

    public NomikaiList(){
        super();
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public int getEventId(){
        return event_id;
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