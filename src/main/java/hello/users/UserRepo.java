package hello.users;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import hello.utils.exceptions.AlreadyExists;

public interface UserRepo extends MongoRepository<UserType, String> {
	
	public List<UserType> findByFirstName(String firstName);
	public List<UserType> findByLastName(String lastName);
	public UserType findByUserId(String userId);
	
	default public void addUser(String userId, String firstName, String lastName) throws AlreadyExists{
		
		if(this.findByUserId(userId)!=null){
			throw new AlreadyExists();
		}
		
		UserType newUser = new UserType();
		newUser.setUserId(userId);
		newUser.setFirstName(firstName);
		newUser.setLastName(lastName);
		this.save(newUser);
	}
	
}