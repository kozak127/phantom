package org.kozak127.phantom

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService

@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class ReservationController {

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
            return [reservationInstanceList: Reservation.list(params), reservationInstanceTotal: Reservation.count()]
        } else {
            def reservationList = user.getReservations(params)
            return [reservationInstanceList: reservationList, reservationInstanceTotal: reservationList.size()]
        }

    }

    def create() {
        [reservationInstance: new Reservation(params)]
    }

    def save() {
        User user = springSecurityService.currentUser
        if (!userIsAdmin()) params.user = user
        
        def reservationInstance = new Reservation(params)

        if (reservationInstance.checkIfDuplicate()) {
            flash.message = message(code: 'controller.reservation.duplicate')
            render(view: "create", model: [reservationInstance: reservationInstance])
            return
        }

        if (!reservationInstance.save(flush: true)) {
            render(view: "create", model: [reservationInstance: reservationInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservationInstance.id])
        redirect(action: "show", id: reservationInstance.id)
    }

    def show(Long id) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        [reservationInstance: reservationInstance]
    }

    def edit(Long id) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        [reservationInstance: reservationInstance]
    }

    def update(Long id, Long version) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (reservationInstance.version > version) {
                reservationInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'reservation.label', default: 'Reservation')] as Object[],
                          "Another user has updated this Reservation while you were editing")
                render(view: "edit", model: [reservationInstance: reservationInstance])
                return
            }
        }

        reservationInstance.properties = params

        if (!reservationInstance.save(flush: true)) {
            render(view: "edit", model: [reservationInstance: reservationInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'reservation.label', default: 'Reservation'), reservationInstance.id])
        redirect(action: "show", id: reservationInstance.id)
    }

    def delete(Long id) {
        def reservationInstance = Reservation.get(id)
        if (!reservationInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
            return
        }

        try {
            reservationInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'reservation.label', default: 'Reservation'), id])
            redirect(action: "show", id: id)
        }
    }
}
