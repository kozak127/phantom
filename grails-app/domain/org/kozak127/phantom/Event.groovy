package org.kozak127.phantom

class Event {

    String name
    Date dateStart
    Date dateEnd

    static belongsTo = [creator: User]

    static constraints = {
        name(blank: false, unique: true)
        creator(nullable: false)
    }

    def getStalls() {
        return Stall.findAllByEvent(this)
    }

    def getProgramItems() {
        return ProgramItem.findAllByEvent(this)
    }

    def getVolunteers() {
        return Volunteer.findAllByEvent(this)
    }

    def getEventOrganizers() {
        return EventOrganizer.findAllByEvent(this)
    }

    void deleteWithDependencies() {
        withTransaction {
            Stall.findAllByEvent(this).each { it.deleteWithDependencies() }
            ProgramItem.findAllByEvent(this).each { it.deleteWithDependencies() }
            Reservation.findAllByEvent(this).each { it.deleteWithDependencies() }
            this.delete()
        }
    }
}
