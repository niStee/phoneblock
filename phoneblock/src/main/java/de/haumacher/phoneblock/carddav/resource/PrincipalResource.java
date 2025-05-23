/*
 * Copyright (c) 2022 Bernhard Haumacher et al. All Rights Reserved.
 */
package de.haumacher.phoneblock.carddav.resource;

import static de.haumacher.phoneblock.util.DomUtil.appendElement;

import java.util.List;

import javax.xml.namespace.QName;

import org.w3c.dom.Element;

import de.haumacher.phoneblock.carddav.CardDavServlet;
import de.haumacher.phoneblock.carddav.schema.CardDavSchema;
import de.haumacher.phoneblock.carddav.schema.DavSchema;
import de.haumacher.phoneblock.db.DBService;
import de.haumacher.phoneblock.util.DomUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * {@link Resource} representing a PhoneBlock user account.
 */
public class PrincipalResource extends Resource {

	private String _principal;

	/** 
	 * Creates a {@link PrincipalResource}.
	 */
	public PrincipalResource(String rootUrl, String resourcePath, String principal) {
		super(rootUrl, resourcePath);
		_principal = principal;
	}
	
	@Override
	public void propfind(HttpServletRequest req, Resource parent, Element multistatus, List<QName> properties) {
		String userAgent = req.getHeader("User-Agent");
		
		DBService.getInstance().updateLastAccess(_principal, System.currentTimeMillis(), userAgent);
		
		super.propfind(req, parent, multistatus, properties);
	}
	
	@Override
	public int fillProperty(HttpServletRequest req, Element propElement, QName property) {
		if (CardDavSchema.CARDDAV_ADDRESSBOOK_HOME_SET.equals(property)) {
			Element container = appendElement(propElement, CardDavSchema.CARDDAV_ADDRESSBOOK_HOME_SET);
			DomUtil.appendTextElement(container, DavSchema.DAV_HREF, url(CardDavServlet.ADDRESSES_PATH + _principal + "/"));
			return HttpServletResponse.SC_OK;
		}
		return super.fillProperty(req, propElement, property);
	}

}
