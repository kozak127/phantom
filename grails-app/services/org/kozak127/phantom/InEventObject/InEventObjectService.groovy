package org.kozak127.phantom.InEventObject

class InEventObjectService {

    def getWorkers(InEventObject object) {
        return InEventObjectWorker.findAllWhere(inEventObject: object)
    }

    def getWorkerUsers() {
        // TODO
        //return this.getWorkers(object)*.reservation.user
    }

    void deleteWithDependencies(InEventObject object) {
        withTransaction {
        	this.getWorkers(object).each { it.delete() }
            InEventObject.creator.delete()
            InEventObject.delete()
        }
    }
}
