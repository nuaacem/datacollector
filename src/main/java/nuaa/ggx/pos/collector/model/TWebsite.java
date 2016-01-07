package nuaa.ggx.pos.collector.model;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * TWebsite entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_website", catalog = "nuaacempos")
public class TWebsite implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -671689333408359472L;
	private Integer id;
	private Integer collectWebsiteId;
	private String websiteName;
	private String url;
	private String type;
	private Integer updateNum;
	private Timestamp updateTime;
	private Boolean isPublic;
	private Integer state;
	private Set<TSubject> TSubjects = new HashSet<TSubject>(0);

	// Constructors

	/** default constructor */
	public TWebsite() {
	}

	public TWebsite(Integer id) {
		this.id = id;
	}
	
	/** full constructor */
	public TWebsite(String websiteName, String url, String type,
			Integer updateNum,Timestamp updateTime, Boolean isPublic, 
			Integer state, Set<TSubject> TSubjects) {
		this.websiteName = websiteName;
		this.url = url;
		this.type = type;
		this.updateNum = updateNum;
		this.updateTime = updateTime;
		this.isPublic = isPublic;
		this.state = state;
		this.TSubjects = TSubjects;
	}

	// Property accessors
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "collect_website_id")
	public Integer getCollectWebsiteId() {
		return collectWebsiteId;
	}

	public void setCollectWebsiteId(Integer collectWebsiteId) {
		this.collectWebsiteId = collectWebsiteId;
	}

	@Column(name = "website_name", length = 50)
	public String getWebsiteName() {
		return this.websiteName;
	}

	public void setWebsiteName(String websiteName) {
		this.websiteName = websiteName;
	}

	@Column(name = "url", length = 50)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "type", length = 5)
	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Column(name = "updateNum")
	public Integer getUpdateNum() {
		return this.updateNum;
	}

	public void setUpdateNum(Integer updateNum) {
		this.updateNum = updateNum;
	}

	@Column(name = "updateTime")		
	public Timestamp getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "isPublic", columnDefinition = "BIT", length = 1)
	public Boolean getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TWebsites")
	public Set<TSubject> getTSubjects() {
		return this.TSubjects;
	}

	public void setTSubjects(Set<TSubject> TSubjects) {
		this.TSubjects = TSubjects;
	}

}