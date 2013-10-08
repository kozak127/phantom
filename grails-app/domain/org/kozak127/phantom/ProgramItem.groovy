package org.kozak127.phantom
import org.kozak127.phantom.Staff.ProgramItemCreator
import org.kozak127.phantom.Staff.ProgramItemWorker

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

    def getWorkers() {
        return ProgramItemWorker.findAllByProgramItem(this)
    }

    def payReservations() {
        creator.payReservation()
        getWorkers.each { it.payReservation() }
    }

    void deleteWithDependencies() {
        withTransaction {
            creator.delete()
            getWorkers().each { it.delete() }
            this.delete()
        }
    }
}
