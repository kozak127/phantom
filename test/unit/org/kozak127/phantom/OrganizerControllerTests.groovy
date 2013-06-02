package org.kozak127.phantom



import org.junit.*
import grails.test.mixin.*

@TestFor(OrganizerController)
@Mock(Organizer)
class OrganizerControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/organizer/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.organizerInstanceList.size() == 0
        assert model.organizerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.organizerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.organizerInstance != null
        assert view == '/organizer/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/organizer/show/1'
        assert controller.flash.message != null
        assert Organizer.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/organizer/list'

        populateValidParams(params)
        def organizer = new Organizer(params)

        assert organizer.save() != null

        params.id = organizer.id

        def model = controller.show()

        assert model.organizerInstance == organizer
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/organizer/list'

        populateValidParams(params)
        def organizer = new Organizer(params)

        assert organizer.save() != null

        params.id = organizer.id

        def model = controller.edit()

        assert model.organizerInstance == organizer
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/organizer/list'

        response.reset()

        populateValidParams(params)
        def organizer = new Organizer(params)

        assert organizer.save() != null

        // test invalid parameters in update
        params.id = organizer.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/organizer/edit"
        assert model.organizerInstance != null

        organizer.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/organizer/show/$organizer.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        organizer.clearErrors()

        populateValidParams(params)
        params.id = organizer.id
        params.version = -1
        controller.update()

        assert view == "/organizer/edit"
        assert model.organizerInstance != null
        assert model.organizerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/organizer/list'

        response.reset()

        populateValidParams(params)
        def organizer = new Organizer(params)

        assert organizer.save() != null
        assert Organizer.count() == 1

        params.id = organizer.id

        controller.delete()

        assert Organizer.count() == 0
        assert Organizer.get(organizer.id) == null
        assert response.redirectedUrl == '/organizer/list'
    }
}
