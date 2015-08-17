package com.javaetmoi.elasticsearch.musicbrainz.domain;

import java.util.List;

/**
 * Created by sinok on 15/08/15.
 */
public class Release {

    private Integer id;

    private String gid;

    private String name;

    private List<String> tags;

    private Album album = new Album();

    private Label label = new Label();

    public int getId() { return id; }

    public void setId(int id) { this.id = id; }

    public String getGid() { return gid; }

    public void setGid(String gid) { this.gid = gid; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public List<String> getTags() { return tags; }

    public void setTags(List<String> tags) { this.tags = tags; }

    public Album getAlbum() { return album; }

    public Label getLabel() { return label; }

}
