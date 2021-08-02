package com.abc.todo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Size;


/**
 * Entity for Todo
 * @author vikas
 *
 */
@Entity
@Table(name = "todos")
public class Todo {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String userName;

	@Size(min = 5, message = "Enter at least 5 Characters...")
	private String description;
	
	private boolean completed;
	@Column(insertable = true, updatable = false)
	private LocalDateTime createdDate;
	private LocalDateTime updateDate;
	

	public Todo(long id,String userName, @Size(min = 5, message = "Enter at least 5 Characters...") String description,
			boolean completed, LocalDateTime createdDate, LocalDateTime updateDate) {
		super();
		this.id = id;
		this.userName = userName;
		this.description = description;
		this.completed = completed;
		this.createdDate = createdDate;
		this.updateDate = updateDate;
	}
	
	public Todo() {
		
	}
	@PrePersist
	 void onCreate() {
	 this.setCreatedDate(LocalDateTime.now());
	 this.setUpdateDate(LocalDateTime.now());
	 }
	 @PreUpdate
	 void onUpdate() {
	 this.setUpdateDate(LocalDateTime.now());
	 }

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

   public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}
}