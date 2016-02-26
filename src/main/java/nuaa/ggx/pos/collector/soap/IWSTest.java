package nuaa.ggx.pos.collector.soap;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public interface IWSTest {
	@WebMethod
	public String sayHello(@WebParam(name="name") String name);
}
