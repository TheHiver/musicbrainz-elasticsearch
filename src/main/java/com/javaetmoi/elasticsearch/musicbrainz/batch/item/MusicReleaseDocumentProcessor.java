/**
 * Copyright 2013 the original author or authors.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.javaetmoi.elasticsearch.musicbrainz.batch.item;

import com.javaetmoi.core.batch.item.EsDocumentProcessor;
import com.javaetmoi.elasticsearch.musicbrainz.domain.*;
import org.elasticsearch.common.xcontent.XContentBuilder;

import java.io.IOException;

public class MusicReleaseDocumentProcessor extends EsDocumentProcessor<Release> {


    private String documentType;

    @Override protected void fillContentBuilder(XContentBuilder content, Release release) throws IOException {
        content.field("id", release.getId());
        content.field("gid", release.getGid());
        content.field("name", release.getName());
        if ((release.getTags() != null) && !release.getTags().isEmpty()) {
            content.array("tags", release.getTags().toArray(new String[release.getTags().size()]));
        }

        fillLabeluilder(content, release.getLabel());
        fillAlbumBuilder(content, release.getAlbum());

    }

    protected void fillLabeluilder(XContentBuilder content, Label label) throws IOException {
        XContentBuilder labelBuilder = content.startObject("label");
        labelBuilder.field("id", label.getId());
        labelBuilder.field("gid", label.getGid());
        labelBuilder.field("name", label.getName());
        labelBuilder.field("major", label.isMajor());
        if ((label.getTags() != null) && !label.getTags().isEmpty()) {
            labelBuilder.array("tags", label.getTags().toArray(new String[label.getTags().size()]));
        }
        labelBuilder.endObject();
    }

    protected void fillAlbumBuilder(XContentBuilder content, Album album) throws IOException {
        XContentBuilder albumBuilder = content.startObject("album");
        albumBuilder.field("id", album.getId());
        albumBuilder.field("gid", album.getGid());
        albumBuilder.field("name", album.getName());
        if (album.getTypeId() != null) {
            albumBuilder.field("type_id", album.getTypeId());
            albumBuilder.field("type_name", album.getTypeName());
        }
        albumBuilder.field("year", album.getYear());
        fillRatingBuilder(albumBuilder, album.getRating());
        if ((album.getTags() != null) && !album.getTags().isEmpty()) {
            albumBuilder.array("tags", album.getTags().toArray(new String[album.getTags().size()]));
        }
        fillArtistBuilder(albumBuilder, album.getArtist());
        albumBuilder.endObject();

    }

    private void fillArtistBuilder(XContentBuilder content, Artist artist) throws IOException {
        XContentBuilder artistBuilder = content.startObject("artist");
        artistBuilder.field("id", artist.getId());
        artistBuilder.field("gid", artist.getGid());
        artistBuilder.field("name", artist.getName());
        if (artist.getTypeId() != null) {
            artistBuilder.field("type_id", artist.getTypeId());
            artistBuilder.field("type_name", artist.getTypeName());
        }
        artistBuilder.field("begin_date_year", artist.getBeginDateYear());
        artistBuilder.field("area_name", artist.getArea());
        if (artist.getGender() != null) {
            artistBuilder.field("gender", artist.getGender().getId());
            artistBuilder.field("gender_name", artist.getGender().getName());

        }
        if ((artist.getTags() != null) && !artist.getTags().isEmpty()) {
            artistBuilder.array("tags", artist.getTags().toArray(new String[artist.getTags().size()]));
        }

        fillRatingBuilder(artistBuilder, artist.getRating());
        artistBuilder.endObject();

    }

    private void fillRatingBuilder(XContentBuilder content, Rating rating) throws IOException {
        XContentBuilder ratingBuilder = content.startObject("rating");
        ratingBuilder.field("score", rating.getScore());
        ratingBuilder.field("count", rating.getCount());
        ratingBuilder.endObject();
    }

    @Override protected String getDocumentId(Release item) {
        return item.getGid();
    }

    @Override protected String getDocumentType(Release item) {
        return documentType;
    }


    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

}
