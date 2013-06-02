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

	//TODO change that miserable names into something more sensible
	static hasMany = [reservation:Reservation, organizer:Organizer, volunteer:Volunteer]
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
}
