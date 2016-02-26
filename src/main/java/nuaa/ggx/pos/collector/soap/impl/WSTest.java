package nuaa.ggx.pos.collector.soap.impl;

import nuaa.ggx.pos.collector.soap.IWSTest;

public class WSTest implements IWSTest {

	@Override
	public String sayHello(String name) {
		return "Hello " + name;
	}

}
