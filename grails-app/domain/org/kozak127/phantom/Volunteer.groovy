package org.kozak127.phantom

class Volunteer {

    boolean accepted = false
    boolean organizer = false
    Date creationDate = new Date()
	//String eventId
	
	//static transients = ['eventId']

    static belongsTo = [reservation: Reservation]

    static constraints = {
        accepted(nullable: false)
        organizer(nullable: false)
        reservation(nullable:false)
        creationDate(nullable: false)
    }
	
	def fillReservation(User user, Map eventParams) {
		String eventId = eventParams.id
		Event event = Event.findById(eventId)
		reservation = Reservation.findByUserAndEvent(user, event)
	}
	
	def checkIfDuplicate(){
		Volunteer duplicate = Volunteer.findByReservation(reservation)
	}
}
