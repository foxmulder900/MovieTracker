package hello.users;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.Id;

public class UserType {
	@Id
	private String userId;
	private String firstName;
	private String lastName;
	private String email;
	
	private List<String> moviesWatched;
	private List<String> moviesToWatch;
	
	public UserType(){
		this.moviesWatched = new ArrayList<String>();
		this.moviesToWatch = new ArrayList<String>();
	}
	
	//getters
	public String getUserId() {
		return userId;
	}
	public String getFirstName() {
		return firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public String getEmail() {
		return email;
	}
	public String getFullName(){
		return this.firstName +' '+this.lastName;
	}
	public List<String> getWatchedList() {
		return this.moviesWatched;
	}
	public List<String> getToWatchList() {
		return this.moviesToWatch;
	}
	
	//setters
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	//other methods
	public void addWatchedMovie(String movieId){
		this.moviesWatched.add(movieId);
	}
	public void addMovieToWatch(String movieId){
		this.moviesToWatch.add(movieId);
	}
}
