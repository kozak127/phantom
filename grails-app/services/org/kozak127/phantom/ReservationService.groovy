package org.kozak127.phantom
import org.kozak127.phantom.InEventObject.InEventObjectService
import org.kozak127.phantom.InEventObject.InEventObjectCreator
import org.kozak127.phantom.InEventObject.InEventObjectWorker

class ReservationService {

	InEventObjectService inEventObjectService

	def getInEventObjects(Reservation reservation) {
		return InEventObjectCreator.findAllByReservation(reservation)*.inEventObject
	}

	def getInEventObjectWorkers(Reservation reservation) {
		return InEventObjectWorker.findAllByReservation(reservation)
	}

	void deleteWithDependencies(Reservation reservation) {
        withTransaction {
            getInEventObjects(reservation).each { inEventObjectService.deleteWithDependencies(it) }
            InEventObjectWorker.findAllByReservation(reservation).each { it.delete() }
            //reservation.volunteer.delete()
            reservation.delete()
        }
    }
}