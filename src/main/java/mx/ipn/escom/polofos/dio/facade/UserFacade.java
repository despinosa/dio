/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package mx.ipn.escom.polofos.dio.facade;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.ipn.escom.polofos.dio.model.User;

/**
 *
 * @author crypt
 */
@Stateless
public class UserFacade extends AbstractFacade<User> {

  @SuppressWarnings("serial")
  static public class NoSuchUserException extends NoResultException {
    public NoSuchUserException(String message) {
      super(message);
    }
  }


  @PersistenceContext(unitName = "DioPU")
  private EntityManager em;

  @Override
  protected EntityManager getEntityManager() {
    return em;
  }

  public UserFacade() {
    super(User.class);
  }

  public User findByUsername(String username) throws NoSuchUserException {
    Query query = em.createNamedQuery("User.findByUsername");
    query.setParameter("username", username);
    try {
      return (User) query.getSingleResult();
    } catch (NoResultException ex) {
      throw new NoSuchUserException(username);
    }
  }

}
