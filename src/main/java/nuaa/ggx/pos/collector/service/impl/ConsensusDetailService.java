package nuaa.ggx.pos.collector.service.impl;

import nuaa.ggx.pos.collector.dao.interfaces.IConsensusDetailDao;
import nuaa.ggx.pos.collector.model.TConsensusDetail;
import nuaa.ggx.pos.collector.service.interfaces.IConsensusDetailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("ConsensusDetailService")
public class ConsensusDetailService extends BaseService<TConsensusDetail, IConsensusDetailDao> implements IConsensusDetailService{

	@Autowired
	public ConsensusDetailService(IConsensusDetailDao consensusDetailDao) {
		super(consensusDetailDao);
	}

}
