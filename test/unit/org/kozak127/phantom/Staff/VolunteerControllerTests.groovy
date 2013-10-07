package org.kozak127.phantom.Staff



import org.junit.*
import grails.test.mixin.*

@TestFor(VolunteerController)
@Mock(Volunteer)
class VolunteerControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/volunteer/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.volunteerInstanceList.size() == 0
        assert model.volunteerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.volunteerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.volunteerInstance != null
        assert view == '/volunteer/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/volunteer/show/1'
        assert controller.flash.message != null
        assert Volunteer.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/volunteer/list'

        populateValidParams(params)
        def volunteer = new Volunteer(params)

        assert volunteer.save() != null

        params.id = volunteer.id

        def model = controller.show()

        assert model.volunteerInstance == volunteer
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/volunteer/list'

        populateValidParams(params)
        def volunteer = new Volunteer(params)

        assert volunteer.save() != null

        params.id = volunteer.id

        def model = controller.edit()

        assert model.volunteerInstance == volunteer
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/volunteer/list'

        response.reset()

        populateValidParams(params)
        def volunteer = new Volunteer(params)

        assert volunteer.save() != null

        // test invalid parameters in update
        params.id = volunteer.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/volunteer/edit"
        assert model.volunteerInstance != null

        volunteer.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/volunteer/show/$volunteer.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        volunteer.clearErrors()

        populateValidParams(params)
        params.id = volunteer.id
        params.version = -1
        controller.update()

        assert view == "/volunteer/edit"
        assert model.volunteerInstance != null
        assert model.volunteerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/volunteer/list'

        response.reset()

        populateValidParams(params)
        def volunteer = new Volunteer(params)

        assert volunteer.save() != null
        assert Volunteer.count() == 1

        params.id = volunteer.id

        controller.delete()

        assert Volunteer.count() == 0
        assert Volunteer.get(volunteer.id) == null
        assert response.redirectedUrl == '/volunteer/list'
    }
}
