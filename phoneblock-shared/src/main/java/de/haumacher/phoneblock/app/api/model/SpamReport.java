package de.haumacher.phoneblock.app.api.model;

public class SpamReport extends de.haumacher.msgbuf.data.AbstractDataObject implements de.haumacher.msgbuf.observer.Observable, de.haumacher.msgbuf.xml.XmlSerializable {

	/**
	 * Creates a {@link de.haumacher.phoneblock.app.api.model.SpamReport} instance.
	 */
	public static de.haumacher.phoneblock.app.api.model.SpamReport create() {
		return new de.haumacher.phoneblock.app.api.model.SpamReport();
	}

	/** Identifier for the {@link de.haumacher.phoneblock.app.api.model.SpamReport} type in JSON format. */
	public static final String SPAM_REPORT__TYPE = "SpamReport";

	/** @see #getPhone() */
	public static final String PHONE__PROP = "phone";

	/** @see #getVotes() */
	public static final String VOTES__PROP = "votes";

	/** @see #getLastUpdate() */
	public static final String LAST_UPDATE__PROP = "lastUpdate";

	/** @see #getDateAdded() */
	public static final String DATE_ADDED__PROP = "dateAdded";

	/** @see #isArchived() */
	public static final String ARCHIVED__PROP = "archived";

	/** @see #isWhiteListed() */
	public static final String WHITE_LISTED__PROP = "whiteListed";

	/** @see #getCnt10() */
	public static final String CNT_10__PROP = "cnt10";

	/** @see #getVotes10() */
	public static final String VOTES_10__PROP = "votes10";

	/** @see #getCnt100() */
	public static final String CNT_100__PROP = "cnt100";

	/** @see #getVotes100() */
	public static final String VOTES_100__PROP = "votes100";

	private String _phone = "";

	private int _votes = 0;

	private long _lastUpdate = 0L;

	private long _dateAdded = 0L;

	private boolean _archived = false;

	private boolean _whiteListed = false;

	private int _cnt10 = 0;

	private int _votes10 = 0;

	private int _cnt100 = 0;

	private int _votes100 = 0;

	/**
	 * Creates a {@link SpamReport} instance.
	 *
	 * @see de.haumacher.phoneblock.app.api.model.SpamReport#create()
	 */
	protected SpamReport() {
		super();
	}

	public final String getPhone() {
		return _phone;
	}

	/**
	 * @see #getPhone()
	 */
	public de.haumacher.phoneblock.app.api.model.SpamReport setPhone(String value) {
		internalSetPhone(value);
		return this;
	}

	/** Internal setter for {@link #getPhone()} without chain call utility. */
	protected final void internalSetPhone(String value) {
		_listener.beforeSet(this, PHONE__PROP, value);
		_phone = value;
	}

	public final int getVotes() {
		return _votes;
	}

	/**
	 * @see #getVotes()
	 */
	public de.haumacher.phoneblock.app.api.model.SpamReport setVotes(int value) {
		internalSetVotes(value);
		return this;
	}

	/** Internal setter for {@link #getVotes()} without chain call utility. */
	protected final void internalSetVotes(int value) {
		_listener.beforeSet(this, VOTES__PROP, value);
		_votes = value;
	}

	public final long getLastUpdate() {
		return _lastUpdate;
	}

	/**
	 * @see #getLastUpdate()
	 */
	public de.haumacher.phoneblock.app.api.model.SpamReport setLastUpdate(long value) {
		internalSetLastUpdate(value);
		return this;
	}

	/** Internal setter for {@link #getLastUpdate()} without chain call utility. */
	protected final void internalSetLastUpdate(long value) {
		_listener.beforeSet(this, LAST_UPDATE__PROP, value);
		_lastUpdate = value;
	}

	public final long getDateAdded() {
		return _dateAdded;
	}

	/**
	 * @see #getDateAdded()
	 */
	public de.haumacher.phoneblock.app.api.model.SpamReport setDateAdded(long value) {
		internalSetDateAdded(value);
		return this;
	}

	/** Internal setter for {@link #getDateAdded()} without chain call utility. */
	protected final void internalSetDateAdded(long value) {
		_listener.beforeSet(this, DATE_ADDED__PROP, value);
		_dateAdded = value;
	}

	public final boolean isArchived() {
		return _archived;
	}

	/**
	 * @see #isArchived()
	 */
	public de.haumacher.phoneblock.app.api.model.SpamReport setArchived(boolean value) {
		internalSetArchived(value);
		return this;
	}

	/** Internal setter for {@link #isArchived()} without chain call utility. */
	protected final void internalSetArchived(boolean value) {
		_listener.beforeSet(this, ARCHIVED__PROP, value);
		_archived = value;
	}

	public final boolean isWhiteListed() {
		return _whiteListed;
	}

	/**
	 * @see #isWhiteListed()
	 */
	public de.haumacher.phoneblock.app.api.model.SpamReport setWhiteListed(boolean value) {
		internalSetWhiteListed(value);
		return this;
	}

	/** Internal setter for {@link #isWhiteListed()} without chain call utility. */
	protected final void internalSetWhiteListed(boolean value) {
		_listener.beforeSet(this, WHITE_LISTED__PROP, value);
		_whiteListed = value;
	}

	/**
	 * The number of phone numbers with the the same prefix but a different end digit that are also reported as SPAM.
	 */
	public final int getCnt10() {
		return _cnt10;
	}

	/**
	 * @see #getCnt10()
	 */
	public de.haumacher.phoneblock.app.api.model.SpamReport setCnt10(int value) {
		internalSetCnt10(value);
		return this;
	}

	/** Internal setter for {@link #getCnt10()} without chain call utility. */
	protected final void internalSetCnt10(int value) {
		_listener.beforeSet(this, CNT_10__PROP, value);
		_cnt10 = value;
	}

	/**
	 * The total number of votes against all phone numbers with the the same prefix but a different end digit.
	 */
	public final int getVotes10() {
		return _votes10;
	}

	/**
	 * @see #getVotes10()
	 */
	public de.haumacher.phoneblock.app.api.model.SpamReport setVotes10(int value) {
		internalSetVotes10(value);
		return this;
	}

	/** Internal setter for {@link #getVotes10()} without chain call utility. */
	protected final void internalSetVotes10(int value) {
		_listener.beforeSet(this, VOTES_10__PROP, value);
		_votes10 = value;
	}

	/**
	 * The number of phone numbers with the the same prefix but two different end digits that are also reported as SPAM.
	 *
	 * <p>
	 * This number only considers {@link #getCnt10() blocks of phone numbers} with a minimum fill-ratio.
	 * </p>
	 */
	public final int getCnt100() {
		return _cnt100;
	}

	/**
	 * @see #getCnt100()
	 */
	public de.haumacher.phoneblock.app.api.model.SpamReport setCnt100(int value) {
		internalSetCnt100(value);
		return this;
	}

	/** Internal setter for {@link #getCnt100()} without chain call utility. */
	protected final void internalSetCnt100(int value) {
		_listener.beforeSet(this, CNT_100__PROP, value);
		_cnt100 = value;
	}

	/**
	 * The total number of votes against all phone numbers with the the same prefix but two different end digits. 
	 *
	 * <p>
	 * This number only considers {@link #getCnt10() blocks of phone numbers} with a minimum fill-ratio.
	 * </p>
	 */
	public final int getVotes100() {
		return _votes100;
	}

	/**
	 * @see #getVotes100()
	 */
	public de.haumacher.phoneblock.app.api.model.SpamReport setVotes100(int value) {
		internalSetVotes100(value);
		return this;
	}

	/** Internal setter for {@link #getVotes100()} without chain call utility. */
	protected final void internalSetVotes100(int value) {
		_listener.beforeSet(this, VOTES_100__PROP, value);
		_votes100 = value;
	}

	protected de.haumacher.msgbuf.observer.Listener _listener = de.haumacher.msgbuf.observer.Listener.NONE;

	@Override
	public de.haumacher.phoneblock.app.api.model.SpamReport registerListener(de.haumacher.msgbuf.observer.Listener l) {
		internalRegisterListener(l);
		return this;
	}

	protected final void internalRegisterListener(de.haumacher.msgbuf.observer.Listener l) {
		_listener = de.haumacher.msgbuf.observer.Listener.register(_listener, l);
	}

	@Override
	public de.haumacher.phoneblock.app.api.model.SpamReport unregisterListener(de.haumacher.msgbuf.observer.Listener l) {
		internalUnregisterListener(l);
		return this;
	}

	protected final void internalUnregisterListener(de.haumacher.msgbuf.observer.Listener l) {
		_listener = de.haumacher.msgbuf.observer.Listener.unregister(_listener, l);
	}

	@Override
	public String jsonType() {
		return SPAM_REPORT__TYPE;
	}

	private static java.util.List<String> PROPERTIES = java.util.Collections.unmodifiableList(
		java.util.Arrays.asList(
			PHONE__PROP, 
			VOTES__PROP, 
			LAST_UPDATE__PROP, 
			DATE_ADDED__PROP, 
			ARCHIVED__PROP, 
			WHITE_LISTED__PROP, 
			CNT_10__PROP, 
			VOTES_10__PROP, 
			CNT_100__PROP, 
			VOTES_100__PROP));

	@Override
	public java.util.List<String> properties() {
		return PROPERTIES;
	}

	@Override
	public Object get(String field) {
		switch (field) {
			case PHONE__PROP: return getPhone();
			case VOTES__PROP: return getVotes();
			case LAST_UPDATE__PROP: return getLastUpdate();
			case DATE_ADDED__PROP: return getDateAdded();
			case ARCHIVED__PROP: return isArchived();
			case WHITE_LISTED__PROP: return isWhiteListed();
			case CNT_10__PROP: return getCnt10();
			case VOTES_10__PROP: return getVotes10();
			case CNT_100__PROP: return getCnt100();
			case VOTES_100__PROP: return getVotes100();
			default: return null;
		}
	}

	@Override
	public void set(String field, Object value) {
		switch (field) {
			case PHONE__PROP: internalSetPhone((String) value); break;
			case VOTES__PROP: internalSetVotes((int) value); break;
			case LAST_UPDATE__PROP: internalSetLastUpdate((long) value); break;
			case DATE_ADDED__PROP: internalSetDateAdded((long) value); break;
			case ARCHIVED__PROP: internalSetArchived((boolean) value); break;
			case WHITE_LISTED__PROP: internalSetWhiteListed((boolean) value); break;
			case CNT_10__PROP: internalSetCnt10((int) value); break;
			case VOTES_10__PROP: internalSetVotes10((int) value); break;
			case CNT_100__PROP: internalSetCnt100((int) value); break;
			case VOTES_100__PROP: internalSetVotes100((int) value); break;
		}
	}

	/** Reads a new instance from the given reader. */
	public static de.haumacher.phoneblock.app.api.model.SpamReport readSpamReport(de.haumacher.msgbuf.json.JsonReader in) throws java.io.IOException {
		de.haumacher.phoneblock.app.api.model.SpamReport result = new de.haumacher.phoneblock.app.api.model.SpamReport();
		result.readContent(in);
		return result;
	}

	@Override
	public final void writeTo(de.haumacher.msgbuf.json.JsonWriter out) throws java.io.IOException {
		writeContent(out);
	}

	@Override
	protected void writeFields(de.haumacher.msgbuf.json.JsonWriter out) throws java.io.IOException {
		super.writeFields(out);
		out.name(PHONE__PROP);
		out.value(getPhone());
		out.name(VOTES__PROP);
		out.value(getVotes());
		out.name(LAST_UPDATE__PROP);
		out.value(getLastUpdate());
		out.name(DATE_ADDED__PROP);
		out.value(getDateAdded());
		out.name(ARCHIVED__PROP);
		out.value(isArchived());
		out.name(WHITE_LISTED__PROP);
		out.value(isWhiteListed());
		out.name(CNT_10__PROP);
		out.value(getCnt10());
		out.name(VOTES_10__PROP);
		out.value(getVotes10());
		out.name(CNT_100__PROP);
		out.value(getCnt100());
		out.name(VOTES_100__PROP);
		out.value(getVotes100());
	}

	@Override
	protected void readField(de.haumacher.msgbuf.json.JsonReader in, String field) throws java.io.IOException {
		switch (field) {
			case PHONE__PROP: setPhone(de.haumacher.msgbuf.json.JsonUtil.nextStringOptional(in)); break;
			case VOTES__PROP: setVotes(in.nextInt()); break;
			case LAST_UPDATE__PROP: setLastUpdate(in.nextLong()); break;
			case DATE_ADDED__PROP: setDateAdded(in.nextLong()); break;
			case ARCHIVED__PROP: setArchived(in.nextBoolean()); break;
			case WHITE_LISTED__PROP: setWhiteListed(in.nextBoolean()); break;
			case CNT_10__PROP: setCnt10(in.nextInt()); break;
			case VOTES_10__PROP: setVotes10(in.nextInt()); break;
			case CNT_100__PROP: setCnt100(in.nextInt()); break;
			case VOTES_100__PROP: setVotes100(in.nextInt()); break;
			default: super.readField(in, field);
		}
	}

	/** XML element name representing a {@link de.haumacher.phoneblock.app.api.model.SpamReport} type. */
	public static final String SPAM_REPORT__XML_ELEMENT = "spam-report";

	/** XML attribute or element name of a {@link #getPhone} property. */
	private static final String PHONE__XML_ATTR = "phone";

	/** XML attribute or element name of a {@link #getVotes} property. */
	private static final String VOTES__XML_ATTR = "votes";

	/** XML attribute or element name of a {@link #getLastUpdate} property. */
	private static final String LAST_UPDATE__XML_ATTR = "last-update";

	/** XML attribute or element name of a {@link #getDateAdded} property. */
	private static final String DATE_ADDED__XML_ATTR = "date-added";

	/** XML attribute or element name of a {@link #isArchived} property. */
	private static final String ARCHIVED__XML_ATTR = "archived";

	/** XML attribute or element name of a {@link #isWhiteListed} property. */
	private static final String WHITE_LISTED__XML_ATTR = "white-listed";

	/** XML attribute or element name of a {@link #getCnt10} property. */
	private static final String CNT_10__XML_ATTR = "cnt-10";

	/** XML attribute or element name of a {@link #getVotes10} property. */
	private static final String VOTES_10__XML_ATTR = "votes-10";

	/** XML attribute or element name of a {@link #getCnt100} property. */
	private static final String CNT_100__XML_ATTR = "cnt-100";

	/** XML attribute or element name of a {@link #getVotes100} property. */
	private static final String VOTES_100__XML_ATTR = "votes-100";

	@Override
	public String getXmlTagName() {
		return SPAM_REPORT__XML_ELEMENT;
	}

	@Override
	public final void writeContent(javax.xml.stream.XMLStreamWriter out) throws javax.xml.stream.XMLStreamException {
		writeAttributes(out);
		writeElements(out);
	}

	/** Serializes all fields that are written as XML attributes. */
	protected void writeAttributes(javax.xml.stream.XMLStreamWriter out) throws javax.xml.stream.XMLStreamException {
		out.writeAttribute(PHONE__XML_ATTR, getPhone());
		out.writeAttribute(VOTES__XML_ATTR, Integer.toString(getVotes()));
		out.writeAttribute(LAST_UPDATE__XML_ATTR, Long.toString(getLastUpdate()));
		out.writeAttribute(DATE_ADDED__XML_ATTR, Long.toString(getDateAdded()));
		out.writeAttribute(ARCHIVED__XML_ATTR, Boolean.toString(isArchived()));
		out.writeAttribute(WHITE_LISTED__XML_ATTR, Boolean.toString(isWhiteListed()));
		out.writeAttribute(CNT_10__XML_ATTR, Integer.toString(getCnt10()));
		out.writeAttribute(VOTES_10__XML_ATTR, Integer.toString(getVotes10()));
		out.writeAttribute(CNT_100__XML_ATTR, Integer.toString(getCnt100()));
		out.writeAttribute(VOTES_100__XML_ATTR, Integer.toString(getVotes100()));
	}

	/** Serializes all fields that are written as XML elements. */
	protected void writeElements(javax.xml.stream.XMLStreamWriter out) throws javax.xml.stream.XMLStreamException {
		// No element fields.
	}

	/** Creates a new {@link de.haumacher.phoneblock.app.api.model.SpamReport} and reads properties from the content (attributes and inner tags) of the currently open element in the given {@link javax.xml.stream.XMLStreamReader}. */
	public static SpamReport readSpamReport_XmlContent(javax.xml.stream.XMLStreamReader in) throws javax.xml.stream.XMLStreamException {
		SpamReport result = new SpamReport();
		result.readContentXml(in);
		return result;
	}

	/** Reads properties from the content (attributes and inner tags) of the currently open element in the given {@link javax.xml.stream.XMLStreamReader}. */
	protected final void readContentXml(javax.xml.stream.XMLStreamReader in) throws javax.xml.stream.XMLStreamException {
		for (int n = 0, cnt = in.getAttributeCount(); n < cnt; n++) {
			String name = in.getAttributeLocalName(n);
			String value = in.getAttributeValue(n);

			readFieldXmlAttribute(name, value);
		}
		while (true) {
			int event = in.nextTag();
			if (event == javax.xml.stream.XMLStreamConstants.END_ELEMENT) {
				break;
			}
			assert event == javax.xml.stream.XMLStreamConstants.START_ELEMENT;

			String localName = in.getLocalName();
			readFieldXmlElement(in, localName);
		}
	}

	/** Parses the given attribute value and assigns it to the field with the given name. */
	protected void readFieldXmlAttribute(String name, String value) {
		switch (name) {
			case PHONE__XML_ATTR: {
				setPhone(value);
				break;
			}
			case VOTES__XML_ATTR: {
				setVotes(Integer.parseInt(value));
				break;
			}
			case LAST_UPDATE__XML_ATTR: {
				setLastUpdate(Long.parseLong(value));
				break;
			}
			case DATE_ADDED__XML_ATTR: {
				setDateAdded(Long.parseLong(value));
				break;
			}
			case ARCHIVED__XML_ATTR: {
				setArchived(Boolean.parseBoolean(value));
				break;
			}
			case WHITE_LISTED__XML_ATTR: {
				setWhiteListed(Boolean.parseBoolean(value));
				break;
			}
			case CNT_10__XML_ATTR: {
				setCnt10(Integer.parseInt(value));
				break;
			}
			case VOTES_10__XML_ATTR: {
				setVotes10(Integer.parseInt(value));
				break;
			}
			case CNT_100__XML_ATTR: {
				setCnt100(Integer.parseInt(value));
				break;
			}
			case VOTES_100__XML_ATTR: {
				setVotes100(Integer.parseInt(value));
				break;
			}
			default: {
				// Skip unknown attribute.
			}
		}
	}

	/** Reads the element under the cursor and assigns its contents to the field with the given name. */
	protected void readFieldXmlElement(javax.xml.stream.XMLStreamReader in, String localName) throws javax.xml.stream.XMLStreamException {
		switch (localName) {
			case PHONE__XML_ATTR: {
				setPhone(in.getElementText());
				break;
			}
			case VOTES__XML_ATTR: {
				setVotes(Integer.parseInt(in.getElementText()));
				break;
			}
			case LAST_UPDATE__XML_ATTR: {
				setLastUpdate(Long.parseLong(in.getElementText()));
				break;
			}
			case DATE_ADDED__XML_ATTR: {
				setDateAdded(Long.parseLong(in.getElementText()));
				break;
			}
			case ARCHIVED__XML_ATTR: {
				setArchived(Boolean.parseBoolean(in.getElementText()));
				break;
			}
			case WHITE_LISTED__XML_ATTR: {
				setWhiteListed(Boolean.parseBoolean(in.getElementText()));
				break;
			}
			case CNT_10__XML_ATTR: {
				setCnt10(Integer.parseInt(in.getElementText()));
				break;
			}
			case VOTES_10__XML_ATTR: {
				setVotes10(Integer.parseInt(in.getElementText()));
				break;
			}
			case CNT_100__XML_ATTR: {
				setCnt100(Integer.parseInt(in.getElementText()));
				break;
			}
			case VOTES_100__XML_ATTR: {
				setVotes100(Integer.parseInt(in.getElementText()));
				break;
			}
			default: {
				internalSkipUntilMatchingEndElement(in);
			}
		}
	}

	protected static final void internalSkipUntilMatchingEndElement(javax.xml.stream.XMLStreamReader in) throws javax.xml.stream.XMLStreamException {
		int level = 0;
		while (true) {
			switch (in.next()) {
				case javax.xml.stream.XMLStreamConstants.START_ELEMENT: level++; break;
				case javax.xml.stream.XMLStreamConstants.END_ELEMENT: if (level == 0) { return; } else { level--; break; }
			}
		}
	}

	/** Creates a new {@link SpamReport} and reads properties from the content (attributes and inner tags) of the currently open element in the given {@link javax.xml.stream.XMLStreamReader}. */
	public static SpamReport readSpamReport(javax.xml.stream.XMLStreamReader in) throws javax.xml.stream.XMLStreamException {
		in.nextTag();
		return de.haumacher.phoneblock.app.api.model.SpamReport.readSpamReport_XmlContent(in);
	}

}
