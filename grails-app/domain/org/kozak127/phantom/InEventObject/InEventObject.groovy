package org.kozak127.phantom.InEventObject
import org.kozak127.phantom.Event
import org.kozak127.phantom.User
import org.kozak127.phantom.Reservation

class InEventObject {

    boolean accepted = false
    Date creationDate = new Date()

    static belongsTo = [event: Event]

    static constraints = {
        event(nullable: false)
        creationDate(nullable: false)
    }

    def gerCreator() {
    	return InEventObjectCreator.findWhere(inEventObject: this)
    }

    def getCreatorUser() {
    	return getCreator()*.reservation.user
    }

    def getWorkers() {
        return InEventObjectWorker.findAllWhere(inEventObject: this)
    }

    def getStaff() {
        return InEventObjectStaff.findAllWhere(inEventObject: this)
    }

    def getWorkerUsers() {
        //return getWorkers().each { it.getUser() }
        return getWorkers()*.reservation.user
    }

    def getStaffReservations() {
        return getStaff()*.reservation
    }

    void payStaffReservations() {
        getStaffReservations().each { it.paid = true }
    }

    void deleteWithDependencies() {
        withTransaction {
            getStaff().each { it.delete() }
            this.delete()
        }
    }

    def createDependencies(User user) {

        Map properties = [
	        reservation: Reservation.findByUserAndEvent(user, event),
	        inEventObject: this
	    ]
        InEventObjectCreator creator = InEventObjectCreator.create(properties)

        return creator        
    }

    static def create(Map properties) {
    	withTransaction {
			InEventObject object = new InEventObject()
			object.update(properties)

			return object
		}
    }

    def update(Map properties) {
    	withTransaction {
			this.properties = properties
			save()

			return this
		}
    }
}
