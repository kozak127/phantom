package org.kozak127.phantom
import org.kozak127.phantom.InEventObject.InEventObjectService


class UserService {

    InEventObjectService inEventObjectService
    ReservationService reservationService
    EventService eventService

    def getReservations(User user) {
        return Reservation.findAllByUser(user)
    }

    def getInEventObjects(User user) {
        return getReservations(user).each { reservationService.getInEventObjects(it) }
    }

    def getEvents(User user) {
        return Event.findAllByCreator(user)
    }

    void deleteWithDependencies(User user) {
        withTransaction {
            getReservations.each { reservationService.deleteWithDependencies(it) }
            getEvents.each { eventService.deleteWithDependencies(it) }
            user.delete()
        }
    }
}
