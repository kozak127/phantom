package org.kozak127.phantom.InEventObject

import org.springframework.dao.DataIntegrityViolationException

class InEventObjectWorkerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [inEventObjectWorkerInstanceList: InEventObjectWorker.list(params), inEventObjectWorkerInstanceTotal: InEventObjectWorker.count()]
    }

    def create() {
        [inEventObjectWorkerInstance: new InEventObjectWorker(params)]
    }

    def save() {
        def inEventObjectWorkerInstance = new InEventObjectWorker(params)
        if (!inEventObjectWorkerInstance.save(flush: true)) {
            render(view: "create", model: [inEventObjectWorkerInstance: inEventObjectWorkerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'inEventObjectWorker.label', default: 'InEventObjectWorker'), inEventObjectWorkerInstance.id])
        redirect(action: "show", id: inEventObjectWorkerInstance.id)
    }

    def show(Long id) {
        def inEventObjectWorkerInstance = InEventObjectWorker.get(id)
        if (!inEventObjectWorkerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inEventObjectWorker.label', default: 'InEventObjectWorker'), id])
            redirect(action: "list")
            return
        }

        [inEventObjectWorkerInstance: inEventObjectWorkerInstance]
    }

    def edit(Long id) {
        def inEventObjectWorkerInstance = InEventObjectWorker.get(id)
        if (!inEventObjectWorkerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inEventObjectWorker.label', default: 'InEventObjectWorker'), id])
            redirect(action: "list")
            return
        }

        [inEventObjectWorkerInstance: inEventObjectWorkerInstance]
    }

    def update(Long id, Long version) {
        def inEventObjectWorkerInstance = InEventObjectWorker.get(id)
        if (!inEventObjectWorkerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inEventObjectWorker.label', default: 'InEventObjectWorker'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (inEventObjectWorkerInstance.version > version) {
                inEventObjectWorkerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'inEventObjectWorker.label', default: 'InEventObjectWorker')] as Object[],
                          "Another user has updated this InEventObjectWorker while you were editing")
                render(view: "edit", model: [inEventObjectWorkerInstance: inEventObjectWorkerInstance])
                return
            }
        }

        inEventObjectWorkerInstance.properties = params

        if (!inEventObjectWorkerInstance.save(flush: true)) {
            render(view: "edit", model: [inEventObjectWorkerInstance: inEventObjectWorkerInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'inEventObjectWorker.label', default: 'InEventObjectWorker'), inEventObjectWorkerInstance.id])
        redirect(action: "show", id: inEventObjectWorkerInstance.id)
    }

    def delete(Long id) {
        def inEventObjectWorkerInstance = InEventObjectWorker.get(id)
        if (!inEventObjectWorkerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inEventObjectWorker.label', default: 'InEventObjectWorker'), id])
            redirect(action: "list")
            return
        }

        try {
            inEventObjectWorkerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'inEventObjectWorker.label', default: 'InEventObjectWorker'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'inEventObjectWorker.label', default: 'InEventObjectWorker'), id])
            redirect(action: "show", id: id)
        }
    }
}
