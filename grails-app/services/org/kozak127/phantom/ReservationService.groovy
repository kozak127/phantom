package org.kozak127.phantom
import org.kozak127.phantom.InEventObject.InEventObjectService
import org.kozak127.phantom.InEventObject.InEventObjectCreator
import org.kozak127.phantom.InEventObject.InEventObjectWorker

class ReservationService {

    InEventObjectService inEventObjectService

    def getCreatedInEventObjects(Reservation reservation) {
        return InEventObjectCreator.findAllByReservation(reservation)*.inEventObject
    }

    def getInEventObjectWorkers(Reservation reservation) {
        return InEventObjectWorker.findAllByReservation(reservation)
    }

    def getVolunteer(Reservation reservation) {
        return Volunteer.findByReservation(reservation)
    }

    void deleteWithDependencies(Reservation reservation) {
        withTransaction {
            getCreatedInEventObjects(reservation).each { inEventObjectService.deleteWithDependencies(it) }
            getInEventObjectWorkers(reservation).each { it.delete() }
            getVolunteer(reservation).each { it.delete() }
            reservation.delete()
        }
    }
}