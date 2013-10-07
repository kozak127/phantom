package org.kozak127.phantom

import org.springframework.dao.DataIntegrityViolationException

class ProgramItemController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [programItemInstanceList: ProgramItem.list(params), programItemInstanceTotal: ProgramItem.count()]
    }

    def create() {
        [programItemInstance: new ProgramItem(params)]
    }

    def save() {
        def programItemInstance = new ProgramItem(params)
        if (!programItemInstance.save(flush: true)) {
            render(view: "create", model: [programItemInstance: programItemInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'programItem.label', default: 'ProgramItem'), programItemInstance.id])
        redirect(action: "show", id: programItemInstance.id)
    }

    def show(Long id) {
        def programItemInstance = ProgramItem.get(id)
        if (!programItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'programItem.label', default: 'ProgramItem'), id])
            redirect(action: "list")
            return
        }

        [programItemInstance: programItemInstance]
    }

    def edit(Long id) {
        def programItemInstance = ProgramItem.get(id)
        if (!programItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'programItem.label', default: 'ProgramItem'), id])
            redirect(action: "list")
            return
        }

        [programItemInstance: programItemInstance]
    }

    def update(Long id, Long version) {
        def programItemInstance = ProgramItem.get(id)
        if (!programItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'programItem.label', default: 'ProgramItem'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (programItemInstance.version > version) {
                programItemInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'programItem.label', default: 'ProgramItem')] as Object[],
                          "Another user has updated this ProgramItem while you were editing")
                render(view: "edit", model: [programItemInstance: programItemInstance])
                return
            }
        }

        programItemInstance.properties = params

        if (!programItemInstance.save(flush: true)) {
            render(view: "edit", model: [programItemInstance: programItemInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'programItem.label', default: 'ProgramItem'), programItemInstance.id])
        redirect(action: "show", id: programItemInstance.id)
    }

    def delete(Long id) {
        def programItemInstance = ProgramItem.get(id)
        if (!programItemInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'programItem.label', default: 'ProgramItem'), id])
            redirect(action: "list")
            return
        }

        try {
            programItemInstance.deleteWithDependencies()
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'programItem.label', default: 'ProgramItem'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'programItem.label', default: 'ProgramItem'), id])
            redirect(action: "show", id: id)
        }
    }
}
