package com.javaetmoi.elasticsearch.musicbrainz.domain;

import java.util.List;

/**
 * Created by sinok on 15/08/15.
 */
public class Track {

    private Integer id;
    private String gid;
    private String title;
    private Integer position;
    private String isrc;
    private List<String> tags;
    private Release release = new Release();

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getGid() { return gid; }

    public void setGid(String gid) { this.gid = gid; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public int getPosition() { return position; }

    public void setPosition(int position) { this.position = position; }

    public String getIsrc() { return isrc; }

    public void setIsrc(String isrc) { this.isrc = isrc; }

    public List<String> getTags() { return tags; }

    public void setTags(List<String> tags) { this.tags = tags; }

    public Release getRelease() { return release; }

}
