package com.rbc.rbcone.position.dashboard.model;

import java.math.BigDecimal;

public class Coordinate {

	private BigDecimal lat;
	private BigDecimal lng;
	
	public BigDecimal getLat() {
		return lat;
	}
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	public BigDecimal getLng() {
		return lng;
	}
	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}

}