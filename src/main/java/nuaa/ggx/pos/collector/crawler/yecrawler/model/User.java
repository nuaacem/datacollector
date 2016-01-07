package nuaa.ggx.pos.collector.crawler.yecrawler.model;

import java.util.List;

public class User {
	private String uid;
	private String name;
	private List<String> commentUids;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<String> getCommentUids() {
		return commentUids;
	}
	public void setCommentUids(List<String> commentUids) {
		this.commentUids = commentUids;
	}
	
}
