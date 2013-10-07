package org.kozak127.phantom

class Stall {

    String name
    Integer size
    boolean paid
    boolean accepted
    Date creationDate

    static belongstTo = [owner: StallOwner, event: Event]
    
    static constraints = {
    }

    def payReservationsForStaff() {
        StaffMember.findAllByProgramItem(this).each {it.payReservation()}
    }

    void deleteWithDependencies() {
		withTransaction {
			owner.delete()
			StallWorker.findAllByStall(this).each { it.delete }
			this.delete()
		}
	}
}
