package org.kozak127.phantom

class Event {

    String name
	Date dateStart
	Date dateEnd

	static hasMany = [
        reservations:Reservation,
        organizators:Organizer,
        volunteers:Volunteer,
        programItems:ProgramItem,
        stalls:Stall
        ]

    static constraints = {
    	name(blank: false, unique: true)
    	reservations(nullable: true)
    	organizators(nullable: true)
    	volunteers(nullable: true)
        programItems(nullable: true)
        stalls(nullable: true)
    }
}
