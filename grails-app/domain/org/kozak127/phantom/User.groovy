package org.kozak127.phantom

class User {

    transient springSecurityService

    String username
    String password
    String confirmPassword
    boolean enabled = true
    boolean accountExpired = false
    boolean accountLocked = false
    boolean passwordExpired = false

    String email
    Date birthDate
    String firstName
    String lastName
    
    static transients = ['confirmPassword']

    static constraints = {
        username blank: false, unique: true
        password blank: false

        email(blank: false, email: true, unique: true)
        firstName(blank: false)
        lastName(blank: false)
    }

    static mapping = {
        password column: '`password`'
    }

    Set<Role> getAuthorities() {
        UserRole.findAllByUser(this).collect { it.role } as Set
    }

    def beforeInsert() {
        encodePassword()
    }

    def beforeUpdate() {
        if (isDirty('password')) {
            encodePassword()
        }
    }

    protected void encodePassword() {
        password = springSecurityService.encodePassword(password)
    }

    boolean isAdmin() {
        if (UserRole.findByUserAndRole(this, Role.findByAuthority('ROLE_ADMIN'))) {
            return true
        } else {
            return false
        }
    }

    void addRole(String role) {
        UserRole.create(this, Role.findByAuthority(role))
    }

    void remove() {
        removed = true
        save(flush: true)
    }

    String toString() {
        return username
    }

    def getReservation(Event event) {
        return Reservation.findByUserAndEvent(this, event)
    }

    def getReservation(Map params) {
        String eventId = params.event.id
        Event event = Event.findById(eventId)
        return getReservation(event)
    }

    def getReservations(Map params) {
        return Reservation.findAllByUser(this, params)
    }
	
	def getVolunteers(Map params) {
		def listOfVolunteers = []
		getReservations().each { listOfVolunteers += it.getVolunteer() }
		listOfVolunteers.flatten()
		return listOfVolunteers
	}

    def getOrganizedEvents() {
        def listOfEvents = []
        getReservations().each {
            def volunteer = it.getVolunteer()
            if ((volunteer.organizer == true) && (volunteer.accepted == true))  {
                listOfEvents += volunteer.reservation.event
            }
        }
        listOfEvents += getCreatedEvents()
        listOfEvents.flatten()
        return listOfEvents
    }

    def getOrganizedEventsReservations() {
        def listOfReservations = []
        getOrganizedEvents().each { listOfReservations += it.getReservations() }
        listOfReservations.flatten()
        return listOfReservations
    }

    def getOrganizedEventsVolunteers() {
        def listOfVolunteers = []
        getOrganizedEvents().each { listOfVolunteers += it.getVolunteers() }
        listOfVolunteers.flatten()
        return listOfVolunteers
    }

    def getOrganizedEventsInEventObjects() {
        def listOfObjects = []
        getOrganizedEvents().each { listOfObjects += it.getInEventObjects() }
        listOfObjects.flatten()
        return listOfObjects
    }

    def getInEventObjects() {
		def listOfObjects = []
        getReservations().each { listOfObjects += it.getCreatedInEventObjects() }
		listOfObjects.flatten()
    	return listOfObjects
	}

    def getCreatedEvents() {
        return Event.findAllByCreator(this)
    }
	
	boolean isCreator(Event event) {
		if(event.creator == this) return true
		return false
	}

    void deleteWithDependencies() {
        withTransaction {
            getReservations().each { it.deleteWithDependencies() }
            getEvents().each { it.deleteWithDependencies() }
            this.delete()
        }
    }

    boolean passwordMatch() {
        if(password.equals(confirmPassword)) return true
        return false
    }
}
