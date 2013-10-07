package org.kozak127.phantom

class User {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	String email
	Date birthDate
	String firstName
	String lastName

	static hasMany = [reservation:Reservation, organizer:Organizer, volunteer:Volunteer, pointOfTheProgram:PointOfTheProgram]
	static constraints = {
		username blank: false, unique: true
		password blank: false

		email(blank: false, email: true, unique: true)
    	firstName(blank: false)
    	lastName(blank: false)
    	
    	reservation(nullable: true)
    	organizer(nullable: true)
    	volunteer(nullable: true)
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}

	boolean isAdmin() {
		if (UserRole.findByUserAndRole(this, Role.findByAuthority('ROLE_ADMIN'))) {
			return true
		} else {
			return false
		}
	}

	void addRole(String role) {
		UserRole.create(this, Role.findByAuthority(role))
	}

	void remove() {
		removed = true
		save(flush: true)
	}
}
