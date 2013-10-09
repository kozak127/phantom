package org.kozak127.phantom.Staff
import org.kozak127.phantom.Reservation
import org.kozak127.phantom.Event

class StaffMember {

    static belongsTo = [reservation: Reservation]
    static constraints = {
    	reservation(nullable:false)
    }

    void payReservation() {
        reservation.paid = true
    }

    Event getEvent() {
    	return reservation.event
    }
}
