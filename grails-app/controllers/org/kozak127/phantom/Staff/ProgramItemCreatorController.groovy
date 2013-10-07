package org.kozak127.phantom.Staff

import org.springframework.dao.DataIntegrityViolationException

class ProgramItemCreatorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [programItemCreatorInstanceList: ProgramItemCreator.list(params), programItemCreatorInstanceTotal: ProgramItemCreator.count()]
    }

    def create() {
        [programItemCreatorInstance: new ProgramItemCreator(params)]
    }

    def save() {
        def programItemCreatorInstance = new ProgramItemCreator(params)
        if (!programItemCreatorInstance.save(flush: true)) {
            render(view: "create", model: [programItemCreatorInstance: programItemCreatorInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'programItemCreator.label', default: 'ProgramItemCreator'), programItemCreatorInstance.id])
        redirect(action: "show", id: programItemCreatorInstance.id)
    }

    def show(Long id) {
        def programItemCreatorInstance = ProgramItemCreator.get(id)
        if (!programItemCreatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'programItemCreator.label', default: 'ProgramItemCreator'), id])
            redirect(action: "list")
            return
        }

        [programItemCreatorInstance: programItemCreatorInstance]
    }

    def edit(Long id) {
        def programItemCreatorInstance = ProgramItemCreator.get(id)
        if (!programItemCreatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'programItemCreator.label', default: 'ProgramItemCreator'), id])
            redirect(action: "list")
            return
        }

        [programItemCreatorInstance: programItemCreatorInstance]
    }

    def update(Long id, Long version) {
        def programItemCreatorInstance = ProgramItemCreator.get(id)
        if (!programItemCreatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'programItemCreator.label', default: 'ProgramItemCreator'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (programItemCreatorInstance.version > version) {
                programItemCreatorInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'programItemCreator.label', default: 'ProgramItemCreator')] as Object[],
                          "Another user has updated this ProgramItemCreator while you were editing")
                render(view: "edit", model: [programItemCreatorInstance: programItemCreatorInstance])
                return
            }
        }

        programItemCreatorInstance.properties = params

        if (!programItemCreatorInstance.save(flush: true)) {
            render(view: "edit", model: [programItemCreatorInstance: programItemCreatorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'programItemCreator.label', default: 'ProgramItemCreator'), programItemCreatorInstance.id])
        redirect(action: "show", id: programItemCreatorInstance.id)
    }

    def delete(Long id) {
        def programItemCreatorInstance = ProgramItemCreator.get(id)
        if (!programItemCreatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'programItemCreator.label', default: 'ProgramItemCreator'), id])
            redirect(action: "list")
            return
        }

        try {
            programItemCreatorInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'programItemCreator.label', default: 'ProgramItemCreator'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'programItemCreator.label', default: 'ProgramItemCreator'), id])
            redirect(action: "show", id: id)
        }
    }
}
