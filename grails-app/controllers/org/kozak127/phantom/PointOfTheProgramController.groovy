package org.kozak127.phantom

import org.springframework.dao.DataIntegrityViolationException

class PointOfTheProgramController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [pointOfTheProgramInstanceList: PointOfTheProgram.list(params), pointOfTheProgramInstanceTotal: PointOfTheProgram.count()]
    }

    def create() {
        [pointOfTheProgramInstance: new PointOfTheProgram(params)]
    }

    def save() {
        def pointOfTheProgramInstance = new PointOfTheProgram(params)
        if (!pointOfTheProgramInstance.save(flush: true)) {
            render(view: "create", model: [pointOfTheProgramInstance: pointOfTheProgramInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'pointOfTheProgram.label', default: 'PointOfTheProgram'), pointOfTheProgramInstance.id])
        redirect(action: "show", id: pointOfTheProgramInstance.id)
    }

    def show(Long id) {
        def pointOfTheProgramInstance = PointOfTheProgram.get(id)
        if (!pointOfTheProgramInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pointOfTheProgram.label', default: 'PointOfTheProgram'), id])
            redirect(action: "list")
            return
        }

        [pointOfTheProgramInstance: pointOfTheProgramInstance]
    }

    def edit(Long id) {
        def pointOfTheProgramInstance = PointOfTheProgram.get(id)
        if (!pointOfTheProgramInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pointOfTheProgram.label', default: 'PointOfTheProgram'), id])
            redirect(action: "list")
            return
        }

        [pointOfTheProgramInstance: pointOfTheProgramInstance]
    }

    def update(Long id, Long version) {
        def pointOfTheProgramInstance = PointOfTheProgram.get(id)
        if (!pointOfTheProgramInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pointOfTheProgram.label', default: 'PointOfTheProgram'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (pointOfTheProgramInstance.version > version) {
                pointOfTheProgramInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'pointOfTheProgram.label', default: 'PointOfTheProgram')] as Object[],
                          "Another user has updated this PointOfTheProgram while you were editing")
                render(view: "edit", model: [pointOfTheProgramInstance: pointOfTheProgramInstance])
                return
            }
        }

        pointOfTheProgramInstance.properties = params

        if (!pointOfTheProgramInstance.save(flush: true)) {
            render(view: "edit", model: [pointOfTheProgramInstance: pointOfTheProgramInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'pointOfTheProgram.label', default: 'PointOfTheProgram'), pointOfTheProgramInstance.id])
        redirect(action: "show", id: pointOfTheProgramInstance.id)
    }

    def delete(Long id) {
        def pointOfTheProgramInstance = PointOfTheProgram.get(id)
        if (!pointOfTheProgramInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'pointOfTheProgram.label', default: 'PointOfTheProgram'), id])
            redirect(action: "list")
            return
        }

        try {
            pointOfTheProgramInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'pointOfTheProgram.label', default: 'PointOfTheProgram'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'pointOfTheProgram.label', default: 'PointOfTheProgram'), id])
            redirect(action: "show", id: id)
        }
    }
}
