package org.kozak127.phantom

class User {

    transient springSecurityService

    String username
    String password
    boolean enabled
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    String email
    Date birthDate
    String firstName
    String lastName
        
    static constraints = {
        username blank: false, unique: true
        password blank: false

        email(blank: false, email: true, unique: true)
        //email(blank: false, email: true)
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
        String tmpString = username + ' ' + password + ' ' + email + ' ' + firstName + ' ' + lastName
        return tmpString
    }

    void deleteWithDependencies() {
        withTransaction {
            Reservation.findAllByUser(this).each { it.deleteWithDependencies() }
            Event.findAllByCreator(this).each { it.deleteWithDependencies() }
            this.delete()
        }
    }
}
