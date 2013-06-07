package org.kozak127.phantom

import grails.util.Environment
import org.springframework.transaction.annotation.Transactional

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
				System.out.println("KURWA MAĆ!")
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
        def organizerRole = Role.findByAuthority('ROLE_ORGANIZER') ?: new Role(authority: 'ROLE_ORGANIZER').save(failOnError: true)
	}

	private void developmentData() {
		initialUsers()
		initialEvents()
		initialOrganizators()
		initialReservations()
		initialVolunteers()
	}

	private void productionData() {
	}

	private void initialUsers() {
		
		def user1 = User.findByUsername('user1') ?: new User(
					username: 'user1',
					password: 'user',
					email: 'user1@bla.com',
					firstName: 'Adam',
					lastName: 'Adamski',
					birthDate: Date.parse("d/M/yyyy", "1/1/1990"),
					enabled: true
					).save(failOnError: true)
		
		def user2 = User.findByUsername('user2') ?: new User(
					username: 'user2',
					password: 'user',
					email: 'user2@bla.com',
					firstName: 'Adam',
					lastName: 'Adamski',
					birthDate: Date.parse("d/M/yyyy", "1/1/1990"),
					enabled: true
					).save(failOnError: true)
		
		def user3 = User.findByUsername('user3') ?: new User(
					username: 'user3',
					password: 'user',
					email: 'user3@bla.com',
					firstName: 'Adam',
					lastName: 'Adamski',
					birthDate: Date.parse("d/M/yyyy", "1/1/1990"),
					enabled: true
					).save(failOnError: true)
		
		def user4 = User.findByUsername('user4') ?: new User(
					username: 'user4',
					password: 'user',
					email: 'user4@bla.com',
					firstName: 'Adam',
					lastName: 'Adamski',
					birthDate: Date.parse("d/M/yyyy", "1/1/1990"),
					enabled: true
					).save(failOnError: true)
		
		def user5 = User.findByUsername('user5') ?: new User(
					username: 'user5',
					password: 'user',
					email: 'user5@bla.com',
					firstName: 'Adam',
					lastName: 'Adamski',
					birthDate: Date.parse("d/M/yyyy", "1/1/1990"),
					enabled: true
					).save(failOnError: true)
	
	}

	private void initialEvents() {
		
		def event1 = Event.findByName('event1') ?: new Event(
					name: 'event1',
					dateStart: Date.parse("d/M/yyyy", "1/1/2013"),
					dateEnd: Date.parse("d/M/yyyy", "2/1/2013")
					).save(failOnError: true)

		def event2 = Event.findByName('event2') ?: new Event(
					name: 'event2',
					dateStart: Date.parse("d/M/yyyy", "1/2/2013"),
					dateEnd: Date.parse("d/M/yyyy", "3/2/2013")
					).save(failOnError: true)

	}

	// TODO fix findByUserAndEvent - it is not working properly
	private void initialOrganizators() {
		
		def organizer1 = Organizer.findByUserAndEvent(user1, event1) ?: new Organizer(
						user: User.findByUsername('user1'),
						event: Event.findByName('event1')
						).save(failOnError: true)
				
		def organizer2 = Organizer.findByUserAndEvent(user2, event1) ?: new Organizer(
						user: User.findByUsername('user2'),
						event: Event.findByName('event1')
						).save(failOnError: true)
				
		def organizer3 = Organizer.findByUserAndEvent(user2, event2) ?: new Organizer(
						user: User.findByUsername('user2'),
						event: Event.findByName('event2')
						).save(failOnError: true)	
	}

	private void initialReservations() {

		def reservation1 = Reservation.findByUserAndEvent(user3, event1) ?: new Reservation(
						user: User.findByUsername('user3'),
						event: Event.findByName('event1'),
						paid: true
						).save(failOnError: true)

		def reservation2 = Reservation.findByUserAndEvent(user3, event2) ?: new Reservation(
						user: User.findByUsername('user3'),
						event: Event.findByName('event2'),
						paid: false
						).save(failOnError: true)

		def reservation3 = Reservation.findByUserAndEvent(user4, event1) ?: new Reservation(
						user: User.findByUsername('user4'),
						event: Event.findByName('event1'),
						paid: true
						).save(failOnError: true)
	}

	private void initialVolunteers() {
		
		def volunteer1 = Volunteer.findByUserAndEvent(user5, event1) ?: new Volunteer(
						user: User.findByUsername('user5'),
						event: Event.findByName('event1')
						).save(failOnError: true)
	}
}
