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
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * TConsensus entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_consensus", catalog = "nuaacempos")
public class TConsensus implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -7818049887402932606L;
	private Integer id;
	private String title;
	private String url;
	private String summary;
	private String fromWebsite;
	private Integer typeId;
	private Timestamp collectTime;
	private Timestamp publishTime;
	private Timestamp updateTime;
	private Integer collectWebsiteId;
	private Integer state;
	private TConsensusDetail TConsensusDetail;

	// Constructors

	/** default constructor */
	public TConsensus() {
	}

	/** full constructor */
	public TConsensus(String title, String url, String summary,
			String fromWebsite, Integer typeId, Timestamp collectTime,
			Timestamp publishTime, Timestamp updateTime,
			Integer collectWebsiteId, Integer state,
			TConsensusDetail TConsensusDetail
			 ) {
		this.title = title;
		this.url = url;
		this.summary = summary;
		this.fromWebsite = fromWebsite;
		this.typeId = typeId;
		this.collectTime = collectTime;
		this.publishTime = publishTime;
		this.updateTime = updateTime;
		this.collectWebsiteId = collectWebsiteId;
		this.state = state;
		this.TConsensusDetail = TConsensusDetail;
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

	@Column(name = "title", length = 300)
	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "url", length = 255)
	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Column(name = "summary", length = 500)
	public String getSummary() {
		return this.summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@Column(name = "from_website", length = 50)
	public String getFromWebsite() {
		return this.fromWebsite;
	}

	public void setFromWebsite(String fromWebsite) {
		this.fromWebsite = fromWebsite;
	}

	@Column(name = "type_id")
	public Integer getTypeId() {
		return this.typeId;
	}

	public void setTypeId(Integer typeId) {
		this.typeId = typeId;
	}

	@Column(name = "collect_time", length = 19)
	public Timestamp getCollectTime() {
		return this.collectTime;
	}

	public void setCollectTime(Timestamp collectTime) {
		this.collectTime = collectTime;
	}

	@Column(name = "publish_time", length = 19)
	public Timestamp getPublishTime() {
		return this.publishTime;
	}

	public void setPublishTime(Timestamp publishTime) {
		this.publishTime = publishTime;
	}

	@Column(name = "update_time", length = 19)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "collect_website_id")
	public Integer getCollectWebsiteId() {
		return this.collectWebsiteId;
	}

	public void setCollectWebsiteId(Integer collectWebsiteId) {
		this.collectWebsiteId = collectWebsiteId;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "TConsensus")
	public TConsensusDetail getTConsensusDetail() {
		return this.TConsensusDetail;
	}

	public void setTConsensusDetail(TConsensusDetail TConsensusDetail) {
		this.TConsensusDetail = TConsensusDetail;
	}
}