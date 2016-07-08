package com.orange.spring.demo.biz.persistence.repository;

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

import com.orange.spring.demo.biz.persistence.model.FavoriteDB;
import com.orange.spring.demo.biz.persistence.model.RequestDB;
import com.orange.spring.demo.biz.persistence.model.SignDB;
import com.orange.spring.demo.biz.persistence.model.UserDB;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SignRepository extends CrudRepository<SignDB, Long> {
    List<SignDB> findByName(String name);

    @Query("select distinct s FROM SignDB s inner join s.favorites favorite where favorite = :favoriteDB")
    List<SignDB> findByFavorite(@Param("favoriteDB") FavoriteDB favoriteDB);

    @Query("select s FROM SignDB s inner join s.associates sign where sign = :signDB")
    List<SignDB> findReferenceByBySign(@Param("signDB") SignDB signDB);

    @Query("select s FROM SignDB s inner join s.referenceBy sign where sign = :signDB")
    List<SignDB> findAssociatesBySign(@Param("signDB") SignDB signDB);

}
