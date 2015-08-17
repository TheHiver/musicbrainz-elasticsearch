package com.javaetmoi.elasticsearch.musicbrainz.batch.mapper;

import com.javaetmoi.elasticsearch.musicbrainz.domain.*;
import fm.last.musicbrainz.data.model.Gender;
import fm.last.musicbrainz.data.model.ReleaseGroupPrimaryType;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * Created by sinok on 15/08/15.
 */
@Component
public class ReleaseRowMapper implements RowMapper<Release> {
    @Override
    public Release mapRow(ResultSet rs, int rowNum) throws SQLException {

        String tagsString;

        Release release = new Release();
        release.setId(rs.getInt("releaseId"));
        release.setGid(rs.getString("releaseGid"));
        release.setName(rs.getString("releaseName"));
        tagsString = rs.getString("releaseTags");
        if(tagsString != null) {
            String[] tags = tagsString.split(",");
            release.setTags(Arrays.asList(tags));
        }


        Label label = release.getLabel();
        label.setId(rs.getInt("labelId"));
        label.setGid(rs.getString("labelGid"));
        label.setName(rs.getString("labelName"));
        label.setMajor(rs.getBoolean("major"));
        tagsString = rs.getString("labelTags");
        if(tagsString != null) {
            String[] tags = tagsString.split(",");
            label.setTags(Arrays.asList(tags));
        }


        Album album = release.getAlbum();
        album.setId(rs.getInt("albumId"));
        album.setGid(rs.getString("albumGid"));
        album.setName(rs.getString("albumName"));
        album.setTypeId(rs.getInt("albumPrimaryTypeId"));
        album.setTypeName(rs.getString("albumPrimaryTypeName"));
        album.setYear(rs.getInt("albumYear"));
        album.getRating().setScore(rs.getInt("albumRatingScore"));
        album.getRating().setCount(rs.getInt("albumRatingCount"));
        tagsString = rs.getString("albumTags");
        if(tagsString != null) {
            String[] tags = tagsString.split(",");
            album.setTags(Arrays.asList(tags));
        }

        Artist artist = album.getArtist();
        artist.setId(rs.getInt("artistId"));
        artist.setGid(rs.getString("artistGid"));
        artist.setName(rs.getString("artistName"));
        artist.setBeginDateYear(rs.getString("artistBeginDateYear"));
        if (rs.getObject("artistTypeId") != null) {
            artist.setTypeId(rs.getInt("artistTypeId"));
            artist.setTypeName(rs.getString("artistTypeName"));
        }
        if (rs.getObject("artistGenderId") != null) {
            artist.setGender(Gender.valueOf(rs.getInt("artistGenderId")));
        }
        artist.setArea(rs.getString("artistAreaName"));
        artist.getRating().setScore(rs.getInt("artistRatingScore"));
        artist.getRating().setCount(rs.getInt("artistRatingCount"));
        tagsString = rs.getString("artistTags");
        if(tagsString != null) {
            String[] tags = tagsString.split(",");
            artist.setTags(Arrays.asList(tags));
        }
        return release;
    }

}
