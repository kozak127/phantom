package org.kozak127.phantom

import grails.plugins.springsecurity.SpringSecurityService

class OptionsService {

    SpringSecurityService springSecurityService

    def setOrganizerPerspective() {
		//optionsService.setPerspective(params)
		def perspective = session["perspective"]
		session["perspective"] = "organizer"
		redirect(controller: "reservation", action: "list")
    }

    def setNormalPerspective() {
    	def perspective = session["perspective"]
		session["perspective"] = "normal"
		redirect(controller: "reservation", action: "list")
    }

    def getPerspective(){
    	def user = springSecurityService.currentUser
    	if (user.isAdmin()) {
    		return "admin"
    	}
    	else {
    	   	return session.perspective
    	}
    }
}
