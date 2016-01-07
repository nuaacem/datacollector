package nuaa.ggx.pos.collector.service.interfaces;

import java.util.List;

import nuaa.ggx.pos.collector.dao.interfaces.IBaseDao;

public interface IBaseService<T,IDaoType extends IBaseDao<T>> {
	public T getById(Integer id);
	public T loadById(Integer id);
	public void save(T t);
	public void delete(Integer id);
	public void delete(T t);
	public void update(T t);
	public T merge(T t);
	public List<T> listAll();
}
