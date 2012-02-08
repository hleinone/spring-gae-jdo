package guestbook;

import javax.jdo.PersistenceManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.TransactionAwarePersistenceManagerFactoryProxy;

public abstract class AbstractJdoDao {
	private final TransactionAwarePersistenceManagerFactoryProxy pmf;

	@Autowired
	public AbstractJdoDao(
			final TransactionAwarePersistenceManagerFactoryProxy pmf) {
		this.pmf = pmf;
	}

	public PersistenceManager getPersistenceManager() {
		return pmf.getObject().getPersistenceManager();
	}
}