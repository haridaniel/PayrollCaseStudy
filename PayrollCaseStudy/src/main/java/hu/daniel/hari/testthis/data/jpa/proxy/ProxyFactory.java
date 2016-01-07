package hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy;

import javax.inject.Inject;

import com.google.inject.Singleton;

import hu.daniel.hari.exercises.cleanarchitecture.payrollcasestudy.interfaceadapters.database.jpa.proxy.util.autobind.AutoBindedProxyFactory;

@Singleton
public class ProxyFactory {

	@Inject private AutoBindedProxyFactory autoBindedProxyFactory;
	
	public <T> T create(Class<T> proxyClass, Object jpaEntity) {
		return autoBindedProxyFactory.create(proxyClass, jpaEntity);
	}

}
