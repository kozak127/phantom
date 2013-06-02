package org.kozak127.phantom

import org.springframework.dao.DataIntegrityViolationException

class VolunteerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [volunteerInstanceList: Volunteer.list(params), volunteerInstanceTotal: Volunteer.count()]
    }

    def create() {
        [volunteerInstance: new Volunteer(params)]
    }

    def save() {
        def volunteerInstance = new Volunteer(params)
        if (!volunteerInstance.save(flush: true)) {
            render(view: "create", model: [volunteerInstance: volunteerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'volunteer.label', default: 'Volunteer'), volunteerInstance.id])
        redirect(action: "show", id: volunteerInstance.id)
    }

    def show(Long id) {
        def volunteerInstance = Volunteer.get(id)
        if (!volunteerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'volunteer.label', default: 'Volunteer'), id])
            redirect(action: "list")
            return
        }

        [volunteerInstance: volunteerInstance]
    }

    def edit(Long id) {
        def volunteerInstance = Volunteer.get(id)
        if (!volunteerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'volunteer.label', default: 'Volunteer'), id])
            redirect(action: "list")
            return
        }

        [volunteerInstance: volunteerInstance]
    }

    def update(Long id, Long version) {
        def volunteerInstance = Volunteer.get(id)
        if (!volunteerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'volunteer.label', default: 'Volunteer'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (volunteerInstance.version > version) {
                volunteerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'volunteer.label', default: 'Volunteer')] as Object[],
                          "Another user has updated this Volunteer while you were editing")
                render(view: "edit", model: [volunteerInstance: volunteerInstance])
                return
            }
        }

        volunteerInstance.properties = params

        if (!volunteerInstance.save(flush: true)) {
            render(view: "edit", model: [volunteerInstance: volunteerInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'volunteer.label', default: 'Volunteer'), volunteerInstance.id])
        redirect(action: "show", id: volunteerInstance.id)
    }

    def delete(Long id) {
        def volunteerInstance = Volunteer.get(id)
        if (!volunteerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'volunteer.label', default: 'Volunteer'), id])
            redirect(action: "list")
            return
        }

        try {
            volunteerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'volunteer.label', default: 'Volunteer'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'volunteer.label', default: 'Volunteer'), id])
            redirect(action: "show", id: id)
        }
    }
}
