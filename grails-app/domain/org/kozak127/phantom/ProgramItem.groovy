package org.kozak127.phantom
import org.kozak127.phantom.Staff.*

class ProgramItem {

    String name
    boolean accepted
    Date creationDate
    Integer timeInHours

    static belongsTo = [event: Event, creator: ProgramItemCreator]

    static constraints = {
        creator(nullable: false)
        event(nullable: false)
    }

    def payReservationsForStaff() {
        StaffMember.findAllByProgramItem(this).each {it.payReservation()}
    }

    void deleteWithDependencies() {
        withTransaction {
            creator.delete()
            ProgramItemWorker.findAllByProgramItem(this).each { it.delete }
            this.delete()
        }
    }
}
