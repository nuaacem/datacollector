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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * TSubject entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_subject", catalog = "nuaacempss")
public class TSubject implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -5869055017666624055L;
	private Integer id;
	private String subjectName;
	private String subjectDesc;
	private Timestamp createTime;
	private Timestamp updateTime;
	private Integer updateNum;
	private Integer state;
	private Set<TKeyword> TKeywords = new HashSet<TKeyword>(0);
	private Set<TWebsite> TWebsites = new HashSet<TWebsite>(0);

	// Constructors

	/** default constructor */
	public TSubject() {
	}

	/** full constructor */
	public TSubject(String subjectName, String subjectDesc,
			Timestamp createTime, Timestamp updateTime, Integer updateNum,
			Integer state, Set<TKeyword> TKeywords,
			Set<TWebsite> TWebsites) {
		this.subjectName = subjectName;
		this.subjectDesc = subjectDesc;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.updateNum = updateNum;
		this.state = state;
		this.TKeywords = TKeywords;
		this.TWebsites = TWebsites;
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

	@Column(name = "subject_name", length = 20)
	public String getSubjectName() {
		return this.subjectName;
	}

	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}

	@Column(name = "subject_desc")
	public String getSubjectDesc() {
		return this.subjectDesc;
	}

	public void setSubjectDesc(String subjectDesc) {
		this.subjectDesc = subjectDesc;
	}

	@Column(name = "create_time", length = 19)
	public Timestamp getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	@Column(name = "update_time", length = 19)
	public Timestamp getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

	@Column(name = "update_num")
	public Integer getUpdateNum() {
		return this.updateNum;
	}

	public void setUpdateNum(Integer updateNum) {
		this.updateNum = updateNum;
	}

	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@OneToMany(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
	@JoinColumn(name="subject_id")
	public Set<TKeyword> getTKeywords() {
		return this.TKeywords;
	}

	public void setTKeywords(Set<TKeyword> TKeywords) {
		this.TKeywords = TKeywords;
	}
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "t_subject_website", catalog = "nuaacempss", joinColumns = { @JoinColumn(name = "subject_id", nullable = false, updatable = true) }, inverseJoinColumns = { @JoinColumn(name = "website_id", nullable = false, updatable = true) })
	public Set<TWebsite> getTWebsites() {
		return this.TWebsites;
	}

	public void setTWebsites(Set<TWebsite> TWebsites) {
		this.TWebsites = TWebsites;
	}

}