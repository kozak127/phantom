package org.kozak127.phantom.Staff
import org.kozak127.phantom.Reservation

class StaffMember {

    static belongsTo = [reservation: Reservation]
    static constraints = {
    	reservation(nullable:false)
    }

    def payReservation() {
        reservation.paid = true
    }
}
