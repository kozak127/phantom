package org.kozak127.phantom

import org.codehaus.groovy.grails.web.util.WebUtils
import grails.plugins.springsecurity.SpringSecurityService

class OptionsService {

    SpringSecurityService springSecurityService

    def setOrganizerPerspective() {
		def webUtils = WebUtils.retrieveGrailsWebRequest()
		def session = webUtils.getSession()
		def perspective = session["perspective"]
		session["perspective"] = "organizer"
    }

    def setNormalPerspective() {
    	def webUtils = WebUtils.retrieveGrailsWebRequest()
		def session = webUtils.getSession()
    	def perspective = session["perspective"]
		session["perspective"] = "normal"
    }

    def getPerspective(){
    	def webUtils = WebUtils.retrieveGrailsWebRequest()
		def session = webUtils.getSession()
    	def user = springSecurityService.currentUser
    	if (user.isAdmin()) {
    		return "admin"
    	}
    	else {
    	   	return session.perspective
    	}
    }
}
