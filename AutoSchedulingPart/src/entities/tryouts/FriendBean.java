package entities.tryouts;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.faces.event.ActionEvent;

public class FriendBean {
	private List<Person> friends;
	private Person selectedFriend;
	private Set<Integer> rowsToUpdate;
	
	public FriendBean() {
		friends = Arrays.asList(new Person[] {
			new Person("David", "Gibb"),
			new Person("Steve", "Shaffer"),
			new Person("Monique", "Slack"),
			new Person("Kevin", "Happ"),
			new Person("Kimberly", "Herald"),
		});
		
		rowsToUpdate = new HashSet<Integer>();
	}

	public List<Person> getFriends() {
		return friends;
	}

	public void setFriends(List<Person> friends) {
		this.friends = friends;
	}
	
	public Person getSelectedFriend() {
		return selectedFriend;
	}

	public void setSelectedFriend(Person selectedFriend) {
		this.selectedFriend = selectedFriend;
	}

	public Set getRowsToUpdate() {
		return rowsToUpdate;
	}

	public void setRowsToUpdate(Set rowsToUpdate) {
		this.rowsToUpdate = rowsToUpdate;
	}

	public String rowClickAction() {
		System.out.println("Row clicked...");
		
		selectedFriend.setSelected(!selectedFriend.isSelected());
		
		rowsToUpdate.clear();
		rowsToUpdate.add(friends.indexOf(selectedFriend));
		
		return null;
	}
}
