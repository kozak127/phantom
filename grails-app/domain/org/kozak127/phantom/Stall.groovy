package org.kozak127.phantom
import org.kozak127.phantom.Staff.StallOwner
import org.kozak127.phantom.Staff.StallWorker

class Stall {

    String name
    Integer size
    boolean paid
    boolean accepted
    Date creationDate

    static belongsTo = [event: Event, owner: StallOwner]

    static constraints = {
        owner(nullable: false)
        event(nullable: false)
    }

    def payReservationsForStaff() {
        StaffMember.findAllByStall(this).each { it.payReservation() }
    }

    void deleteWithDependencies() {
		withTransaction {
			owner.delete()
			StallWorker.findAllByStall(this).each { it.delete() }
			this.delete()
		}
	}
}
