package org.kozak127.phantom.Staff

import org.springframework.dao.DataIntegrityViolationException

class ProgramItemWorkerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [programItemWorkerInstanceList: ProgramItemWorker.list(params), programItemWorkerInstanceTotal: ProgramItemWorker.count()]
    }

    def create() {
        [programItemWorkerInstance: new ProgramItemWorker(params)]
    }

    def save() {
        def programItemWorkerInstance = new ProgramItemWorker(params)
        if (!programItemWorkerInstance.save(flush: true)) {
            render(view: "create", model: [programItemWorkerInstance: programItemWorkerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'programItemWorker.label', default: 'ProgramItemWorker'), programItemWorkerInstance.id])
        redirect(action: "show", id: programItemWorkerInstance.id)
    }

    def show(Long id) {
        def programItemWorkerInstance = ProgramItemWorker.get(id)
        if (!programItemWorkerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'programItemWorker.label', default: 'ProgramItemWorker'), id])
            redirect(action: "list")
            return
        }

        [programItemWorkerInstance: programItemWorkerInstance]
    }

    def edit(Long id) {
        def programItemWorkerInstance = ProgramItemWorker.get(id)
        if (!programItemWorkerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'programItemWorker.label', default: 'ProgramItemWorker'), id])
            redirect(action: "list")
            return
        }

        [programItemWorkerInstance: programItemWorkerInstance]
    }

    def update(Long id, Long version) {
        def programItemWorkerInstance = ProgramItemWorker.get(id)
        if (!programItemWorkerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'programItemWorker.label', default: 'ProgramItemWorker'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (programItemWorkerInstance.version > version) {
                programItemWorkerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'programItemWorker.label', default: 'ProgramItemWorker')] as Object[],
                          "Another user has updated this ProgramItemWorker while you were editing")
                render(view: "edit", model: [programItemWorkerInstance: programItemWorkerInstance])
                return
            }
        }

        programItemWorkerInstance.properties = params

        if (!programItemWorkerInstance.save(flush: true)) {
            render(view: "edit", model: [programItemWorkerInstance: programItemWorkerInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'programItemWorker.label', default: 'ProgramItemWorker'), programItemWorkerInstance.id])
        redirect(action: "show", id: programItemWorkerInstance.id)
    }

    def delete(Long id) {
        def programItemWorkerInstance = ProgramItemWorker.get(id)
        if (!programItemWorkerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'programItemWorker.label', default: 'ProgramItemWorker'), id])
            redirect(action: "list")
            return
        }

        try {
            programItemWorkerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'programItemWorker.label', default: 'ProgramItemWorker'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'programItemWorker.label', default: 'ProgramItemWorker'), id])
            redirect(action: "show", id: id)
        }
    }
}
