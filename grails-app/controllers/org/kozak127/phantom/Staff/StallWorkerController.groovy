package org.kozak127.phantom.Staff

import org.springframework.dao.DataIntegrityViolationException

class StallWorkerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [stallWorkerInstanceList: StallWorker.list(params), stallWorkerInstanceTotal: StallWorker.count()]
    }

    def create() {
        [stallWorkerInstance: new StallWorker(params)]
    }

    def save() {
        def stallWorkerInstance = new StallWorker(params)
        if (!stallWorkerInstance.save(flush: true)) {
            render(view: "create", model: [stallWorkerInstance: stallWorkerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'stallWorker.label', default: 'StallWorker'), stallWorkerInstance.id])
        redirect(action: "show", id: stallWorkerInstance.id)
    }

    def show(Long id) {
        def stallWorkerInstance = StallWorker.get(id)
        if (!stallWorkerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stallWorker.label', default: 'StallWorker'), id])
            redirect(action: "list")
            return
        }

        [stallWorkerInstance: stallWorkerInstance]
    }

    def edit(Long id) {
        def stallWorkerInstance = StallWorker.get(id)
        if (!stallWorkerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stallWorker.label', default: 'StallWorker'), id])
            redirect(action: "list")
            return
        }

        [stallWorkerInstance: stallWorkerInstance]
    }

    def update(Long id, Long version) {
        def stallWorkerInstance = StallWorker.get(id)
        if (!stallWorkerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stallWorker.label', default: 'StallWorker'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (stallWorkerInstance.version > version) {
                stallWorkerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'stallWorker.label', default: 'StallWorker')] as Object[],
                          "Another user has updated this StallWorker while you were editing")
                render(view: "edit", model: [stallWorkerInstance: stallWorkerInstance])
                return
            }
        }

        stallWorkerInstance.properties = params

        if (!stallWorkerInstance.save(flush: true)) {
            render(view: "edit", model: [stallWorkerInstance: stallWorkerInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'stallWorker.label', default: 'StallWorker'), stallWorkerInstance.id])
        redirect(action: "show", id: stallWorkerInstance.id)
    }

    def delete(Long id) {
        def stallWorkerInstance = StallWorker.get(id)
        if (!stallWorkerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stallWorker.label', default: 'StallWorker'), id])
            redirect(action: "list")
            return
        }

        try {
            stallWorkerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'stallWorker.label', default: 'StallWorker'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'stallWorker.label', default: 'StallWorker'), id])
            redirect(action: "show", id: id)
        }
    }
}
