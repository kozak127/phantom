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

    def getStaffMembers() {
        return StaffMember.findAllByReservation(this)
    }

    void deleteWithDependencies() {
		withTransaction {
			getStaffMembers().each { it.delete() }
			this.delete()
		}
	}
}
