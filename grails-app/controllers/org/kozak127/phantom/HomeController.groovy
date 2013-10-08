package org.kozak127.phantom
import grails.plugins.springsecurity.SpringSecurityService

class HomeController {

    SpringSecurityService springSecurityService

	def index() {
		if (springSecurityService.isLoggedIn()) {
			redirect(controller: UserRole.findByUser(springSecurityService.getCurrentUser()).role.defaultController)
		} else {
			redirect(controller: 'login', action: 'auth')
		}
	}
}
