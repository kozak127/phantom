package org.kozak127.phantom
import org.kozak127.phantom.Staff.*

class Reservation {
    
    boolean paid
    Date creationDate

    static belongsTo = [user: User, event: Event]

    static constraints = {
        paid(nullable: false)
        user(nullable: false)
        event(nullable: false)
    }

    void deleteWithDependencies() {
		withTransaction {
			StaffMember.findAllByReservation(this).each { it.delete() }
			this.delete()
		}
	}
}
