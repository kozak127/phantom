package org.kozak127.phantom.InEventObject

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.SpringSecurityService
import grails.plugins.springsecurity.Secured
import org.kozak127.phantom.User
import org.kozak127.phantom.Event
import org.kozak127.phantom.OptionsService

@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class InEventObjectController {

    SpringSecurityService springSecurityService
    OptionsService optionsService


    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
            params.max = Math.min(max ?: 10, 100)
            User user = springSecurityService.currentUser
            switch (optionsService.getPerspective()) {
                case 'admin' :
                    return [inEventObjectInstanceList: InEventObject.list(params), inEventObjectInstanceTotal: InEventObject.count()]
                    break
                case 'organizer' :
                    def inEventObjectList = user.getOrganizedEventsInEventObjects()
                    return [inEventObjectInstanceList: inEventObjectList, inEventObjectInstanceTotal: inEventObjectList.size()]
                    break
                case 'normal' :
                    def inEventObjectList = user.getInEventObjects()
                    return [inEventObjectInstanceList: inEventObjectList, inEventObjectInstanceTotal: inEventObjectList.size()]
                    break
                default :
                    def inEventObjectList = user.getInEventObjects()
                    return [inEventObjectInstanceList: inEventObjectList, inEventObjectInstanceTotal: inEventObjectList.size()]
                    break
            }        
        }
	
    def create() {
        [inEventObjectInstance: new InEventObject(params)]
    }

    def save() {
        User currentUser = springSecurityService.currentUser

        if (!currentUser.getReservation(params)) {
            flash.message = message(code: 'controller.InEventObject.reservation.not.found')
            redirect(controller: "reservation", action: "list")
            return
        }
        
        def inEventObjectInstance = new InEventObject(params)
        if (!inEventObjectInstance.save(flush: true)) {
            render(view: "create", model: [inEventObjectInstance: inEventObjectInstance])
            return
        }

        def inEventObjectCreatorInstance = inEventObjectInstance.createDependencies(currentUser)
        inEventObjectCreatorInstance.save(flush: true)

        flash.message = message(code: 'default.created.message', args: [message(code: 'inEventObject.label', default: 'InEventObject'), inEventObjectInstance.id])
        redirect(action: "show", id: inEventObjectInstance.id)
    }

    def show(Long id) {
        def inEventObjectInstance = InEventObject.get(id)
        if (!inEventObjectInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inEventObject.label', default: 'InEventObject'), id])
            redirect(action: "list")
            return
        }

        [inEventObjectInstance: inEventObjectInstance]
    }

    def edit(Long id) {
        def inEventObjectInstance = InEventObject.get(id)
        if (!inEventObjectInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inEventObject.label', default: 'InEventObject'), id])
            redirect(action: "list")
            return
        }

        [inEventObjectInstance: inEventObjectInstance]
    }

    def update(Long id, Long version) {
        def inEventObjectInstance = InEventObject.get(id)
        if (!inEventObjectInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inEventObject.label', default: 'InEventObject'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (inEventObjectInstance.version > version) {
                inEventObjectInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'inEventObject.label', default: 'InEventObject')] as Object[],
                          "Another user has updated this InEventObject while you were editing")
                render(view: "edit", model: [inEventObjectInstance: inEventObjectInstance])
                return
            }
        }

        inEventObjectInstance.properties = params

        if (!inEventObjectInstance.save(flush: true)) {
            render(view: "edit", model: [inEventObjectInstance: inEventObjectInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'inEventObject.label', default: 'InEventObject'), inEventObjectInstance.id])
        redirect(action: "show", id: inEventObjectInstance.id)
    }

    def delete(Long id) {
        def inEventObjectInstance = InEventObject.get(id)
        if (!inEventObjectInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'inEventObject.label', default: 'InEventObject'), id])
            redirect(action: "list")
            return
        }

        try {
            inEventObjectInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'inEventObject.label', default: 'InEventObject'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'inEventObject.label', default: 'InEventObject'), id])
            redirect(action: "show", id: id)
        }
    }
}
