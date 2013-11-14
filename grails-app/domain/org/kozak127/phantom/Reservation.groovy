package org.kozak127.phantom

class Reservation {
    
    boolean paid = false
    Date creationDate

    static belongsTo = [user: User, event: Event]

    static constraints = {
        paid(nullable: false)
        user(nullable: false)
        event(nullable: false)
    }
}
