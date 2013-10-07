package org.kozak127.phantom.Staff



import org.junit.*
import grails.test.mixin.*

@TestFor(ProgramItemWorkerController)
@Mock(ProgramItemWorker)
class ProgramItemWorkerControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/programItemWorker/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.programItemWorkerInstanceList.size() == 0
        assert model.programItemWorkerInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.programItemWorkerInstance != null
    }

    void testSave() {
        controller.save()

        assert model.programItemWorkerInstance != null
        assert view == '/programItemWorker/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/programItemWorker/show/1'
        assert controller.flash.message != null
        assert ProgramItemWorker.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/programItemWorker/list'

        populateValidParams(params)
        def programItemWorker = new ProgramItemWorker(params)

        assert programItemWorker.save() != null

        params.id = programItemWorker.id

        def model = controller.show()

        assert model.programItemWorkerInstance == programItemWorker
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/programItemWorker/list'

        populateValidParams(params)
        def programItemWorker = new ProgramItemWorker(params)

        assert programItemWorker.save() != null

        params.id = programItemWorker.id

        def model = controller.edit()

        assert model.programItemWorkerInstance == programItemWorker
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/programItemWorker/list'

        response.reset()

        populateValidParams(params)
        def programItemWorker = new ProgramItemWorker(params)

        assert programItemWorker.save() != null

        // test invalid parameters in update
        params.id = programItemWorker.id
        //TODO: add invalid values to params object

        controller.update()

        assert view == "/programItemWorker/edit"
        assert model.programItemWorkerInstance != null

        programItemWorker.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/programItemWorker/show/$programItemWorker.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        programItemWorker.clearErrors()

        populateValidParams(params)
        params.id = programItemWorker.id
        params.version = -1
        controller.update()

        assert view == "/programItemWorker/edit"
        assert model.programItemWorkerInstance != null
        assert model.programItemWorkerInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/programItemWorker/list'

        response.reset()

        populateValidParams(params)
        def programItemWorker = new ProgramItemWorker(params)

        assert programItemWorker.save() != null
        assert ProgramItemWorker.count() == 1

        params.id = programItemWorker.id

        controller.delete()

        assert ProgramItemWorker.count() == 0
        assert ProgramItemWorker.get(programItemWorker.id) == null
        assert response.redirectedUrl == '/programItemWorker/list'
    }
}
