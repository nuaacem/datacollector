package nuaa.ggx.pos.collector.dao.impl;

import nuaa.ggx.pos.collector.dao.interfaces.ISubjectDao;
import nuaa.ggx.pos.collector.model.TSubject;

import org.springframework.stereotype.Repository;

@Repository("SubjectDao")
public class SubjectDao extends BaseDao<TSubject> implements ISubjectDao{

}
