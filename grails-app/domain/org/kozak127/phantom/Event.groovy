package org.kozak127.phantom

class Event {

    String name
    Date dateStart = new Date()
    Date dateEnd = new Date()
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

    static def create(Map properties) {
        withTransaction {
            Event object = new Event()
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
