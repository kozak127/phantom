package org.kozak127.phantom.InEventObject

class InEventObjectService {

    def getWorkers(InEventObject object) {
        return InEventObjectWorker.findAllWhere(inEventObject: object)
    }

    def getStaff(InEventObject object) {
        return InEventObjectStaff.findAllWhere(inEventObject: object)
    }

    def getStaffUser(InEventObjectStaff staff) {
        return staff.reservation.user
    }

    def getWorkerUsers(InEventObject object) {
        return getWorkers(object).each { getStaffUser(it) }
    }

    def getReservations(InEventObject object) {
        return getStaff(object)*.reservation
    }

    def payStaffReservations(InEventObject object) {
        getReservations.each { it.paid = true }
    }

    void deleteWithDependencies(InEventObject object) {
        withTransaction {
            getStaff.each { it.delete() }
            InEventObject.delete()
        }
    }
}
