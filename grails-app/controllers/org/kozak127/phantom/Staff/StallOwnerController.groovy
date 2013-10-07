package org.kozak127.phantom.Staff

import org.springframework.dao.DataIntegrityViolationException

class StallOwnerController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [stallOwnerInstanceList: StallOwner.list(params), stallOwnerInstanceTotal: StallOwner.count()]
    }

    def create() {
        [stallOwnerInstance: new StallOwner(params)]
    }

    def save() {
        def stallOwnerInstance = new StallOwner(params)
        if (!stallOwnerInstance.save(flush: true)) {
            render(view: "create", model: [stallOwnerInstance: stallOwnerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'stallOwner.label', default: 'StallOwner'), stallOwnerInstance.id])
        redirect(action: "show", id: stallOwnerInstance.id)
    }

    def show(Long id) {
        def stallOwnerInstance = StallOwner.get(id)
        if (!stallOwnerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stallOwner.label', default: 'StallOwner'), id])
            redirect(action: "list")
            return
        }

        [stallOwnerInstance: stallOwnerInstance]
    }

    def edit(Long id) {
        def stallOwnerInstance = StallOwner.get(id)
        if (!stallOwnerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stallOwner.label', default: 'StallOwner'), id])
            redirect(action: "list")
            return
        }

        [stallOwnerInstance: stallOwnerInstance]
    }

    def update(Long id, Long version) {
        def stallOwnerInstance = StallOwner.get(id)
        if (!stallOwnerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stallOwner.label', default: 'StallOwner'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (stallOwnerInstance.version > version) {
                stallOwnerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'stallOwner.label', default: 'StallOwner')] as Object[],
                          "Another user has updated this StallOwner while you were editing")
                render(view: "edit", model: [stallOwnerInstance: stallOwnerInstance])
                return
            }
        }

        stallOwnerInstance.properties = params

        if (!stallOwnerInstance.save(flush: true)) {
            render(view: "edit", model: [stallOwnerInstance: stallOwnerInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'stallOwner.label', default: 'StallOwner'), stallOwnerInstance.id])
        redirect(action: "show", id: stallOwnerInstance.id)
    }

    def delete(Long id) {
        def stallOwnerInstance = StallOwner.get(id)
        if (!stallOwnerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stallOwner.label', default: 'StallOwner'), id])
            redirect(action: "list")
            return
        }

        try {
            stallOwnerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'stallOwner.label', default: 'StallOwner'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'stallOwner.label', default: 'StallOwner'), id])
            redirect(action: "show", id: id)
        }
    }
}
