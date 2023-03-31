package com.orange.signsatwork.biz.persistence.model;

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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.orange.signsatwork.biz.domain.CommunityType;
import com.orange.signsatwork.biz.domain.FavoriteType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

// we want to save 'community' objects in the 'communities' DB table
@Table(name = "communities")
@Entity
// default constructor only exists for the sake of JPA
@NoArgsConstructor
@Getter
@Setter
public class CommunityDB {
    // An autogenerated id (unique for each user in the db)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    @NotNull
    private String name;

    private String descriptionText;

    private String descriptionVideo;

    @Enumerated(EnumType.STRING)
    private CommunityType type;

    @ManyToOne(fetch = FetchType.LAZY)
    private UserDB user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "communities_users", joinColumns = @JoinColumn(name = "communities_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "users_id", referencedColumnName = "id"))
    @JsonManagedReference
    private List<UserDB> users = new ArrayList<>();

    @ManyToMany(mappedBy = "communities", fetch = FetchType.LAZY)
    @JsonBackReference
    private List<FavoriteDB> favorites;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "communities_videos", joinColumns = @JoinColumn(name = "communities_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "videos_id", referencedColumnName = "id"))
    @JsonManagedReference
    @Fetch(value = FetchMode.SUBSELECT)
    private List<VideoDB> videos = new ArrayList<>();


    public CommunityDB(String name, CommunityType type) {
        this.name = name;
        this.type = type;
    }
}
