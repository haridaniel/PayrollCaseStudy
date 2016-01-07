package hu.daniel.hari.testthis.data.jpa.proxy;

import javax.inject.Inject;

import com.google.inject.Singleton;

import hu.daniel.hari.testthis.data.jpa.proxy.util.autobind.AutoBindedProxyFactory;

@Singleton
public class ProxyFactory {

	@Inject private AutoBindedProxyFactory autoBindedProxyFactory;
	
	public ProxyFactory() {
		// TODO Auto-generated constructor stub
		System.out.println();
	}
	
	public <T> T create(Class<T> proxyClass, Object jpaEntity) {
		return autoBindedProxyFactory.create(proxyClass, jpaEntity);
	}

}
