package nuaa.ggx.pos.collector.service.impl;

import nuaa.ggx.pos.collector.dao.interfaces.ISubjectDao;
import nuaa.ggx.pos.collector.model.TSubject;
import nuaa.ggx.pos.collector.service.interfaces.ISubjectService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SubjectService")
public class SubjectService extends BaseService<TSubject, ISubjectDao> implements ISubjectService{

	@Autowired
	public SubjectService(ISubjectDao subjectDao) {
		super(subjectDao);
	}

}
