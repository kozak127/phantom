package org.kozak127.phantom.InEventObject

import org.springframework.dao.DataIntegrityViolationException

class InEventObjectCreatorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [inEventObjectCreatorInstanceList: InEventObjectCreator.list(params), inEventObjectCreatorInstanceTotal: InEventObjectCreator.count()]
    }

    def create() {
        [inEventObjectCreatorInstance: new InEventObjectCreator(params)]
    }

    def save() {
        def inEventObjectCreatorInstance = new InEventObjectCreator(params)
        if (!inEventObjectCreatorInstance.save(flush: true)) {
            render(view: "create", model: [inEventObjectCreatorInstance: inEventObjectCreatorInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'inEventObjectCreator.label', default: 'InEventObjectCreator'), inEventObjectCreatorInstance.id])
        redirect(action: "show", id: inEventObjectCreatorInstance.id)
    }

    def show(Long id) {
        def inEventObjectCreatorInstance = InEventObjectCreator.get(id)
        if (!inEventObjectCreatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inEventObjectCreator.label', default: 'InEventObjectCreator'), id])
            redirect(action: "list")
            return
        }

        [inEventObjectCreatorInstance: inEventObjectCreatorInstance]
    }

    def edit(Long id) {
        def inEventObjectCreatorInstance = InEventObjectCreator.get(id)
        if (!inEventObjectCreatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inEventObjectCreator.label', default: 'InEventObjectCreator'), id])
            redirect(action: "list")
            return
        }

        [inEventObjectCreatorInstance: inEventObjectCreatorInstance]
    }

    def update(Long id, Long version) {
        def inEventObjectCreatorInstance = InEventObjectCreator.get(id)
        if (!inEventObjectCreatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inEventObjectCreator.label', default: 'InEventObjectCreator'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (inEventObjectCreatorInstance.version > version) {
                inEventObjectCreatorInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'inEventObjectCreator.label', default: 'InEventObjectCreator')] as Object[],
                          "Another user has updated this InEventObjectCreator while you were editing")
                render(view: "edit", model: [inEventObjectCreatorInstance: inEventObjectCreatorInstance])
                return
            }
        }

        inEventObjectCreatorInstance.properties = params

        if (!inEventObjectCreatorInstance.save(flush: true)) {
            render(view: "edit", model: [inEventObjectCreatorInstance: inEventObjectCreatorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'inEventObjectCreator.label', default: 'InEventObjectCreator'), inEventObjectCreatorInstance.id])
        redirect(action: "show", id: inEventObjectCreatorInstance.id)
    }

    def delete(Long id) {
        def inEventObjectCreatorInstance = InEventObjectCreator.get(id)
        if (!inEventObjectCreatorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inEventObjectCreator.label', default: 'InEventObjectCreator'), id])
            redirect(action: "list")
            return
        }

        try {
            inEventObjectCreatorInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'inEventObjectCreator.label', default: 'InEventObjectCreator'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'inEventObjectCreator.label', default: 'InEventObjectCreator'), id])
            redirect(action: "show", id: id)
        }
    }
}
