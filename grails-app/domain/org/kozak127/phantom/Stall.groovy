package org.kozak127.phantom

class Stall {

	boolean accepted
	Date creationDate

	static hasOne = [owner: User, event: Event]
	static hasMany = [workers: StallWorker]
    
    static constraints = {
    }
}
