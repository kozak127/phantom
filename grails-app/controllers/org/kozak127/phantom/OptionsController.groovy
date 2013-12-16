package org.kozak127.phantom
import grails.plugins.springsecurity.Secured
import grails.plugins.springsecurity.SpringSecurityService

@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class OptionsController {

	OptionsService optionsService

    def index() {
    	
    }

    def setOrganizerPerspective() {
		optionsService.setOrganizerPerspective()
		redirect(controller: "reservation", action: "list")
    }

    def setNormalPerspective() {
    	optionsService.setNormalPerspective()
		redirect(controller: "reservation", action: "list")
    }
}
