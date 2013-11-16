package org.kozak127.phantom.InEventObject

import org.springframework.dao.DataIntegrityViolationException

class InEventObjectStaffController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [inEventObjectStaffInstanceList: InEventObjectStaff.list(params), inEventObjectStaffInstanceTotal: InEventObjectStaff.count()]
    }

    def create() {
        [inEventObjectStaffInstance: new InEventObjectStaff(params)]
    }

    def save() {
        def inEventObjectStaffInstance = new InEventObjectStaff(params)
        if (!inEventObjectStaffInstance.save(flush: true)) {
            render(view: "create", model: [inEventObjectStaffInstance: inEventObjectStaffInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'inEventObjectStaff.label', default: 'InEventObjectStaff'), inEventObjectStaffInstance.id])
        redirect(action: "show", id: inEventObjectStaffInstance.id)
    }

    def show(Long id) {
        def inEventObjectStaffInstance = InEventObjectStaff.get(id)
        if (!inEventObjectStaffInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inEventObjectStaff.label', default: 'InEventObjectStaff'), id])
            redirect(action: "list")
            return
        }

        [inEventObjectStaffInstance: inEventObjectStaffInstance]
    }

    def edit(Long id) {
        def inEventObjectStaffInstance = InEventObjectStaff.get(id)
        if (!inEventObjectStaffInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inEventObjectStaff.label', default: 'InEventObjectStaff'), id])
            redirect(action: "list")
            return
        }

        [inEventObjectStaffInstance: inEventObjectStaffInstance]
    }

    def update(Long id, Long version) {
        def inEventObjectStaffInstance = InEventObjectStaff.get(id)
        if (!inEventObjectStaffInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inEventObjectStaff.label', default: 'InEventObjectStaff'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (inEventObjectStaffInstance.version > version) {
                inEventObjectStaffInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'inEventObjectStaff.label', default: 'InEventObjectStaff')] as Object[],
                          "Another user has updated this InEventObjectStaff while you were editing")
                render(view: "edit", model: [inEventObjectStaffInstance: inEventObjectStaffInstance])
                return
            }
        }

        inEventObjectStaffInstance.properties = params

        if (!inEventObjectStaffInstance.save(flush: true)) {
            render(view: "edit", model: [inEventObjectStaffInstance: inEventObjectStaffInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'inEventObjectStaff.label', default: 'InEventObjectStaff'), inEventObjectStaffInstance.id])
        redirect(action: "show", id: inEventObjectStaffInstance.id)
    }

    def delete(Long id) {
        def inEventObjectStaffInstance = InEventObjectStaff.get(id)
        if (!inEventObjectStaffInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inEventObjectStaff.label', default: 'InEventObjectStaff'), id])
            redirect(action: "list")
            return
        }

        try {
            inEventObjectStaffInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'inEventObjectStaff.label', default: 'InEventObjectStaff'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'inEventObjectStaff.label', default: 'InEventObjectStaff'), id])
            redirect(action: "show", id: id)
        }
    }
}
