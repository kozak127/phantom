package org.kozak127.phantom

class ProgramItem {

	boolean accepted
	Date creationDate
	Integer timeInHours

	static hasOne = [event: Event, creator: User]
	static hasMany = [worker: ProgramItemWorker]

    static constraints = {
    	creator(nullable: false)
    	event(nullable: false)
    }
}
