package org.kozak127.phantom
import org.kozak127.phantom.InEventObject.InEventObjectCreator
import org.kozak127.phantom.InEventObject.InEventObjectWorker

class Reservation {
    
    boolean paid = false
    Date creationDate = new Date()

    static belongsTo = [user: User, event: Event]

    static constraints = {
        paid(nullable: false)
        user(nullable: false)
        event(nullable: false)
        creationDate(nullable: false)
    }

    def getCreatedInEventObjects() {
        return InEventObjectCreator.findAllByReservation(this)*.inEventObject
    }

    def getInEventObjectWorkers() {
        return InEventObjectWorker.findAllByReservation(this)
    }

    def getVolunteer() {
        return Volunteer.findAllByReservation(this)
    }

    void deleteWithDependencies() {
        withTransaction {
            getCreatedInEventObjects().each { it.deleteWithDependencies() }
            getInEventObjectWorkers().each { it.delete() }
            getVolunteer().each { it.delete() }
            this.delete()
        }
    }
}
