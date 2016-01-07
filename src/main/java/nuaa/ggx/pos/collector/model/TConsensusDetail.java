package nuaa.ggx.pos.collector.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

/**
 * TConsensusDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_consensus_detail", catalog = "nuaacempos", uniqueConstraints = @UniqueConstraint(columnNames = "div_id"))
public class TConsensusDetail implements java.io.Serializable {

	// Fields

	private Integer consensusId;
	private TConsensus TConsensus;
	private String divId;
	private String userId;
	private String cmtUid;
	private String sharpTopic;
	private String blankTitle;
	private String oriUrl;
	private String forwardReason;
	private Integer praiseNum;
	private Integer forwardNum;
	private Integer cmtNum;
	private Integer emotionPole;
	private Integer impactValue;
	private Timestamp version;

	// Constructors

	/** default constructor */
	public TConsensusDetail() {
	}

	/** minimal constructor */
	public TConsensusDetail(Integer consensusId, TConsensus TConsensus) {
		this.consensusId = consensusId;
		this.TConsensus = TConsensus;
	}

	/** full constructor */
	public TConsensusDetail(Integer consensusId, TConsensus TConsensus,
			String divId, String userId, String cmtUid, String sharpTopic,
			String blankTitle, String oriUrl, String forwardReason,
			Integer praiseNum, Integer forwardNum, Integer cmtNum,
			Integer emotionPole, Integer impactValue, Timestamp version) {
		this.consensusId = consensusId;
		this.TConsensus = TConsensus;
		this.divId = divId;
		this.userId = userId;
		this.cmtUid = cmtUid;
		this.sharpTopic = sharpTopic;
		this.blankTitle = blankTitle;
		this.oriUrl = oriUrl;
		this.forwardReason = forwardReason;
		this.praiseNum = praiseNum;
		this.forwardNum = forwardNum;
		this.cmtNum = cmtNum;
		this.emotionPole = emotionPole;
		this.impactValue = impactValue;
		this.version = version;
	}

	// Property accessors
	@Id
	@Column(name = "consensus_id", unique = true, nullable = false)
	@GenericGenerator(name ="pkGenerator",strategy="foreign" ,parameters={@Parameter(name="property",value="TConsensus")})
    @GeneratedValue(generator="pkGenerator")
	public Integer getConsensusId() {
		return this.consensusId;
	}

	public void setConsensusId(Integer consensusId) {
		this.consensusId = consensusId;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@PrimaryKeyJoinColumn
	public TConsensus getTConsensus() {
		return this.TConsensus;
	}

	public void setTConsensus(TConsensus TConsensus) {
		this.TConsensus = TConsensus;
	}

	@Column(name = "div_id", length = 50)
	public String getDivId() {
		return this.divId;
	}

	public void setDivId(String divId) {
		this.divId = divId;
	}

	@Column(name = "user_id", length = 50)
	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Column(name = "cmt_uid", length = 50)
	public String getCmtUid() {
		return this.cmtUid;
	}

	public void setCmtUid(String cmtUid) {
		this.cmtUid = cmtUid;
	}

	@Column(name = "sharp_topic")
	public String getSharpTopic() {
		return this.sharpTopic;
	}

	public void setSharpTopic(String sharpTopic) {
		this.sharpTopic = sharpTopic;
	}

	@Column(name = "blank_title")
	public String getBlankTitle() {
		return this.blankTitle;
	}

	public void setBlankTitle(String blankTitle) {
		this.blankTitle = blankTitle;
	}

	@Column(name = "ori_url")
	public String getOriUrl() {
		return this.oriUrl;
	}

	public void setOriUrl(String oriUrl) {
		this.oriUrl = oriUrl;
	}

	@Column(name = "forward_reason")
	public String getForwardReason() {
		return this.forwardReason;
	}

	public void setForwardReason(String forwardReason) {
		this.forwardReason = forwardReason;
	}

	@Column(name = "praise_num")
	public Integer getPraiseNum() {
		return this.praiseNum;
	}

	public void setPraiseNum(Integer praiseNum) {
		this.praiseNum = praiseNum;
	}

	@Column(name = "forward_num")
	public Integer getForwardNum() {
		return this.forwardNum;
	}

	public void setForwardNum(Integer forwardNum) {
		this.forwardNum = forwardNum;
	}

	@Column(name = "cmt_num")
	public Integer getCmtNum() {
		return this.cmtNum;
	}

	public void setCmtNum(Integer cmtNum) {
		this.cmtNum = cmtNum;
	}

	@Column(name = "emotion_pole")
	public Integer getEmotionPole() {
		return this.emotionPole;
	}

	public void setEmotionPole(Integer emotionPole) {
		this.emotionPole = emotionPole;
	}

	@Column(name = "impact_value")
	public Integer getImpactValue() {
		return this.impactValue;
	}

	public void setImpactValue(Integer impactValue) {
		this.impactValue = impactValue;
	}

	@Column(name = "version", length = 19)
	public Timestamp getVersion() {
		return this.version;
	}

	public void setVersion(Timestamp version) {
		this.version = version;
	}

}