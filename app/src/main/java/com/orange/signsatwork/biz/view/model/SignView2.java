package com.orange.signsatwork.biz.view.model;

/*
 * #%L
 * Signs at work
 * %%
 * Copyright (C) 2016 Orange
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as
 * published by the Free Software Foundation, either version 2 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/gpl-2.0.html>.
 * #L%
 */

import com.orange.signsatwork.biz.persistence.model.SignViewData;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SignView2 implements ComparableSign {
  private long id;
  private String name;
  private Date createDate;
  private long lastVideoId;
  private String url;
  private String pictureUri;
  private boolean signCreateAfterLastDateConnection;
  private boolean videoHasComment;

  public SignView2(SignViewData signViewData, boolean videoHasComment, boolean createdAfterLastConnection) {
    id = signViewData.id;
    name = signViewData.name;
    createDate = signViewData.createDate;
    lastVideoId = signViewData.lastVideoId;
    url = signViewData.url;
    pictureUri = signViewData.pictureUri;
    signCreateAfterLastDateConnection = createdAfterLastConnection;

    this.videoHasComment = videoHasComment;
  }


  @Override
  public long id() {
    return id;
  }

  @Override
  public boolean createdSinceLastConnexion() {
    return signCreateAfterLastDateConnection;
  }

  @Override
  public boolean modifiedSinceLastConnexion() {
    return videoHasComment;
  }

  public static boolean createdAfterLastConnection(Date createDate, Date lastConnection) {
    return (lastConnection != null) && createDate.compareTo(lastConnection) >= 0;
  }
}
