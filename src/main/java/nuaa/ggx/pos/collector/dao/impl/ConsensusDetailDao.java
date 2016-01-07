package nuaa.ggx.pos.collector.dao.impl;

import nuaa.ggx.pos.collector.dao.interfaces.IConsensusDetailDao;
import nuaa.ggx.pos.collector.model.TConsensusDetail;

import org.springframework.stereotype.Repository;

@Repository("ConsensusDetailDao")
public class ConsensusDetailDao extends BaseDao<TConsensusDetail> implements IConsensusDetailDao{

}
