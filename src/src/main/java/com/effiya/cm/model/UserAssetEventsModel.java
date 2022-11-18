package com.effiya.cm.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * @author Abhishek Kumar Singh
 */

@Entity
@Table(name = "USER_ASSET_EVENTS")
public class UserAssetEventsModel extends BaseModel{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="use_sno")
	@GeneratedValue (strategy= GenerationType.TABLE, generator="useSeq")
	@SequenceGenerator(name="useSeq", sequenceName= "useSeq",allocationSize=1)
	private Integer useSno;

	@Column(name = "event_on")
	private String eventOn;
	
	@Column(name = "event_type")
	private String eventType;
	
	@Column(name = "event_impacted_id")
	private String eventImpactedId;
	
	@Column(name = "event_reason")
	private String event_reason;

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getEventImpactedId() {
		return eventImpactedId;
	}

	public void setEventImpactedId(String eventImpactedId) {
		this.eventImpactedId = eventImpactedId;
	}

	public Integer getUseSno() {
		return useSno;
	}

	public void setUseSno(Integer useSno) {
		this.useSno = useSno;
	}

	public String getEventOn() {
		return eventOn;
	}

	public void setEventOn(String eventOn) {
		this.eventOn = eventOn;
	}

	public String getEvent_reason() {
		return event_reason;
	}

	public void setEvent_reason(String event_reason) {
		this.event_reason = event_reason;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
