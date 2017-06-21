package phonebook.jpa;

import javax.enterprise.inject.Model;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Model
public class EntityManagerProducer {

	@PersistenceContext(unitName = "phonebook")
	private EntityManager em;

	@Produces
	protected EntityManager createEntityManager() {
		return em;
	}
}
