package nuaa.ggx.pos.collector.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface ICollectComment {
	@WebMethod
	public Integer collectComment(@WebParam(name = "url") String url);
}
