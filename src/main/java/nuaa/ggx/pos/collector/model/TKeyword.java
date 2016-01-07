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
 * TKeyword entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_keyword", catalog = "nuaacempss")
public class TKeyword implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 4186777384105551582L;
	private Integer id;
	private String keyword;
	private Integer updateNum;
	private Timestamp updateTime;
	private Integer state;
	private TSubject TSubject;
	private Set<TConsensus> TConsensuses = new HashSet<TConsensus>(0);

	// Constructors

	/** default constructor */
	public TKeyword() {
	}
	public TKeyword(String keyword) {
		this.keyword = keyword;
	}

	/** full constructor */
	public TKeyword(String keyword, Integer updateNum,
			Integer state, Set<TConsensus> TConsensuses) {
		this.keyword = keyword;
		this.updateNum = updateNum;
		this.state = state;
		this.TConsensuses = TConsensuses;
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

	@Column(name = "keyword", length = 20)
	public String getKeyword() {
		return this.keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Column(name = "update_num")
	public Integer getUpdateNum() {
		return this.updateNum;
	}

	public void setUpdateNum(Integer updateNum) {
		this.updateNum = updateNum;
	}
	
	@Column(name = "update_time", length = 19)
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}
	@Column(name = "state")
	public Integer getState() {
		return this.state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "subject_id")
	public TSubject getTSubject() {
		return TSubject;
	}
	public void setTSubject(TSubject tSubject) {
		this.TSubject = tSubject;
	}

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "t_consensus_keyword", catalog = "nuaacempss", joinColumns = { @JoinColumn(name = "keyword_id", nullable = false, updatable = false) }, inverseJoinColumns = { @JoinColumn(name = "consensus_id", nullable = false, updatable = false) })
	public Set<TConsensus> getTConsensuses() {
		return this.TConsensuses;
	}

	public void setTConsensuses(Set<TConsensus> TConsensuses) {
		this.TConsensuses = TConsensuses;
	}

}