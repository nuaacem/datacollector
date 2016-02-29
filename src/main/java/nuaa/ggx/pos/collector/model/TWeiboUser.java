package nuaa.ggx.pos.collector.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TWeiboUser entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_weibo_user", catalog = "nuaacempos")
public class TWeiboUser implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2746039475464444520L;
	private String uid;
	private String nickname;
	private Integer type;

	// Constructors

	/** default constructor */
	public TWeiboUser() {
	}

	/** minimal constructor */
	public TWeiboUser(String uid) {
		this.uid = uid;
	}

	/** full constructor */
	public TWeiboUser(String uid, String nickname, Integer type) {
		this.uid = uid;
		this.nickname = nickname;
		this.type = type;
	}

	// Property accessors
	@Id
	@Column(name = "uid", unique = true, nullable = false, length = 20)
	public String getUid() {
		return this.uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Column(name = "nickname", length = 100)
	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	@Column(name = "type")
	public Integer getType() {
		return this.type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

}