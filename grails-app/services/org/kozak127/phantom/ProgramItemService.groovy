package org.kozak127.phantom

import org.kozak127.phantom.Event

class ProgramItemService {

	def getList(User user, String viewEvent, def params) {		
        Event event = Event.findByName(viewEvent)
        def result

        if (user.isAdmin()) {
            System.out.println('listing all program items')
            result = [programItemInstanceList: ProgramItem.list(params), programItemInstanceTotal: ProgramItem.count()]
        } else if(event) {
            System.out.println('listing event program items')
            result = [programItemInstanceList: event.getProgramItems(), programItemInstanceTotal: event.getProgramItems().count()]
        } else {
            System.out.println('listing user program items')
            result = [programItemInstanceList: user.getProgramItems(), programItemInstanceTotal: user.getProgramItems().count()]
        }

        System.out.println(result)
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
