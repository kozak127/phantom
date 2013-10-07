package org.kozak127.phantom.Staff

import org.springframework.dao.DataIntegrityViolationException

class EventOrganizerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [eventOrganizerInstanceList: EventOrganizer.list(params), eventOrganizerInstanceTotal: EventOrganizer.count()]
    }

    def create() {
        [eventOrganizerInstance: new EventOrganizer(params)]
    }

    def save() {
        def eventOrganizerInstance = new EventOrganizer(params)
        if (!eventOrganizerInstance.save(flush: true)) {
            render(view: "create", model: [eventOrganizerInstance: eventOrganizerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'eventOrganizer.label', default: 'EventOrganizer'), eventOrganizerInstance.id])
        redirect(action: "show", id: eventOrganizerInstance.id)
    }

    def show(Long id) {
        def eventOrganizerInstance = EventOrganizer.get(id)
        if (!eventOrganizerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'eventOrganizer.label', default: 'EventOrganizer'), id])
            redirect(action: "list")
            return
        }

        [eventOrganizerInstance: eventOrganizerInstance]
    }

    def edit(Long id) {
        def eventOrganizerInstance = EventOrganizer.get(id)
        if (!eventOrganizerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'eventOrganizer.label', default: 'EventOrganizer'), id])
            redirect(action: "list")
            return
        }

        [eventOrganizerInstance: eventOrganizerInstance]
    }

    def update(Long id, Long version) {
        def eventOrganizerInstance = EventOrganizer.get(id)
        if (!eventOrganizerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'eventOrganizer.label', default: 'EventOrganizer'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (eventOrganizerInstance.version > version) {
                eventOrganizerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'eventOrganizer.label', default: 'EventOrganizer')] as Object[],
                          "Another user has updated this EventOrganizer while you were editing")
                render(view: "edit", model: [eventOrganizerInstance: eventOrganizerInstance])
                return
            }
        }

        eventOrganizerInstance.properties = params

        if (!eventOrganizerInstance.save(flush: true)) {
            render(view: "edit", model: [eventOrganizerInstance: eventOrganizerInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'eventOrganizer.label', default: 'EventOrganizer'), eventOrganizerInstance.id])
        redirect(action: "show", id: eventOrganizerInstance.id)
    }

    def delete(Long id) {
        def eventOrganizerInstance = EventOrganizer.get(id)
        if (!eventOrganizerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'eventOrganizer.label', default: 'EventOrganizer'), id])
            redirect(action: "list")
            return
        }

        try {
            eventOrganizerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'eventOrganizer.label', default: 'EventOrganizer'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'eventOrganizer.label', default: 'EventOrganizer'), id])
            redirect(action: "show", id: id)
        }
    }
}
