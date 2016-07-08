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


import com.orange.spring.demo.biz.domain.Favorite;
import com.orange.spring.demo.biz.domain.Sign;
import com.orange.spring.demo.biz.domain.Signs;
import com.orange.spring.demo.biz.persistence.service.SignService;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
public class SignProfileView {
  private Sign sign;
  private List<Long> associateSignsIds;
  private List<Sign> allSignsWithoutCurrentSign;

  public SignProfileView(Sign signWithoutAssociatesReferenceBy, SignService signService) {
    sign = signWithoutAssociatesReferenceBy.loadAssociatesReferenceBy(signService);
    List<Long> associateIds = sign.associateSignsIds;
    associateIds.addAll(sign.refrenceBySignsIds);
    this.associateSignsIds = associateIds;

    List<Sign> listSignWithOutId = new ArrayList<>();
    for (Sign signe:signService.all().list()) {
      if (signe.id != sign.id) {
        listSignWithOutId.add(signe);
      }
    }
    this.allSignsWithoutCurrentSign = listSignWithOutId;

  }
}
