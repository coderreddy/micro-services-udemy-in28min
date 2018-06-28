package com.kris.udemy.microservicesspnewlearn;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFilter;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;

@ApiModel(description="Information about Users")
@Entity
//Can be used to ignore certain feilds in the response
//@JsonIgnoreProperties(value={"dob"})
//@JsonFilter(value="Filter")
public class User {
	
	@Id
	@GeneratedValue//(strategy=GenerationType.AUTO)
	private int ID;
	
	//to define the doc in the swagger
	@ApiModelProperty(notes="Name cannot be less than two chars")
	@Size(min=2,message="Name cannot be less than two chars")
	private String firstname;
	
	@Temporal(TemporalType.DATE)
	@Past
	private Date dob;
	
	@OneToMany(mappedBy="user")
	private List<Post> post = new ArrayList<Post>();
	
	public int getID() {
		return ID;
	}

	@ApiOperation(produces = "none", value = "API Operation desc")
	public void setID(int iD) {
		ID = iD;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public List<Post> getPost() {
		return post;
	}

	public void setPost(List<Post> post) {
		this.post = post;
	}

}
