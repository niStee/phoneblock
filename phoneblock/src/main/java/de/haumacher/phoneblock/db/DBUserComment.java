/*
 * Copyright (c) 2023 Bernhard Haumacher et al. All Rights Reserved.
 */
package de.haumacher.phoneblock.db;

import de.haumacher.phoneblock.app.api.model.Rating;
import de.haumacher.phoneblock.app.api.model.UserComment;

/**
 * DB data class for {@link UserComment}.
 */
public class DBUserComment extends UserComment {

	/** 
	 * Creates a {@link DBUserComment}.
	 */
	public DBUserComment(String id, String phone, Rating rating, String comment, String lang, String service, long created, int up, int down, Long userId) {
		setId(id);
		setPhone(phone);
		setRating(rating);
		setComment(comment);
		setLang(lang);
		setService(service);
		setCreated(created);
		setUp(up);
		setDown(down);
		setUserId(userId);
	}
	
}
