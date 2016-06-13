package test;

import com.fiap.dao.GenericDao;
import com.fiap.entity.User;

public class Test {

	public static void main(String[] args) {
		
		User user = new User();
		user.setPassword("112628");
		user.setName("caiopr");
		
		user.setCpf("3439394354439");
		
		GenericDao<User> dao= new GenericDao<>(User.class);
		try {
			dao.persist(user);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
