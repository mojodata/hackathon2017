package com.rbc.rbcone.position.dashboard.news;

import java.util.Date;

class RssSessionState {
	
	private String currentTopic;
	private Date latestNewsDate;
	
	public String getCurrentTopic() {
		return currentTopic;
	}
	public void setCurrentTopic(String currentTopic) {
		this.currentTopic = currentTopic;
	}
	public Date getLatestNewsDate() {
		return latestNewsDate;
	}
	public void setLatestNewsDate(Date latestNewsDate) {
		this.latestNewsDate = latestNewsDate;
	}	

	
}