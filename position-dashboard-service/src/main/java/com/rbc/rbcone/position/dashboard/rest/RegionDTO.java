package com.rbc.rbcone.position.dashboard.rest;

import java.math.BigDecimal;

public final class RegionDTO {

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