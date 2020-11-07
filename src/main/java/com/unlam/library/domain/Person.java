package com.unlam.library.domain;

public class Person {

    protected Long id;
    protected String name;
    protected String lastName;
    protected Integer identification;

    public Person() { }

    public Person(Person person) {
        this.id = person.id;
        this.name = person.name;
        this.lastName = person.lastName;
        this.identification = person.identification;
    }

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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getIdentification() {
        return identification;
    }

    public void setIdentification(Integer i) {
        this.identification = i;
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((identification == null) ? 0 : identification.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (identification == null) {
			if (other.identification != null)
				return false;
		} else if (!identification.equals(other.identification))
			return false;
		return true;
	}
    
    
    
}
