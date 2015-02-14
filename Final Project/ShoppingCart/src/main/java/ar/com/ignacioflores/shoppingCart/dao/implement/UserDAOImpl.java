package ar.com.ignacioflores.shoppingCart.dao.implement;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ar.com.ignacioflores.shoppingCart.dao.UserDAO;
import ar.com.ignacioflores.shoppingCart.model.User;

@Repository
@Transactional
public class UserDAOImpl implements UserDAO {
	
	private static final Logger logger = LoggerFactory.getLogger(UserDAOImpl.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}

	@Override
	public void addUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.save(user);
		logger.info("User saved successfully, User Details=" + user);
	}

	@Override
	public void updateUser(User user) {
		Session session = sessionFactory.getCurrentSession();
		session.update(user);
		logger.info("User updated successfully, User Details=" + user);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> listUsers() {
		Session session = sessionFactory.getCurrentSession();
		List<User> users = session.createQuery("from User").list();
		for(User user : users){
			logger.info("User List::" + user);
		}
		return users;
	}

	@Override
	public User getUserById(int id) {
		Session session = sessionFactory.openSession();		
		User user = (User) session.get(User.class, new Integer(id));
		logger.info("User loaded successfully, User details=" + user);
		return user;
	}

	@Override
	public void removeUser(int id) {
		Session session = sessionFactory.getCurrentSession();
		User user = (User) session.get(User.class, new Integer(id));
		if(null != user){
			session.delete(user);
		}
		logger.info("User deleted successfully, User details=" + user);
	}

}
