package org.kozak127.phantom

class Volunteer {

    boolean accepted = false
    boolean organizer = false
    Date creationDate = new Date()
	String eventName
	
	static transients = ['eventName']

    static belongsTo = [reservation: Reservation]

    static constraints = {
        accepted(nullable: false)
        organizer(nullable: false)
        reservation(nullable:false)
        creationDate(nullable: false)
    }
	
	def fillReservation(User user) {
		Event event = Event.findByName(eventName)
		reservation = Reservation.findByUserAndEvent(user, event)
	}
	
	def checkIfDuplicate(){
		Volunteer duplicate = Volunteer.findByReservation(reservation)
	}
}
