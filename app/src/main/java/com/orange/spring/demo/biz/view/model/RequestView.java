package com.orange.spring.demo.biz.view.model;

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

import com.orange.spring.demo.biz.domain.Communities;
import com.orange.spring.demo.biz.domain.Community;
import com.orange.spring.demo.biz.domain.Request;
import com.orange.spring.demo.biz.domain.Requests;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RequestView {
  private long id;
  private String name;
  private Date requestDate;

  public Request toRequest() {
    return new Request(id, name, requestDate);
  }

  public static RequestView from(Request request) {
    return new RequestView(request.id, request.name, request.requestDate);
  }

  public static List<RequestView> from(Requests requests) {
    return requests.stream()
            .map(RequestView::from)
            .collect(Collectors.toList());
  }
}
