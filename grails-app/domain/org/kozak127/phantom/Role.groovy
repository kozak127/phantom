package org.kozak127.phantom

class Role {

    String authority

    static mapping = {
        cache true
    }

    static constraints = {
        authority blank: false, unique: true
    }

    static void createDefaults() {
        [
            ["ADMIN", 'user'],
            ["USER", 'reservation'],
        ].each {
            new Role(authority:it[0], defaultController:it[1]).save()
        }
    }

    @Override
    public String toString() {
        "Role: $authority"
    }
}
