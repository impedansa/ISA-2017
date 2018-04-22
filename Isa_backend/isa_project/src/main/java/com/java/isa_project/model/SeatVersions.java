package com.java.isa_project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Version;

import lombok.Data;

@Entity
@Data
public class SeatVersions {

	@GeneratedValue
	@Id
	private int id;

	@Column(nullable = false)
	private String date;

	@Version
	private long version;

	public long getVersion() {
		return version;
	}

	public void setVersion(long version) {
		this.version = version;
	}
}
