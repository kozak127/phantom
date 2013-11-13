package org.kozak127.phantom

class Volunteer {

	boolean accepted = false
	boolean organizer = false

	static belongsTo = [reservation: Reservation]

    static constraints = {
    	accepted(nullable: false)
    	organizer(nullable: false)
    	reservation(nullable:false)
    }
}
