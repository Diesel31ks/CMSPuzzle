package hibernate.tables;

import hibernate.tables.userInfo.UserRole;
import hibernate.tables.userInfo.UserStatus;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User implements Serializable{
	
	private static final long serialVersionUID = -1698226571037727021L;
	@Id
	@Column(name = "u_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	@Column(name = "u_name")
	private String login;
	@Column(name = "u_firstname")
	private String firstName;
	@Column(name = "u_lastname")
	private String lastName;
	@Column(name = "u_email")
	private String email;
	@Column(name = "u_password")
	private String password;

	@Enumerated(EnumType.STRING)
	@Column (columnDefinition="enum('ADMINISTRATOR', 'CORRECTOR', 'EDITOR', 'AUTHOR', 'UNCONFIRMED')",name="u_role")
	private UserRole role;
	
	@Enumerated(EnumType.STRING)
	@Column (columnDefinition="enum('UNAVAILABLE','AVAILABLE')", name="u_status") 
	private UserStatus status;

	@OneToMany(mappedBy="author")	//ссылка на поле в объекте Content
	private Set<Content>content;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) throws NoSuchAlgorithmException, InvalidKeySpecException {
		this.password = password;
	}
	public UserRole getRole() {
		return role;
	}
	public void setRole(UserRole role) {
		this.role = role;
	}
	public UserStatus getStatus() {
		return status;
	}
	public void setStatus(UserStatus status) {
		this.status = status;
	}
	public Set<Content> getContent() {
		return content;
	}
	public void setContent(Set<Content> content) {
		this.content = content;
	}
	
	@Override
	public String toString() {
		return new StringBuffer().append("Users [id=").append(id)
				.append(", login=").append(login)
				.append(", firstName=").append(firstName)
				.append(", lastName=").append(lastName)
				.append(", email=").append(email)
				.append(", password=").append(password)
				.append(", role=").append(role)
				.append(", status=").append(status)
				.append(", content=").append(content)
				.append("]").toString();
	}
}
