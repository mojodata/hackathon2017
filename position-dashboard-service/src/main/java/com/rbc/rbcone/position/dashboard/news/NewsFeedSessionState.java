package com.rbc.rbcone.position.dashboard.news;

import java.util.Date;

import org.springframework.web.socket.WebSocketSession;

class NewsFeedSessionState {

	private String currentTopic;
	private String previousTopic;
	private Date latestNewsDate;
	private WebSocketSession session;

	String getPreviousTopic() {
		return previousTopic;
	}

	String getCurrentTopic() {
		return currentTopic;
	}

	void setCurrentTopic(String currentTopic) {
		if (this.currentTopic == null) {
			this.currentTopic = currentTopic;
		} else if (this.currentTopic != null && !this.currentTopic.equals(currentTopic)) {
			this.previousTopic = this.currentTopic;
			this.currentTopic = currentTopic;
			this.latestNewsDate = null;
		}
	}

	Date getLatestNewsDate() {
		return latestNewsDate;
	}

	void setLatestNewsDate(Date latestNewsDate) {
		this.latestNewsDate = latestNewsDate;
	}

	WebSocketSession getSession() {
		return session;
	}

	void setSession(WebSocketSession session) {
		this.session = session;
	}

}