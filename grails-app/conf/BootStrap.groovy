import grails.util.Environment
import org.kozak127.phantom.BootStrapService
import org.codehaus.groovy.grails.commons.GrailsApplication


class BootStrap {

	GrailsApplication grailsApplication
	BootStrapService bootStrapService

    def init = { servletContext ->
		String bootMessage = 'Bootstrap success'
		bootStrapService.boot(Environment.current)
	}

	def destroy = {
		
	}
}
