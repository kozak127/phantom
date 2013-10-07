package org.kozak127.phantom

import grails.util.Environment
import org.springframework.transaction.annotation.Transactional
import org.kozak127.phantom.Staff.EventOrganizer
import org.kozak127.phantom.Staff.Volunteer

class BootStrapService {

    static transactional = false

    @Transactional
    void boot(Environment environment) {
        switch (environment) {
            case Environment.PRODUCTION:
                bootProduction()
                break
            case Environment.DEVELOPMENT:
                bootDevelopment()
                break
            case Environment.TEST:
                bootTest()
                break
            default:
                System.out.println("ERROR: NO ENVIRONMENT CASE")
                break
        }
    }

    void bootProduction() {
        defaultSecurity()
        productionData()
    }

    void bootDevelopment() {
        defaultSecurity()
        developmentData()
    }

    void bootTest() {
        defaultSecurity()
    }

    private void defaultSecurity() {
        defaultRoles()
        def adminUser = User.findByUsername('admin') ?: new User(
                username: 'admin',
                password: 'admin',
                email: 'admin@admin.com',
                firstName: 'admin',
                lastName: 'admin',
                birthDate: Date.parse("d/M/yyyy", "1/1/1990"),
                enabled: true
                ).save(failOnError: true)

        if (!adminUser.isAdmin()) {
            UserRole.create adminUser, Role.findByAuthority('ROLE_ADMIN')
        }
    }

    private void defaultRoles() {
        def userRole = Role.findByAuthority('ROLE_USER') ?: new Role(authority: 'ROLE_USER').save(failOnError: true)
        def adminRole = Role.findByAuthority('ROLE_ADMIN') ?: new Role(authority: 'ROLE_ADMIN').save(failOnError: true)
        def volunteerRole = Role.findByAuthority('ROLE_VOLUNTEER') ?: new Role(authority: 'ROLE_VOLUNTEER').save(failOnError: true)
        def eventOrganizerRole = Role.findByAuthority('ROLE_ORGANIZER') ?: new Role(authority: 'ROLE_ORGANIZER').save(failOnError: true)
    }

    private void developmentData() {
        initialUsers()
        initialEvents()
        initialReservations()
        initialOrganizators()
        initialVolunteers()
    }

    private void productionData() {
    }

    private void createTestUser(String name) {
        User tmp = new User(
            username: name,
            password: name,
            email: name + '@bla.com',
            firstName: 'Adam',
            lastName: 'Adamski',
            birthDate: Date.parse("d/M/yyyy", "1/1/1990"),
            enabled: true
            ).save(failOnError: true)
        System.out.println('Created user:' + name)
    }

    private void createTestReservation(User user, Event event) {
        Reservation tmp = new Reservation(
            user: user,
            event: event,
            creationDate: new Date(System.currentTimeMillis()),
            paid: true
            ).save(failOnError: true)
        System.out.println('Created reservation for ' + user.username + '/' + event.name)
    }

    private void createTestEvent(String name, User creator) {
        Event tmp = new Event(
            name: name,
            creator: User.findByUsername('user1'),
            dateStart: Date.parse("d/M/yyyy", "1/1/2013"),
            dateEnd: Date.parse("d/M/yyyy", "2/1/2013")
            ).save(failOnError: true)
        System.out.println('Created event ' + name + ' with creator ' + creator.username)
    }

    private void createTestVolunteer(Reservation res) {
        Volunteer tmp = new Volunteer(
            reservation: res
            ).save(failOnError: true)
        System.out.println('Created volunteer')
    }
            
    private void createTestOrganizer(Reservation res) {
        EventOrganizer tmp = new EventOrganizer(
            reservation: res
            ).save(failOnError: true)
        System.out.println('Created organizer')
    }        

    private void initialUsers() {
        User.findByUsername('user1') ?: createTestUser('user1')
        User.findByUsername('user2') ?: createTestUser('user2')
        User.findByUsername('user3') ?: createTestUser('user3')
        User.findByUsername('user4') ?: createTestUser('user4')
        User.findByUsername('user5') ?: createTestUser('user5')
        User.findByUsername('user6') ?: createTestUser('user6')
        User.findByUsername('user7') ?: createTestUser('user7')
        User.findByUsername('user8') ?: createTestUser('user8')
        User.findByUsername('user9') ?: createTestUser('user9')
    }

    private void initialEvents() {
        User user1 = User.findByUsername('user1')
        User user2 = User.findByUsername('user2')

        Event event1 = Event.findByName('event1') ?: createTestEvent('event1', user1)
        Event event2 = Event.findByName('event2') ?: createTestEvent('event2', user2)
    }

    private void initialReservations() {

        def user2 = User.findByUsername('user2')
        def user3 = User.findByUsername('user3')
        def user4 = User.findByUsername('user4')
        def user5 = User.findByUsername('user5')
        def user6 = User.findByUsername('user6')
        def user7 = User.findByUsername('user7')
        def user8 = User.findByUsername('user8')
        def user9 = User.findByUsername('user9')
        
        def event1 = Event.findByName('event1')

        Reservation.findByUserAndEvent(user2, event1) ?: createTestReservation(user2, event1)
        Reservation.findByUserAndEvent(user3, event1) ?: createTestReservation(user3, event1)
        Reservation.findByUserAndEvent(user4, event1) ?: createTestReservation(user4, event1)
        Reservation.findByUserAndEvent(user5, event1) ?: createTestReservation(user5, event1)
        Reservation.findByUserAndEvent(user6, event1) ?: createTestReservation(user6, event1)
        Reservation.findByUserAndEvent(user7, event1) ?: createTestReservation(user7, event1)
        Reservation.findByUserAndEvent(user8, event1) ?: createTestReservation(user8, event1)
        Reservation.findByUserAndEvent(user9, event1) ?: createTestReservation(user9, event1)
    }

    private void initialOrganizators() {
        
        def event1 = Event.findByName('event1')
        
        def user2 = User.findByUsername('user2')
        def user3 = User.findByUsername('user3')

        Reservation res2 = Reservation.findByUserAndEvent(user2, event1)
        Reservation res3 = Reservation.findByUserAndEvent(user3, event1)

        EventOrganizer.findByReservation(res2) ?: createTestOrganizer(res2) 
        EventOrganizer.findByReservation(res3) ?: createTestOrganizer(res3)
    }

    private void initialVolunteers() {
        
        def user4 = User.findByUsername('user4')
        def event1 = Event.findByName('event1')
        def res4 = Reservation.findByUserAndEvent(user4, event1)

        Volunteer.findByReservation(res4) ?: createTestVolunteer(res4)
    }

    private void initialAttractions() {
    }
}
