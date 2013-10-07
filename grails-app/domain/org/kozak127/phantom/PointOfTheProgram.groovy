package org.kozak127.phantom

class PointOfTheProgram {

	boolean accepted
	Date creationDate
	Integer timeInHours

	static hasOne = [event: Event, creator: User]

    static constraints = {
    	creator(nullable: false)
    	event(nullable: false)
    }
}
