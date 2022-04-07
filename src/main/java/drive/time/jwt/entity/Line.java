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
//	@Column
//	private String shift_one;
//	@Column
//	private String shift_two;
//	@Column
//	private String shift_three;
//	
//	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//	@JoinColumn(name = "shift_one_id", referencedColumnName = "id") 
//	private Driver shift_one;
//	
//	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//	@JoinColumn(name = "shift_two_id", referencedColumnName = "id") 
//	private Driver shift_two;
//	
//	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//	@JoinColumn(name = "shift_three_id", referencedColumnName = "id") 
//	private Driver shift_three;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.REFRESH})
	@JoinColumn(name = "user_id", referencedColumnName = "id") 
	private User user;
	
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(name = "drivers_shifts", joinColumns = @JoinColumn(name = "publiclines_id"), inverseJoinColumns = @JoinColumn(name = "driver_id"))
	private List<Driver> drivers;
	
//	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//	private Driver drivers;
	
//	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
//	private List<Driver> drivers;

	
	public Line() {
		
	}

//	public Line(String name_of_line, Time start_time, Time end_time, Time total_time, Integer num_drivers,
//			String shift_one, String shift_two, String shift_three, Driver drivers) {
//		
//		this.name_of_line = name_of_line;
//		this.start_time = start_time;
//		this.end_time = end_time;
//		this.total_time = total_time;
//		this.num_drivers = num_drivers;
//		this.shift_one = shift_one;
//		this.shift_two = shift_two;
//		this.shift_three = shift_three;
//		this.drivers = drivers;
//	}
	
//	public Line(String name_of_line, Time start_time, Time end_time, Time total_time, Integer num_drivers,
//			String shift_one, String shift_two, String shift_three) {
//		super();
//		this.name_of_line = name_of_line;
//		this.start_time = start_time;
//		this.end_time = end_time;
//		this.total_time = total_time;
//		this.num_drivers = num_drivers;
//		this.shift_one = shift_one;
//		this.shift_two = shift_two;
//		this.shift_three = shift_three;
//	}
	
//	public Line(String name_of_line, LocalTime start_time, LocalTime end_time, LocalTime total_time, Integer num_drivers, String shift_one,
//			String shift_two, String shift_three, List<Driver> drivers) {
//		this.name_of_line = name_of_line;
//		this.start_time = start_time;
//		this.end_time = end_time;
//		this.total_time = total_time;
//		this.num_drivers = num_drivers;
//		this.shift_one = shift_one;
//		this.shift_two = shift_two;
//		this.shift_three = shift_three;
//	}
	
//	public Line(String name_of_line, LocalTime start_time, LocalTime end_time, LocalTime total_time, Integer num_drivers,
//			String shift_one, String shift_two, String shift_three, User user) {
//			this.name_of_line = name_of_line;
//			this.start_time = start_time;
//			this.end_time = end_time;
//			this.total_time = total_time;
//			this.num_drivers = num_drivers;
//			this.shift_one = shift_one;
//			this.shift_two = shift_two;
//			this.shift_three = shift_three;
//			this.user = user;
//	}
	
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

//	public String getShift_one() {
//		return shift_one;
//	}
//
//	public void setShift_one(String shift_one) {
//		this.shift_one = shift_one;
//	}
//
//	public String getShift_two() {
//		return shift_two;
//	}
//
//	public void setShift_two(String shift_two) {
//		this.shift_two = shift_two;
//	}
//
//	public String getShift_three() {
//		return shift_three;
//	}
//
//	public void setShift_three(String shift_three) {
//		this.shift_three = shift_three;
//	}
	

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

//	public Driver getShift_one() {
//		return shift_one;
//	}
//
//	public void setShift_one(Driver shift_one) {
//		this.shift_one = shift_one;
//	}
//
//	public Driver getShift_two() {
//		return shift_two;
//	}
//
//	public void setShift_two(Driver shift_two) {
//		this.shift_two = shift_two;
//	}
//
//	public Driver getShift_three() {
//		return shift_three;
//	}
//
//	public void setShift_three(Driver shift_three) {
//		this.shift_three = shift_three;
//	}
	
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
	
	

//	public List<Driver> getDrivers() {
//		return drivers;
//	}
//
//	public void setDrivers(List<Driver> drivers) {
//		this.drivers = drivers;
//	}
	
	

//	public Driver getDrivers() {
//		return drivers;
//	}
//
//	public void setDrivers(Driver drivers) {
//		this.drivers = drivers;
//	}
//
//	@Override
//	public String toString() {
//		return "Line [id=" + id + ", name_of_line=" + name_of_line + ", start_time=" + start_time + ", end_time="
//				+ end_time + ", total_time=" + total_time + ", num_drivers=" + num_drivers + ", shift_one=" + shift_one
//				+ ", shift_two=" + shift_two + ", shift_three=" + shift_three + ", drivers=" + drivers + "]";
//	}
//	
	
	
}
