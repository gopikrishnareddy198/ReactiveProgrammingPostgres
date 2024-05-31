package com.reactive.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Employee implements Persistable<Integer> {

	@Id
	private Integer id;
	private String name;
	
	 @Transient
	 @JsonIgnore
	 private boolean newEmployee;

	    @Override
	    @Transient
	    @JsonIgnore
	    public boolean isNew() {
	        return this.newEmployee || id == null;
	    }

	    public Employee setAsNew() {
	        this.newEmployee = true;
	        return this;
	    }

		@Override
		public String toString() {
			return "Employee [id=" + id + ", name=" + name + "]";
		}
}
