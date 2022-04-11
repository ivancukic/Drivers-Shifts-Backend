package drive.time.jwt.entity;

import java.time.LocalTime;
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

import org.springframework.format.annotation.DateTimeFormat;


@Entity
@Table(name = "publiclines")
public class Line {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column
	private String name_of_line;
	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime start_time;
	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime end_time;
	@Column
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private LocalTime total_time;
	@Column
	private Integer num_drivers;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "user_id", referencedColumnName = "id") 
	private User user;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "drivers_shifts", joinColumns = @JoinColumn(name = "publiclines_id"), inverseJoinColumns = @JoinColumn(name = "driver_id"))
	private List<Driver> drivers;
	

	public Line() {
		
	}

	
	public Line(String name_of_line, LocalTime start_time, LocalTime end_time) {
		this.name_of_line = name_of_line;
		this.start_time = start_time;
		this.end_time = end_time;
	}

	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}

	public String getName_of_line() {
		return name_of_line;
	}

	public void setName_of_line(String name_of_line) {
		this.name_of_line = name_of_line;
	}

	public LocalTime getStart_time() {
		return start_time;
	}

	public void setStart_time(LocalTime start_time) {
		this.start_time = start_time;
	}

	public LocalTime getEnd_time() {
		return end_time;
	}

	public void setEnd_time(LocalTime end_time) {
		this.end_time = end_time;
	}

	public LocalTime getTotal_time() {
		return total_time;
	}

	public void setTotal_time(LocalTime total_time) {
		this.total_time = total_time;
	}

	public Integer getNum_drivers() {
		return num_drivers;
	}

	public void setNum_drivers(Integer num_drivers) {
		this.num_drivers = num_drivers;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
	
	public List<Driver> getDrivers() {
		return drivers;
	}

	public void setDrivers(List<Driver> drivers) {
		this.drivers = drivers;
	}

	@Override
	public String toString() {
		return "Line [id=" + id + ", name_of_line=" + name_of_line + ", start_time=" + start_time + ", end_time="
				+ end_time + ", total_time=" + total_time + ", num_drivers=" + num_drivers + ", user=" + user
				+ ", drivers=" + drivers + "]";
	}
	
	
}
