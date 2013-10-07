package org.kozak127.phantom



import org.junit.*
import grails.test.mixin.*

@TestFor(PointOfTheProgramController)
@Mock(PointOfTheProgram)
class PointOfTheProgramControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/pointOfTheProgram/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.pointOfTheProgramInstanceList.size() == 0
        assert model.pointOfTheProgramInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.pointOfTheProgramInstance != null
    }

    void testSave() {
        controller.save()

        assert model.pointOfTheProgramInstance != null
        assert view == '/pointOfTheProgram/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/pointOfTheProgram/show/1'
        assert controller.flash.message != null
        assert PointOfTheProgram.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/pointOfTheProgram/list'

        populateValidParams(params)
        def pointOfTheProgram = new PointOfTheProgram(params)

        assert pointOfTheProgram.save() != null

        params.id = pointOfTheProgram.id

        def model = controller.show()

        assert model.pointOfTheProgramInstance == pointOfTheProgram
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/pointOfTheProgram/list'

        populateValidParams(params)
        def pointOfTheProgram = new PointOfTheProgram(params)

        assert pointOfTheProgram.save() != null

        params.id = pointOfTheProgram.id

        def model = controller.edit()

        assert model.pointOfTheProgramInstance == pointOfTheProgram
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/pointOfTheProgram/list'

        response.reset()

        populateValidParams(params)
        def pointOfTheProgram = new PointOfTheProgram(params)

        assert pointOfTheProgram.save() != null

        // test invalid parameters in update
        params.id = pointOfTheProgram.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/pointOfTheProgram/edit"
        assert model.pointOfTheProgramInstance != null

        pointOfTheProgram.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/pointOfTheProgram/show/$pointOfTheProgram.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        pointOfTheProgram.clearErrors()

        populateValidParams(params)
        params.id = pointOfTheProgram.id
        params.version = -1
        controller.update()

        assert view == "/pointOfTheProgram/edit"
        assert model.pointOfTheProgramInstance != null
        assert model.pointOfTheProgramInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/pointOfTheProgram/list'

        response.reset()

        populateValidParams(params)
        def pointOfTheProgram = new PointOfTheProgram(params)

        assert pointOfTheProgram.save() != null
        assert PointOfTheProgram.count() == 1

        params.id = pointOfTheProgram.id

        controller.delete()

        assert PointOfTheProgram.count() == 0
        assert PointOfTheProgram.get(pointOfTheProgram.id) == null
        assert response.redirectedUrl == '/pointOfTheProgram/list'
    }
}
