package org.kozak127.phantom

class Organizer {

	static hasOne = [user: User, event: Event]

    static constraints = {
    	user(nullable: false)
    	event(nullable: false)
    }
}
