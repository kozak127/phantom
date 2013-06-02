package org.kozak127.phantom

import org.springframework.dao.DataIntegrityViolationException

class OrganizerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [organizerInstanceList: Organizer.list(params), organizerInstanceTotal: Organizer.count()]
    }

    def create() {
        [organizerInstance: new Organizer(params)]
    }

    def save() {
        def organizerInstance = new Organizer(params)
        if (!organizerInstance.save(flush: true)) {
            render(view: "create", model: [organizerInstance: organizerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'organizer.label', default: 'Organizer'), organizerInstance.id])
        redirect(action: "show", id: organizerInstance.id)
    }

    def show(Long id) {
        def organizerInstance = Organizer.get(id)
        if (!organizerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'organizer.label', default: 'Organizer'), id])
            redirect(action: "list")
            return
        }

        [organizerInstance: organizerInstance]
    }

    def edit(Long id) {
        def organizerInstance = Organizer.get(id)
        if (!organizerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'organizer.label', default: 'Organizer'), id])
            redirect(action: "list")
            return
        }

        [organizerInstance: organizerInstance]
    }

    def update(Long id, Long version) {
        def organizerInstance = Organizer.get(id)
        if (!organizerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'organizer.label', default: 'Organizer'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (organizerInstance.version > version) {
                organizerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'organizer.label', default: 'Organizer')] as Object[],
                          "Another user has updated this Organizer while you were editing")
                render(view: "edit", model: [organizerInstance: organizerInstance])
                return
            }
        }

        organizerInstance.properties = params

        if (!organizerInstance.save(flush: true)) {
            render(view: "edit", model: [organizerInstance: organizerInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'organizer.label', default: 'Organizer'), organizerInstance.id])
        redirect(action: "show", id: organizerInstance.id)
    }

    def delete(Long id) {
        def organizerInstance = Organizer.get(id)
        if (!organizerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'organizer.label', default: 'Organizer'), id])
            redirect(action: "list")
            return
        }

        try {
            organizerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'organizer.label', default: 'Organizer'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'organizer.label', default: 'Organizer'), id])
            redirect(action: "show", id: id)
        }
    }
}
