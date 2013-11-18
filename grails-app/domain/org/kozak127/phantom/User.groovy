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

    def getReservations(Map params) {
        return Reservation.findAllByUser(this, params)
    }

    def getInEventObjects() {
        return getReservations().each { it.getInEventObjects() }
    }

    def getEvents() {
        return Event.findAllByCreator(this)
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
