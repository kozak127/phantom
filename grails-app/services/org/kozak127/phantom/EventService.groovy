package org.kozak127.phantom
import org.kozak127.phantom.InEventObject.InEventObject


class EventService {

    ReservationService reservationService

    def getInEventObjects(Event event) {
        return InEventObject.findAllByEvent(event)
    }

    def getVolunteers(Event event) {
        return Volunteer.findAllByEvent(event)
    }

    def getEventOrganizers(Event event) {
        return Volunteer.findAllByEventAndOrganizer(event, true)
    }

    def getReservations(Event event) {
        return Reservation.findAllByEvent(event)
    }

    void deleteWithDependencies(Event event) {
        withTransaction {
            getReservations(event).each { reservationService.deleteWithDependencies(it) }
            event.delete()
        }
    }
}
