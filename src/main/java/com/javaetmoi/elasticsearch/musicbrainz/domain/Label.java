package com.javaetmoi.elasticsearch.musicbrainz.domain;

import java.util.List;

/**
 * Created by sinok on 15/08/15.
 */
public class Label {

    private Integer id;

    private String gid;

    private String name;

    private boolean major;

    private List<String> tags;


    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getGid() { return gid; }

    public void setGid(String gid) { this.gid = gid; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public boolean isMajor() { return major; }

    public void setMajor(boolean major) { this.major = major; }

    public List<String> getTags() { return tags; }

    public void setTags(List<String> tags) { this.tags = tags; }

}
