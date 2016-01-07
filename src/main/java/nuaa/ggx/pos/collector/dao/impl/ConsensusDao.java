package nuaa.ggx.pos.collector.dao.impl;

import nuaa.ggx.pos.collector.dao.interfaces.IConsensusDao;
import nuaa.ggx.pos.collector.model.TConsensus;

import org.springframework.stereotype.Repository;

@Repository("ConsensusDao")
public class ConsensusDao extends BaseDao<TConsensus> implements IConsensusDao{

}
