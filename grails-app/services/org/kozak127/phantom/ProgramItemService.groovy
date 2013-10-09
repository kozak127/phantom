package org.kozak127.phantom

import org.kozak127.phantom.Event

class ProgramItemService {

	def getList() {
		String setting = response.getCookie('phantomSystemMode')
        Event event = Event.findByName(setting)
        def result
        
        if (user.isAdmin()) {
            result = [programItemInstanceList: ProgramItem.list(params), programItemInstanceTotal: ProgramItem.count()]
        } else if(event) {
            result = [programItemInstanceList: event.getProgramItems(), programItemInstanceTotal: event.getProgramItems().count()]
        } else {
            result = [programItemInstanceList: user.getProgramItems(), programItemInstanceTotal: user.getProgramItems().count()]
        }
        return result
    }

    def getWorkers(ProgramItem programItem) {
        return ProgramItemWorker.findAllByProgramItem(programItem)
    }

    def payReservations(ProgramItem programItem) {
        programItem.creator.payReservation()
        programItem.getWorkers.each { it.payReservation() }
    }

    void deleteWithDependencies(ProgramItem programItem) {
        withTransaction {
            programItem.creator.delete()
            programItem.getWorkers().each { it.delete() }
            programItem.delete()
        }
    }
}
