package org.kozak127.phantom
import org.kozak127.phantom.InEventObject.InEventObject


class EventService {

    def getInEventObjects(Event object) {
        return InEventObject.findAllByEvent(object)
    }

    def getVolunteers(Event object) {
        return Volunteer.findAllByEvent(object)
    }

    def getEventOrganizers(Event object) {
        return Volunteer.findAllByEventAndOrganizer(object, true)
    }

    void deleteWithDependencies(Event object) {
        withTransaction {
            InEventObject.findAllByEvent(object).each { inEventObjectService.deleteWithDependencies(it) }
            Reservation.findAllByEvent(object).each { reservationService.deleteWithDependencies(it) }
            object.delete()
        }
    }
}
