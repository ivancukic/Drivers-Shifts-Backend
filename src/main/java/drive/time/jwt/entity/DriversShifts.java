package drive.time.jwt.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "drivers_shifts")
public class DriversShifts {
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "publiclines_id")
	private Integer publiclinesId;
	
	@Column(name = "driver_id")
	private Integer driverId;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "shift")
	private Shift shift;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name = "user_id", referencedColumnName = "id") 
	private User user;
	
	public DriversShifts() {
		
	}
	
	public DriversShifts(Integer publiclinesId, Integer driverId, Shift shift) {
		this.publiclinesId = publiclinesId;
		this.driverId = driverId;
		this.shift = shift;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPubliclinesId() {
		return publiclinesId;
	}

	public void setPubliclinesId(Integer publiclinesId) {
		this.publiclinesId = publiclinesId;
	}

	public Integer getDriverId() {
		return driverId;
	}

	public void setDriverId(Integer driverId) {
		this.driverId = driverId;
	}

	public Shift getShift() {
		return shift;
	}

	public void setShift(Shift shift) {
		this.shift = shift;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "DriverShifts [id=" + id + ", publiclinesId=" + publiclinesId + ", driverId=" + driverId + ", shift="
				+ shift + ", user=" + user + "]";
	}

	
	
}
