package guestbook;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jdo.TransactionAwarePersistenceManagerFactoryProxy;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class JdoGuestbookDao extends AbstractJdoDao implements GuestbookDao {
	@Autowired
	public JdoGuestbookDao(
			final TransactionAwarePersistenceManagerFactoryProxy pmf) {
		super(pmf);
	}

	@Override
	@Transactional
	public Greeting store(final Greeting greeting) {
		return getPersistenceManager().makePersistent(greeting);
	}

	@Override
	@Transactional
	@SuppressWarnings("unchecked")
	public List<Greeting> getAll() {
		return (List<Greeting>) getPersistenceManager()
				.newQuery(Greeting.class).execute();
	}
}