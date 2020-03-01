package core.springbackend.users.entities;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import core.springbackend.shared.entities.State;

@Entity
@Table(name="roles")
public class Role {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;

	@NotNull
	@Column(name="name")
	private String name;
	
	@Column(name="description")
	private String description;
	
	@NotNull
	@Column(name="state_id")
	private Long stateId;
	
//	@OneToMany(fetch = FetchType.LAZY, 
//			mappedBy = "rights",
//			targetEntity=Right.class)
	@Transient
    private Set<Right> rights;


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id", insertable=false, updatable=false)
	private State state;
	
//@OneToMany(fetch = FetchType.LAZY, 
//mappedBy = "roles",
//targetEntity=User.class)
//@Transient
//private Set<User> users;

	
	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getStateId() {
		return stateId;
	}

	public void setStateId(Long stateId) {
		this.stateId = stateId;
	}

	public Set<Right> getRights() {
		return rights;
	}

	public void setRights(Set<Right> rights) {
		this.rights = rights;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}


	
}
