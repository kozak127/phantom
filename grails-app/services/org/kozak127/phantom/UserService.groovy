package org.kozak127.phantom
import org.kozak127.phantom.InEventObject.InEventObjectService


class UserService {

	InEventObjectService inEventObjectService
	//ReservationService reservationService
	EventService eventService

    def getReservations(User user) {
        return Reservation.findAllByUser(user)
    }

    def getEvents(User user) {
        return Event.findAllByCreator(user)
    }

    /*def getInEventObjects(User user) {
    	return InEventObject.findAllWhere('creator.reservation.user': user)
    }*/

    void deleteWithDependencies(User user) {
        withTransaction {
            //this.getInEventObjects(user).each { inEventObjectService.deleteWithDependencies(it) }
            //Reservation.findAllByUser(user).each { reservationService.deleteWithDependencies(it) }
            Event.findAllByCreator(user).each { eventService.deleteWithDependencies(it) }
            user.delete()
        }
    }
}
