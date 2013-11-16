package org.kozak127.phantom

class Event {

    String name
    Date dateStart
    Date creationDate = new Date()

    static belongsTo = [creator: User]

    static constraints = {
        name(blank: false, unique: true)
        creator(nullable: false)
        creationDate(nullable: false)
    }

    def getInEventObjects() {
        return InEventObject.findAllByEvent(this)
    }

    def getVolunteers() {
        return Volunteer.findAllByEvent(this)
    }

    def getOrganizers() {
        return Volunteer.findAllByEventAndOrganizer(this, true)
    }

    def getReservations() {
        return Reservation.findAllByEvent(this)
    }

    void deleteWithDependencies() {
        withTransaction {
            getReservations().each { it.deleteWithDependencies() }
            this.delete()
        }
    }
}
