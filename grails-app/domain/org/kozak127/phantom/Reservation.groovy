package org.kozak127.phantom

class Reservation {
	
	boolean paid
	Date dateOfExpirationIfNotPaid

	static hasOne = [user: User, event: Event]

    static constraints = {
    	paid(nullable: false)
    	user(nullable: false)
    	event(nullable: false)
    }
}
