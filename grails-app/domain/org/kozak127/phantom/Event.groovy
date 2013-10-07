package org.kozak127.phantom
import org.kozak127.phantom.Staff.*

class Event {

    String name
    Date dateStart
    Date dateEnd

    static belongsTo = [creator: User]

    static constraints = {
        name(blank: false, unique: true)
        creator(nullable: false)
    }

    void deleteWithDependencies() {
        withTransaction {
            Stall.findAllByEvent(this).each { it.deleteWithDependencies() }
            ProgramItem.findAllByEvent(this).each { it.deleteWithDependencies() }
            Volunteer.findAllByEvent(this).each { it.delete }
            EventOrganizer.findAllByEvent(this).each { it.delete }
            Reservation.findAllByEvent(this).each { it.deleteWithDependencies() }
            this.delete()
        }
    }
}
