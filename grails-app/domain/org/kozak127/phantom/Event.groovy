package org.kozak127.phantom

class Event {

    String name
    Date dateStart
    Date dateEnd

    static belongsTo = [creator: User]

    static constraints = {
        name(blank: false, unique: true)
        creator(nullable: false)
    }
}
