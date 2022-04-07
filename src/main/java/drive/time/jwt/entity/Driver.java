package drive.time.jwt.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "drivers")
public class Driver {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name;
	@Column
	private Date dob;
	@Column 
	private Boolean active;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "category_id", referencedColumnName = "id") 
	private Category category;  
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "user_id", referencedColumnName = "id") 
	private User user;
	
	@JsonIgnore
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "drivers_shifts", joinColumns = @JoinColumn(name = "driver_id"), inverseJoinColumns = @JoinColumn(name = "publiclines_id"))
	private List<Line> lines;
	
	
	
	public Driver() {
		
	}

	public Driver(String name, Date dob, Boolean active) {
		this.name = name;
		this.dob = dob;
		this.active = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}
	
	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
	
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Line> getLines() {
		return lines;
	}

	public void setLines(List<Line> lines) {
		this.lines = lines;
	}

	@Override
	public String toString() {
		return "Driver [id=" + id + ", name=" + name + ", dob=" + dob + "]";
	}
	
	

}
