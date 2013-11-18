package org.kozak127.phantom

import grails.plugins.springsecurity.SpringSecurityService
import grails.plugins.springsecurity.Secured
import org.springframework.dao.DataIntegrityViolationException

@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class VolunteerController {

	SpringSecurityService springSecurityService
	
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def userIsAdmin() {
		User user = springSecurityService.currentUser
		return user.isAdmin()
	}
	
    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		User user = springSecurityService.currentUser
		if(user.isAdmin()){
			return [volunteerInstanceList: Volunteer.list(params), volunteerInstanceTotal: Volunteer.count()]
		} else {
			def volunteerList = user.getVolunteers(params)
			return [volunteerInstanceList: volunteerList, volunteerInstanceTotal: volunteerList.size()]
		}
        
    }

    def create() {
        [volunteerInstance: new Volunteer(params)]
    }

    def save() {
        def volunteerInstance = new Volunteer(params)
		User user = springSecurityService.currentUser
		print params.eventName
		volunteerInstance.eventName = params.eventName
		if(!volunteerInstance.fillReservation(user)) {
			flash.message = message(code: 'controller.volunteer.reservation.not.found')
			redirect(controller: "reservation", action: "list")
		}
		
        if (!volunteerInstance.save(flush: true)) {
            render(view: "create", model: [volunteerInstance: volunteerInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'volunteer.label', default: 'Volunteer'), volunteerInstance.id])
        redirect(action: "show", id: volunteerInstance.id)
    }

    def show(Long id) {
        def volunteerInstance = Volunteer.get(id)
        if (!volunteerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'volunteer.label', default: 'Volunteer'), id])
            redirect(action: "list")
            return
        }

        [volunteerInstance: volunteerInstance]
    }

    def edit(Long id) {
        def volunteerInstance = Volunteer.get(id)
        if (!volunteerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'volunteer.label', default: 'Volunteer'), id])
            redirect(action: "list")
            return
        }

        [volunteerInstance: volunteerInstance]
    }

    def update(Long id, Long version) {
        def volunteerInstance = Volunteer.get(id)
        if (!volunteerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'volunteer.label', default: 'Volunteer'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (volunteerInstance.version > version) {
                volunteerInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'volunteer.label', default: 'Volunteer')] as Object[],
                          "Another user has updated this Volunteer while you were editing")
                render(view: "edit", model: [volunteerInstance: volunteerInstance])
                return
            }
        }

        volunteerInstance.properties = params

        if (!volunteerInstance.save(flush: true)) {
            render(view: "edit", model: [volunteerInstance: volunteerInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'volunteer.label', default: 'Volunteer'), volunteerInstance.id])
        redirect(action: "show", id: volunteerInstance.id)
    }

    def delete(Long id) {
        def volunteerInstance = Volunteer.get(id)
        if (!volunteerInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'volunteer.label', default: 'Volunteer'), id])
            redirect(action: "list")
            return
        }

        try {
            volunteerInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'volunteer.label', default: 'Volunteer'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'volunteer.label', default: 'Volunteer'), id])
            redirect(action: "show", id: id)
        }
    }
}
