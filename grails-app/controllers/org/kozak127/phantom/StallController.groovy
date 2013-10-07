package org.kozak127.phantom

import org.springframework.dao.DataIntegrityViolationException

class StallController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [stallInstanceList: Stall.list(params), stallInstanceTotal: Stall.count()]
    }

    def create() {
        [stallInstance: new Stall(params)]
    }

    def save() {
        def stallInstance = new Stall(params)
        if (!stallInstance.save(flush: true)) {
            render(view: "create", model: [stallInstance: stallInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'stall.label', default: 'Stall'), stallInstance.id])
        redirect(action: "show", id: stallInstance.id)
    }

    def show(Long id) {
        def stallInstance = Stall.get(id)
        if (!stallInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stall.label', default: 'Stall'), id])
            redirect(action: "list")
            return
        }

        [stallInstance: stallInstance]
    }

    def edit(Long id) {
        def stallInstance = Stall.get(id)
        if (!stallInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stall.label', default: 'Stall'), id])
            redirect(action: "list")
            return
        }

        [stallInstance: stallInstance]
    }

    def update(Long id, Long version) {
        def stallInstance = Stall.get(id)
        if (!stallInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stall.label', default: 'Stall'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (stallInstance.version > version) {
                stallInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'stall.label', default: 'Stall')] as Object[],
                          "Another user has updated this Stall while you were editing")
                render(view: "edit", model: [stallInstance: stallInstance])
                return
            }
        }

        stallInstance.properties = params

        if (!stallInstance.save(flush: true)) {
            render(view: "edit", model: [stallInstance: stallInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'stall.label', default: 'Stall'), stallInstance.id])
        redirect(action: "show", id: stallInstance.id)
    }

    def delete(Long id) {
        def stallInstance = Stall.get(id)
        if (!stallInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'stall.label', default: 'Stall'), id])
            redirect(action: "list")
            return
        }

        try {
            stallInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'stall.label', default: 'Stall'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'stall.label', default: 'Stall'), id])
            redirect(action: "show", id: id)
        }
    }
}
