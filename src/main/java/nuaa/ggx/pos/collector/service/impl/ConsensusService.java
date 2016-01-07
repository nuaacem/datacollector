package nuaa.ggx.pos.collector.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import nuaa.ggx.pos.collector.dao.interfaces.IConsensusDao;
import nuaa.ggx.pos.collector.model.TConsensus;
import nuaa.ggx.pos.collector.service.interfaces.IConsensusService;

@Service("ConsensusService")
public class ConsensusService extends BaseService<TConsensus, IConsensusDao> implements IConsensusService{

	@Autowired
	public ConsensusService(IConsensusDao consensusDao) {
		super(consensusDao);
	}

}
