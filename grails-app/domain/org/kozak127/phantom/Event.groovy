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

    void deleteWithDependencies() {
        withTransaction {
            Stall.findAllByEvent(this).each { it.deleteWithDependencies() }
            ProgramItem.findAllByEvent(this).each { it.deleteWithDependencies() }
            Reservation.findAllByEvent(this).each { it.deleteWithDependencies() }
            this.delete()
        }
    }
}
